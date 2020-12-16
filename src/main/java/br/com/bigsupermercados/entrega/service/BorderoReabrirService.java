package br.com.bigsupermercados.entrega.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bigsupermercados.entrega.modelo.entrega.Bordero;
import br.com.bigsupermercados.entrega.modelo.entrega.LancamentoBordero;
import br.com.bigsupermercados.entrega.repository.entrega.Borderos;
import br.com.bigsupermercados.entrega.repository.entrega.LancamentoBorderoRepository;

@Service
public class BorderoReabrirService {

	@Autowired
	private Borderos repository;

	@Autowired
	private LancamentoBorderoRepository lancamentoRepository;

	private static final String ARREDONDAMENTO = "Arredondamento";

	@Transactional
	public void reabrir(Long codigo) {
		Bordero bordero = repository.getOne(codigo);
		Optional<LancamentoBordero> lancamentoArredondamento = lancamentoRepository
				.findByBordero_CodigoAndTipoLancamento_Nome(bordero.getCodigo(), ARREDONDAMENTO);

		if (lancamentoArredondamento.isPresent()) {
			lancamentoRepository.delete(lancamentoArredondamento.get());
		}

		bordero.setAberto(true);
	}
}
