package br.com.bigsupermercados.entrega.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bigsupermercados.entrega.modelo.entrega.TipoLancamento;
import br.com.bigsupermercados.entrega.repository.entrega.TipoLancamentoRepository;

@Service
public class TipoLancamentoService {

	@Autowired
	private TipoLancamentoRepository repository;

	public List<TipoLancamento> listar() {
		return repository.findAll();
	}

	public void salvar(TipoLancamento tipoLancamento) {
		repository.save(tipoLancamento);
	}
}
