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
		Optional<Bordero> borderoOpt = repository.findByMotorista_CodigoAndAbertoTrue(motorista.getCodigo());

		if (borderoOpt.isPresent()) {
			throw new RegistroJaCadastradoException("O borderô " + borderoOpt.get().getCodigo()
					+ " já está vinculado ao motorista " + motorista.getNome());
		}

		Bordero bordero = new Bordero(motorista);
		return repository.save(bordero);
	}
}
