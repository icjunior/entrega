package br.com.bigsupermercados.entrega.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bigsupermercados.entrega.controller.dto.GuiaDTO;
import br.com.bigsupermercados.entrega.modelo.entrega.Guia;
import br.com.bigsupermercados.entrega.repository.entrega.GuiaRepository;
import br.com.bigsupermercados.entrega.service.exception.RegistroNaoEncontradoException;

@Service
public class ValidarGuiaService {

	@Autowired
	private GuiaRepository repository;

	@Transactional
	public GuiaDTO validarCupomBordero(Long bordero, String chaveAcesso) {

		Optional<Guia> guiaOpt = repository.buscarCupomNoBordero(bordero, chaveAcesso);

		return Optional.ofNullable(new GuiaDTO(guiaOpt.get()))
				.orElseThrow(() -> new RegistroNaoEncontradoException("Registro não encontrado"));
	}
}
