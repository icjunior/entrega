package br.com.bigsupermercados.entrega.service;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.bigsupermercados.entrega.modelo.entrega.Cliente;
import br.com.bigsupermercados.entrega.repository.entrega.Clientes;
import br.com.bigsupermercados.entrega.service.exception.ImpossivelExcluirEntidadeException;

@Service
public class ExclusaoCadastroClienteService {
	
	@Autowired
	private Clientes repository;

	@Transactional
	public void excluir(Cliente cliente) {
		try {
			repository.delete(cliente);
			repository.flush();

		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar cliente. Já foi usada em algum momento");
		}
	}
}
