package br.com.bigsupermercados.entrega.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.bigsupermercados.entrega.controller.form.GuiaForm;
import br.com.bigsupermercados.entrega.modelo.entrega.Guia;
import br.com.bigsupermercados.entrega.repository.entrega.Clientes;
import br.com.bigsupermercados.entrega.repository.entrega.GuiaRepository;
import br.com.bigsupermercados.entrega.repository.entrega.Lojas;
import br.com.bigsupermercados.entrega.service.GuiaService;

@Controller
@RequestMapping("/guia")
public class GuiaController {

	@Autowired
	private GuiaService service;

	@Autowired
	private Clientes clienteRepository;

	@Autowired
	private Lojas lojaRepository;

	@Autowired
	private GuiaRepository repository;

	@GetMapping("/nova")
	public ModelAndView nova(GuiaForm guiaForm) {
		ModelAndView mv = new ModelAndView("guia/EmitirGuia");
		mv.addObject("guias", repository.findAll());
		return mv;
	}

	@GetMapping
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("guia/PesquisaGuia");

		return mv;
	}

	@PostMapping("/nova")
	public ModelAndView salvar(GuiaForm form, BindingResult result, Model model, RedirectAttributes attributes) {

		Guia guia = form.converter(clienteRepository, lojaRepository);

		if (result.hasErrors()) {
			return nova(form);
		}

		service.salvar(guia);
		attributes.addFlashAttribute("mensagem", "Guia lançada com sucesso");

		return new ModelAndView("redirect:/guia/nova");
	}
}
