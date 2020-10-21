package br.com.bigsupermercados.entrega.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.bigsupermercados.entrega.modelo.entrega.Cliente;
import br.com.bigsupermercados.entrega.repository.entrega.Clientes;
import br.com.bigsupermercados.entrega.service.exception.ImpossivelExcluirEntidadeException;

@Service
public class CadastroClienteService {

	@Autowired
	private Clientes clientes;

	@Transactional
	public void salvar(Cliente cliente) {
		clientes.save(cliente);
	}

	@Transactional
	public void excluir(Cliente cliente) {
		try {
			clientes.delete(cliente);
			clientes.flush();

		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar cliente. Já foi usada em algum momento");
		}
	}

	public Optional<Cliente> buscaPorCPF(String cpf) {
		return clientes.findByCpf(cpf);
	}

	public List<Cliente> buscaPorNome(String nome) {
		return clientes.findByNomeContaining(nome);
	}
}
