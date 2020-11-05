package br.com.bigsupermercados.entrega.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bigsupermercados.entrega.controller.filter.GuiaFilter;
import br.com.bigsupermercados.entrega.controller.form.GuiaLiberarForm;
import br.com.bigsupermercados.entrega.modelo.entrega.Bordero;
import br.com.bigsupermercados.entrega.modelo.entrega.Guia;
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

	@Autowired
	private BorderoService borderoService;

	public void salvar(Guia guia) {
		Optional<ZanM45> cupomZanthusOpt = zanM45Service.buscarCupom(guia.getData(), guia.getLoja().getCodigo(),
				guia.getPdv(), Integer.parseInt(guia.getCupom()));

		if (!cupomZanthusOpt.isPresent()) {
			throw new RegistroNaoEncontradoException(
					"Cupom fiscal não encontrado. Revise as informações e tente novamente.");
		}

		Optional<Guia> guiaOpt = repository.buscarCupom(guia.getData(), guia.getLoja().getCodigo(), guia.getPdv(),
				guia.getCupom(), guia.getValor());

		if (guiaOpt.isPresent()) {
			throw new RegistroJaCadastradoException("Guia já lançada no sistema.");
		}

		guia.setChaveAcesso(cupomZanthusOpt.get().getM45xb());

		repository.save(guia);
	}

	public void liberarGuia(GuiaLiberarForm guiaLiberarForm) {
		Bordero bordero;
		Optional<Bordero> borderoAberto = borderoService.borderoAbertoPorMotorista(guiaLiberarForm.getMotorista());

		if (!borderoAberto.isPresent()) {
			bordero = borderoService.criarBordero(guiaLiberarForm.getMotorista());
		} else {
			bordero = borderoAberto.get();
		}

		List<Guia> guias = guiaLiberarForm.getGuias();

		guias.forEach(guia -> {
			guia.setMotorista(guiaLiberarForm.getMotorista());
			guia.setBordero(bordero);
		});

		repository.saveAll(guias);
	}

	public List<Bordero> buscarPorBordero(Bordero bordero) {
		return repository.findByBordero_Codigo(bordero.getCodigo());
	}

	public void eliminarCupomBordero(Guia guia) {
		guia.setBordero(null);
		guia.setMotorista(null);
		guia.setValidado(false);
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
}
