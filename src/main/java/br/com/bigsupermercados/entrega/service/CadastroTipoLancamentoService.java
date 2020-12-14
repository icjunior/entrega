package br.com.bigsupermercados.entrega.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bigsupermercados.entrega.modelo.entrega.TipoLancamento;
import br.com.bigsupermercados.entrega.repository.entrega.TipoLancamentoRepository;

@Service
public class CadastroTipoLancamentoService {

	@Autowired
	private TipoLancamentoRepository repository;

	public void salvar(TipoLancamento tipoLancamento) {
		repository.save(tipoLancamento);
	}
}
