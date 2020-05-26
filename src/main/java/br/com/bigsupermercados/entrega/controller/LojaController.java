package br.com.bigsupermercados.entrega.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.bigsupermercados.entrega.modelo.Loja;
import br.com.bigsupermercados.entrega.repository.Lojas;
import br.com.bigsupermercados.entrega.service.CadastroLojaService;

@Controller
@RequestMapping("/loja")
public class LojaController {

	@Autowired
	private Lojas lojas;
	
	@Autowired
	private CadastroLojaService cadastroLojaService;
	
	@GetMapping
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("loja/PesquisaLoja");
		mv.addObject("lojas", lojas.findAll());
		return mv;
	}
	
	@GetMapping("/nova")
	public ModelAndView nova(Loja loja) {
		ModelAndView mv = new ModelAndView("loja/CadastroLoja");
		return mv;
	}
	
	@PostMapping("/nova")
	public ModelAndView salvar(@Valid Loja loja, BindingResult result, Model model,
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return nova(loja);
		}

		cadastroLojaService.salvar(loja);
		attributes.addFlashAttribute("mensagem", "Loja salva com sucesso");

		return new ModelAndView("redirect:/loja/nova");
	}
}
