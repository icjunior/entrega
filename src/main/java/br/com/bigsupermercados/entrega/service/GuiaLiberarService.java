package br.com.bigsupermercados.entrega.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bigsupermercados.entrega.controller.form.GuiaLiberarForm;
import br.com.bigsupermercados.entrega.modelo.entrega.Guia;
import br.com.bigsupermercados.entrega.repository.entrega.GuiaRepository;

@Service
public class GuiaLiberarService {

	@Autowired
	private GuiaRepository repository;

	public void liberarGuia(GuiaLiberarForm guiaLiberarForm) {
		List<Guia> guias = guiaLiberarForm.getGuias();

		guias.forEach(guia -> {
			guia.setMotorista(guiaLiberarForm.getMotorista());
		});

		repository.saveAll(guias);
	}
}
