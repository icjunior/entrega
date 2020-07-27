package br.com.bigsupermercados.entrega.service;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.bigsupermercados.entrega.modelo.Motorista;
import br.com.bigsupermercados.entrega.repository.Motoristas;
import br.com.bigsupermercados.entrega.service.exception.ImpossivelExcluirEntidadeException;

@Service
public class CadastroMotoristaService {

	@Autowired
	private Motoristas motoristas;

	@Transactional
	public void salvar(Motorista motorista) {
		motoristas.save(motorista);
	}

	@Transactional
	public void excluir(Motorista motorista) {
		try {
			motoristas.delete(motorista);
			motoristas.flush();

		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar motorista. Já foi utilizado em algum momento");
		}
	}
}
