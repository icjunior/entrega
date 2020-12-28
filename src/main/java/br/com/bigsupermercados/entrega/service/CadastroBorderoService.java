package br.com.bigsupermercados.entrega.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bigsupermercados.entrega.modelo.entrega.Bordero;
import br.com.bigsupermercados.entrega.modelo.entrega.LancamentoBordero;
import br.com.bigsupermercados.entrega.modelo.entrega.Motorista;
import br.com.bigsupermercados.entrega.modelo.entrega.TipoLancamento;
import br.com.bigsupermercados.entrega.repository.entrega.Borderos;
import br.com.bigsupermercados.entrega.repository.entrega.TipoLancamentoRepository;
import br.com.bigsupermercados.entrega.service.exception.RegistroJaCadastradoException;

@Service
public class CadastroBorderoService {

	@Autowired
	private Borderos repository;
	
	@Autowired
	private CadastroLancamentoBorderoService cadastroLancamentoBorderoService;
	
	@Autowired
	private TipoLancamentoRepository tipoLancamentoRepository;

	@Transactional
	public Bordero criar(Motorista motorista) {
		Optional<Bordero> borderoAbertoOpt = repository.findByMotorista_CodigoAndAbertoTrue(motorista.getCodigo());

		if (borderoAbertoOpt.isPresent()) {
			throw new RegistroJaCadastradoException("O borderô " + borderoAbertoOpt.get().getCodigo()
					+ " já está vinculado ao motorista " + motorista.getNome());
		}


		Bordero bordero = new Bordero(motorista);
		repository.save(bordero);

		Optional<Bordero> borderoFechadoOpt = repository
				.findTop1ByMotorista_CodigoAndAbertoFalseOrderByDataHoraFechamentoDesc(motorista.getCodigo());

		if (borderoFechadoOpt.isPresent()) {
			List<LancamentoBordero> lancamentos = borderoFechadoOpt.get().getLancamentos();

			BigDecimal arredondamentoAnterior = lancamentos.stream()
					.filter(lancamento -> "Arredondamento".equals(lancamento.getTipoLancamento().getNome()))
					.map(LancamentoBordero::getValor).findAny().orElse(BigDecimal.ZERO);
			
			TipoLancamento tipoLancamento = tipoLancamentoRepository.findByNome("Arredondamento Anterior");
			
			LancamentoBordero lancamento = new LancamentoBordero(bordero, tipoLancamento, arredondamentoAnterior);
			
			cadastroLancamentoBorderoService.cadastrarLancamento(lancamento);
			
		}


		return bordero;
	}
}
