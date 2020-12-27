package br.com.bigsupermercados.entrega.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bigsupermercados.entrega.modelo.entrega.Bordero;
import br.com.bigsupermercados.entrega.modelo.entrega.Motorista;
import br.com.bigsupermercados.entrega.repository.entrega.Borderos;
import br.com.bigsupermercados.entrega.service.exception.RegistroJaCadastradoException;

@Service
public class CadastroBorderoService {

	@Autowired
	private Borderos repository;

	@Transactional
	public Bordero criar(Motorista motorista) {
		Optional<Bordero> borderoAbertoOpt = repository.findByMotorista_CodigoAndAbertoTrue(motorista.getCodigo());

		if (borderoAbertoOpt.isPresent()) {
			throw new RegistroJaCadastradoException("O borderô " + borderoAbertoOpt.get().getCodigo()
					+ " já está vinculado ao motorista " + motorista.getNome());
		}

//		Optional<Bordero> borderoFechadoOpt = repository
//				.findTop1ByMotorista_CodigoAndAbertoFalseOrderByDataHoraFechamentoDesc(motorista.getCodigo());
//
//		BigDecimal arredondamentoAnterior;
//		if (borderoFechadoOpt.isPresent()) {
//			List<LancamentoBordero> lancamentos = borderoFechadoOpt.get().getLancamentos();
//			
//			arredondamentoAnterior = lancamentos.stream()
//					.filter(lancamento -> "Arredondamento".equals(lancamento.getTipoLancamento().getNome()))
//					.map(LancamentoBordero::getValor)
//					.findAny()
//					.orElse(BigDecimal.ZERO);
//		}

		Bordero bordero = new Bordero(motorista);

		return repository.save(bordero);
	}
}
