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

import br.com.bigsupermercados.entrega.modelo.Cliente;
import br.com.bigsupermercados.entrega.repository.Clientes;
import br.com.bigsupermercados.entrega.service.CadastroClienteService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private Clientes clientes;

	@Autowired
	private CadastroClienteService cadastroClienteService;

	@GetMapping
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("cliente/PesquisaCliente");
		mv.addObject("clientes", clientes.findAll());
		return mv;
	}

	@GetMapping("/nova")
	public ModelAndView nova(Cliente cliente) {
		ModelAndView mv = new ModelAndView("cliente/CadastroCliente");
		return mv;
	}

	@PostMapping("/nova")
	public ModelAndView salvar(@Valid Cliente cliente, BindingResult result, Model model,
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return nova(cliente);
		}

		cadastroClienteService.salvar(cliente);
		attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso");

		return new ModelAndView("redirect:/cliente/nova");
	}
}
