package br.com.bigsupermercados.entrega.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.bigsupermercados.entrega.controller.dto.CidadeDTO;
import br.com.bigsupermercados.entrega.controller.form.CidadeForm;
import br.com.bigsupermercados.entrega.modelo.entrega.Cidade;
import br.com.bigsupermercados.entrega.service.CidadeService;

@Controller
@RequestMapping("/cidade")
public class CidadeController {

	@Autowired
	private CidadeService service;
	
	@GetMapping
	public ModelAndView lista(CidadeForm form) {
		ModelAndView mv = new ModelAndView("cidade/PesquisaCidade");
		List<Cidade> cidades = service.lista();
		mv.addObject("cidades", CidadeDTO.converter(cidades));
		return mv;
	}
}
