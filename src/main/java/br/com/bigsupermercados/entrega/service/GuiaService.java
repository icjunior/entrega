package br.com.bigsupermercados.entrega.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bigsupermercados.entrega.controller.form.GuiaLiberarForm;
import br.com.bigsupermercados.entrega.modelo.entrega.Bordero;
import br.com.bigsupermercados.entrega.modelo.entrega.Guia;
import br.com.bigsupermercados.entrega.repository.entrega.GuiaRepository;

@Service
public class GuiaService {

	@Autowired
	private GuiaRepository repository;

	@Autowired
	private BorderoService borderoService;

	public void salvar(Guia guia) {
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
}
