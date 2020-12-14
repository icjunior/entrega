package br.com.bigsupermercados.entrega.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bigsupermercados.entrega.modelo.entrega.TipoLancamento;
import br.com.bigsupermercados.entrega.repository.entrega.TipoLancamentoRepository;

@Service
public class SelecaoTipoLancamentoService {

	@Autowired
	private TipoLancamentoRepository repository;

	public List<TipoLancamento> listar() {
		return repository.findAll();
	}

	public Page<TipoLancamento> buscarPaginado(Pageable pageable) {
		List<TipoLancamento> tiposLancamento = repository.findAll();

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;

		List<TipoLancamento> tiposLancamentoList = new ArrayList<>();

		if (tiposLancamento.size() < startItem) {
			tiposLancamento = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, tiposLancamento.size());
			tiposLancamentoList = tiposLancamento.subList(startItem, toIndex);
		}

		Page<TipoLancamento> tipoLancamentoPage = new PageImpl<TipoLancamento>(tiposLancamentoList,
				PageRequest.of(currentPage, pageSize), tiposLancamento.size());

		return tipoLancamentoPage;
	}
}
