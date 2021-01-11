package br.com.bigsupermercados.entrega.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bigsupermercados.entrega.controller.form.GuiaLiberarForm;
import br.com.bigsupermercados.entrega.modelo.entrega.Guia;
import br.com.bigsupermercados.entrega.modelo.entrega.Loja;
import br.com.bigsupermercados.entrega.modelo.entrega.Motorista;
import br.com.bigsupermercados.entrega.repository.entrega.GuiaRepository;

@Service
public class LiberarGuiaService {

	@Autowired
	private GuiaRepository repository;

	public void liberarGuia(GuiaLiberarForm guiaLiberarForm) {
		List<Guia> guias = guiaLiberarForm.getGuias();

		guias.forEach(guia -> {
			Motorista motorista = guiaLiberarForm.getMotorista();

//			if(motorista.isPossuiPorcentagemDeExcecao()) {
//				guia.setPorcentagem(motorista.getPorcentagemExcecao());
//			}

			tratarPorcentagem(guia, motorista);

			guia.setMotorista(motorista);
		});

		repository.saveAll(guias);
	}

	public void tratarPorcentagem(Guia guia, Motorista motorista) {
		Loja loja = guia.getLoja();

		if (loja.isPossuiPorcentagemExcecao()) {
			guia.setPorcentagem(loja.getPorcentagemExcecao());
			return;
		}

		if (motorista.isPossuiPorcentagemDeExcecao()) {
			guia.setPorcentagem(motorista.getPorcentagemExcecao());
			return;
		}
	}
}
