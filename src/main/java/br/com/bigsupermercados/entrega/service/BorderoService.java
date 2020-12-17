package br.com.bigsupermercados.entrega.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bigsupermercados.entrega.modelo.entrega.Bordero;
import br.com.bigsupermercados.entrega.modelo.entrega.Motorista;
import br.com.bigsupermercados.entrega.repository.entrega.Borderos;

@Service
public class BorderoService {

	@Autowired
	private Borderos repository;

	public Optional<Bordero> borderoAbertoPorMotorista(Motorista motorista) {
		return repository.findByMotorista_CodigoAndAbertoTrue(motorista.getCodigo());
	}

	public List<Bordero> listar() {
		return repository.findByAbertoTrue();
	}

	public Optional<Bordero> buscarBordero(Long codigoBordero) {
		return repository.findById(codigoBordero);
	}

	public List<Bordero> listaFechados() {
		return repository.findByAbertoFalse();
	}
}
