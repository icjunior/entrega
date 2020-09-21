package br.com.bigsupermercados.entrega.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bigsupermercados.entrega.modelo.entrega.Bordero;
import br.com.bigsupermercados.entrega.modelo.entrega.ModoLancamento;
import br.com.bigsupermercados.entrega.modelo.entrega.Motorista;
import br.com.bigsupermercados.entrega.repository.entrega.Borderos;

@Service
public class BorderoService {

	@Autowired
	private Borderos repository;

	public Optional<Bordero> borderoAbertoPorMotorista(Motorista motorista) {
		return repository.findByMotorista_CodigoAndAbertoTrue(motorista.getCodigo());
	}

	public Bordero criarBordero(Motorista motorista) {
		Bordero bordero = new Bordero(motorista, LocalDateTime.now(), true);
		return repository.save(bordero);
	}

	public List<Bordero> listar() {
		return repository.findAll();
	}

	public Optional<Bordero> buscarBordero(Long codigoBordero) {
		return repository.findById(codigoBordero);
	}

	public void reabrir(Long codigo) {
		Bordero bordero = repository.getOne(codigo);
		// apagar os registros de arredondamento na parte das listas
		bordero.setAberto(true);
		repository.flush();
	}

	public List<Bordero> listaFechados() {
		return repository.findByAbertoFalse();
	}

	public void fechar(Long codigo) {
		Bordero bordero = repository.findById(codigo).get();
		// calcular o arredondamento

		// total dos descontos
		BigDecimal totalDescontos = bordero.getLancamentos().stream()
				.filter(lancamento -> lancamento.getTipoLancamento().getModoLancamento() == ModoLancamento.DESCONTO)
				.map(lancamento -> lancamento.getValor()).reduce(BigDecimal.ZERO, BigDecimal::add);

		// total dos acréscimos
		BigDecimal totalAcrescimos = bordero.getLancamentos().stream()
				.filter(lancamento -> lancamento.getTipoLancamento().getModoLancamento() == ModoLancamento.ACRESCIMO)
				.map(lancamento -> lancamento.getValor()).reduce(BigDecimal.ZERO, BigDecimal::add);

		// total dos cupons
		BigDecimal totalCupons = bordero.getGuias().stream().map(guia -> guia.getValor()).reduce(BigDecimal.ZERO,
				BigDecimal::add);

		// valor total do borderô
		BigDecimal total = totalCupons.add(totalAcrescimos).subtract(totalDescontos);
		
		// valor a ser creditado
		BigDecimal valorArredondamento = calculaArredondamento(total).subtract(total);

		System.out.println(valorArredondamento);

		// fazer a inserção do registro do arredondamento na tela de lançamentos
//		bordero.setAberto(false);
//		repository.flush();
	}

	public BigDecimal calculaArredondamento(BigDecimal valor) {
		BigDecimal fatorArredondamento = new BigDecimal("5");

		return valor.divide(fatorArredondamento).setScale(0, RoundingMode.UP).multiply(fatorArredondamento);
	}
}
