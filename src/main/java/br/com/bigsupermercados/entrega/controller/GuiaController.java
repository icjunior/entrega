package br.com.bigsupermercados.entrega.controller;

import java.util.List;

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
import br.com.bigsupermercados.entrega.controller.form.GuiaLiberarForm;
import br.com.bigsupermercados.entrega.modelo.entrega.Guia;
import br.com.bigsupermercados.entrega.modelo.entrega.Loja;
import br.com.bigsupermercados.entrega.modelo.entrega.Motorista;
import br.com.bigsupermercados.entrega.repository.entrega.Clientes;
import br.com.bigsupermercados.entrega.repository.entrega.GuiaRepository;
import br.com.bigsupermercados.entrega.repository.entrega.Lojas;
import br.com.bigsupermercados.entrega.repository.entrega.Motoristas;
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

	@Autowired
	private Motoristas motoristaRepository;

	@GetMapping("/nova")
	public ModelAndView nova(GuiaForm guiaForm) {
		ModelAndView mv = new ModelAndView("guia/EmitirGuia");
		List<Loja> lojas = lojaRepository.findAll();
		
		mv.addObject("lojas", lojas);
		return mv;
	}

	@GetMapping
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("guia/PesquisaGuia");
		mv.addObject("guias", repository.findAll());
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

	@GetMapping("/liberar")
	public ModelAndView listaGuiasALiberar(GuiaLiberarForm guiaLiberarForm) {
		ModelAndView mv = new ModelAndView("guia/LiberarGuia");
		List<Motorista> motoristas = motoristaRepository.findByAtivoTrue();
		List<Guia> guiasALiberar = repository.findByMotoristaIsNull();
		mv.addObject("guiasALiberar", guiasALiberar);
		mv.addObject("motoristas", motoristas);
		return mv;
	}

	@PostMapping("/liberar")
	public ModelAndView liberarGuias(GuiaLiberarForm guiaLiberarForm, BindingResult result, Model model,
			RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return listaGuiasALiberar(guiaLiberarForm);
		}

		service.liberarGuia(guiaLiberarForm);
		attributes.addFlashAttribute("mensagem", "Guias vinculadas com sucesso");

		return new ModelAndView("redirect:/guia/liberar");
	}
}