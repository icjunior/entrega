package br.com.bigsupermercados.entrega.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bigsupermercados.entrega.modelo.entrega.Bordero;
import br.com.bigsupermercados.entrega.modelo.entrega.Guia;
import br.com.bigsupermercados.entrega.modelo.entrega.LancamentoBordero;
import br.com.bigsupermercados.entrega.modelo.entrega.ModoLancamento;
import br.com.bigsupermercados.entrega.modelo.entrega.Motorista;
import br.com.bigsupermercados.entrega.repository.entrega.Borderos;
import br.com.bigsupermercados.entrega.repository.entrega.GuiaRepository;

@Service
public class BorderoService {

	@Autowired
	private Borderos repository;

	@Autowired
	private LancamentoBorderoService lancamentoBorderoService;

	@Autowired
	private GuiaRepository guiaRepository;

	public Optional<Bordero> borderoAbertoPorMotorista(Motorista motorista) {
		return repository.findByMotorista_CodigoAndAbertoTrue(motorista.getCodigo());
	}

	public Bordero criarBordero(Motorista motorista) {
		Bordero bordero = new Bordero(motorista, LocalDateTime.now(), true);
		return repository.save(bordero);
	}

	public List<Bordero> listar() {
		return repository.findByAbertoTrue();
	}

	public Optional<Bordero> buscarBordero(Long codigoBordero) {
		return repository.findById(codigoBordero);
	}

	public void reabrir(Long codigo) {
		Bordero bordero = repository.getOne(codigo);
		// apagar os registros de arredondamento na parte das listas
		bordero.setAberto(true);
	}

	public List<Bordero> listaFechados() {
		return repository.findByAbertoFalse();
	}

	public Bordero fechar(Long codigo) {
		Bordero bordero = repository.findById(codigo).get();

		// total dos descontos
		BigDecimal totalDescontos = calculaDescontos(bordero.getLancamentos());

		// total dos acréscimos
		BigDecimal totalAcrescimos = calculaAcrescimos(bordero.getLancamentos());

		// total dos cupons
		BigDecimal totalGuias = calculaTotalGuias(bordero.getGuias());

		// valor a ser creditado
		BigDecimal valorArredondamento = calculaArredondamento(totalDescontos, totalAcrescimos, totalGuias);

		// gravando o registro de arredondamento
		lancamentoBorderoService.gravarArredondamento(bordero, valorArredondamento);

		// fazer a inserção do registro do arredondamento na tela de lançamentos
		bordero.setAberto(false);

		return bordero;
	}

	public BigDecimal calculaDescontos(List<LancamentoBordero> lancamentos) {
		return lancamentos.stream()
				.filter(lancamento -> lancamento.getTipoLancamento().getModoLancamento() == ModoLancamento.DESCONTO)
				.map(lancamento -> lancamento.getValor()).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public BigDecimal calculaAcrescimos(List<LancamentoBordero> lancamentos) {
		return lancamentos.stream()
				.filter(lancamento -> lancamento.getTipoLancamento().getModoLancamento() == ModoLancamento.ACRESCIMO)
				.map(lancamento -> lancamento.getValor()).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public BigDecimal calculaTotalGuias(List<Guia> guias) {
		// return guias.stream().map(guia -> guia.getValor()).reduce(BigDecimal.ZERO,
		// BigDecimal::add);
		return guias
				.stream().filter(guia -> guia.isValidado()).map(guia -> guia.getValor().multiply(guia.getPorcentagem())
						.divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_DOWN))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public BigDecimal calculaArredondamento(BigDecimal descontos, BigDecimal acrescimos, BigDecimal guias) {
		BigDecimal fatorArredondamento = new BigDecimal("5");
		BigDecimal valorBorderoSemArredondamento = guias.add(acrescimos).subtract(descontos);
		BigDecimal valorBorderoArredondado = valorBorderoSemArredondamento.divide(fatorArredondamento)
				.setScale(0, RoundingMode.UP).multiply(fatorArredondamento);

		return valorBorderoArredondado.subtract(valorBorderoSemArredondamento);
	}

	public Optional<Guia> validarCupomBordero(Long bordero, String chaveAcesso) {

		Optional<Guia> guiaOpt = guiaRepository.buscarCupomNoBordero(bordero, chaveAcesso);

		return guiaOpt;
	}
}
