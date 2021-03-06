package br.com.bigsupermercados.entrega.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bigsupermercados.entrega.controller.filter.GuiaFilter;
import br.com.bigsupermercados.entrega.modelo.entrega.Bordero;
import br.com.bigsupermercados.entrega.modelo.entrega.Guia;
import br.com.bigsupermercados.entrega.modelo.entrega.Motorista;
import br.com.bigsupermercados.entrega.modelo.zanthus.ZanM45;
import br.com.bigsupermercados.entrega.repository.entrega.GuiaRepository;
import br.com.bigsupermercados.entrega.service.exception.RegistroJaCadastradoException;
import br.com.bigsupermercados.entrega.service.exception.RegistroNaoEncontradoException;

@Service
public class GuiaService {

	@Autowired
	private GuiaRepository repository;

	@Autowired
	private ZanM45Service zanM45Service;

	public void salvar(Guia guia) {
		if(guia.isCupom()) {
			Optional<ZanM45> cupomZanthusOpt = zanM45Service.buscarCupom(guia.getData(), guia.getLoja().getCodigo(),
					guia.getPdv(), Integer.parseInt(guia.getCupom()));
			
			if (!cupomZanthusOpt.isPresent()) {
				throw new RegistroNaoEncontradoException(
						"Cupom fiscal não encontrado. Revise as informações e tente novamente.");
			}
		
			guia.setChaveAcesso(cupomZanthusOpt.get().getM45xb());
		}

		Optional<Guia> guiaOpt = repository.buscarCupom(guia.getData(), guia.getLoja().getCodigo(), guia.getPdv(),
				guia.getCupom(), guia.getValor());

		if (guiaOpt.isPresent()) {
			throw new RegistroJaCadastradoException("Guia já lançada no sistema.");
		}

		repository.save(guia);
	}

	public List<Bordero> buscarPorBordero(Bordero bordero) {
		return repository.findByBordero_Codigo(bordero.getCodigo());
	}

	public void eliminarCupomBordero(Guia guia) {
		guia.setBordero(null);
//		guia.setMotorista(null);
	}

	public Page<Guia> buscar(GuiaFilter guiaFilter, Pageable paginacao) {
		return repository.buscarCupomComPaginacao(guiaFilter.getData(), guiaFilter.getLoja(), guiaFilter.getPdv(),
				guiaFilter.getCupom(), guiaFilter.getValor(), paginacao);
	}

	public Guia reentrega(Guia guia) {
		Guia guiaNova = new Guia(guia);
		guiaNova.setReentrega(true);
		return guiaNova;
	}

	public Guia buscarCupom(Motorista motorista, String cupom, BigDecimal valor) {
		Optional<Guia> guia = repository.buscarGuiaLiberada(motorista.getCodigo(), cupom, valor);

		if (!guia.isPresent()) {
			return guia.orElseThrow(RegistroNaoEncontradoException::new);
		}

		return guia.get();
	}

	@Transactional
	public Guia validarCupom(Guia guia, Bordero bordero) {
		guia.setBordero(bordero);
		return guia;
	}
}
