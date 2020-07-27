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

import br.com.bigsupermercados.entrega.modelo.Motorista;
import br.com.bigsupermercados.entrega.repository.Motoristas;
import br.com.bigsupermercados.entrega.service.CadastroMotoristaService;

@Controller
@RequestMapping("/motorista")
public class MotoristaController {

	@Autowired
	private Motoristas motoristas;

	@Autowired
	private CadastroMotoristaService cadastroMotoristaService;

	@GetMapping
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("motorista/PesquisaMotorista");
		mv.addObject("motoristas", motoristas.findAll());
		return mv;
	}

	@GetMapping("/nova")
	public ModelAndView nova(Motorista motorista) {
		ModelAndView mv = new ModelAndView("motorista/CadastroMotorista");
		return mv;
	}

	@PostMapping("/nova")
	public ModelAndView salvar(@Valid Motorista motorista, BindingResult result, Model model,
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return nova(motorista);
		}

		cadastroMotoristaService.salvar(motorista);
		attributes.addFlashAttribute("mensagem", "Motorista salvo com sucesso");

		return new ModelAndView("redirect:/motorista/nova");
	}
}
