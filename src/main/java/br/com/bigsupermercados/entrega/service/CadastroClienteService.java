package br.com.bigsupermercados.entrega.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.bigsupermercados.entrega.modelo.entrega.Cliente;
import br.com.bigsupermercados.entrega.repository.entrega.Clientes;
import br.com.bigsupermercados.entrega.service.exception.RegistroJaCadastradoException;

@Service
public class CadastroClienteService {

	@Autowired
	private Clientes repository;

	@Transactional
	public void salvar(Cliente cliente) {
		Optional<Cliente> clienteOpt = repository.findByCpf(cliente.getCpf());

		if (clienteOpt.isPresent()) {
			throw new RegistroJaCadastradoException("CPF já cadastrado no sistema");
		}

		repository.save(cliente);
	}
}
