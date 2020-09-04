package br.com.bigsupermercados.entrega.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.bigsupermercados.entrega.controller.dto.BorderoDTO;
import br.com.bigsupermercados.entrega.controller.dto.BorderoDetalheDTO;
import br.com.bigsupermercados.entrega.modelo.entrega.Bordero;
import br.com.bigsupermercados.entrega.service.BorderoService;

@Controller
@RequestMapping("/bordero")
public class BorderoController {

	@Autowired
	private BorderoService service;

	@GetMapping
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("bordero/PesquisaBordero");

		List<Bordero> borderos = service.listar();

		mv.addObject("borderos", BorderoDTO.converter(borderos));
		return mv;
	}

	@GetMapping("/edita/{codigoBordero}")
	public ModelAndView pesquisaPorBodero(@PathVariable Long codigoBordero) {
		ModelAndView mv = new ModelAndView("bordero/DetalheBordero");

		Optional<Bordero> bordero = service.buscarBordero(codigoBordero);
		mv.addObject("bordero", BorderoDetalheDTO.converter(bordero));

		return mv;
	}
}
