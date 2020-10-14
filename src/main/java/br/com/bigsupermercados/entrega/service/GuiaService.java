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

	public List<Bordero> buscarPorBordero(Bordero bordero) {
		return repository.findByBordero_Codigo(bordero.getCodigo());
	}

	public void eliminarCupomBordero(Long codigo) {
		Guia guia = repository.getOne(codigo);
		guia.setBordero(null);
		repository.flush();
	}

	public Page<Guia> buscar(GuiaFilter guiaFilter, Pageable paginacao) {
		return repository.findByDataAndLoja_CodigoAndPdvAndCupomAndValor(guiaFilter.getData(), guiaFilter.getLoja(),
				guiaFilter.getPdv(), guiaFilter.getCupom(), guiaFilter.getValor(), paginacao);
	}
}
