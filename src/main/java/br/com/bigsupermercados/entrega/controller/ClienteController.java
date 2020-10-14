package br.com.bigsupermercados.entrega.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.bigsupermercados.entrega.controller.dto.ClienteDTO;
import br.com.bigsupermercados.entrega.controller.form.ClienteForm;
import br.com.bigsupermercados.entrega.modelo.entrega.Cliente;
import br.com.bigsupermercados.entrega.repository.entrega.Clientes;
import br.com.bigsupermercados.entrega.repository.entrega.EnderecoRepository;
import br.com.bigsupermercados.entrega.service.CadastroClienteService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private Clientes clientes;

	@Autowired
	private CadastroClienteService cadastroClienteService;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	@GetMapping
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("cliente/PesquisaCliente");
		mv.addObject("clientes", clientes.findAll());
		return mv;
	}
	
	@GetMapping("/busca/{cpf}")
	public ResponseEntity<ClienteDTO> buscarPorCPF(@PathVariable String cpf) {
		Optional<Cliente> clienteOpt = cadastroClienteService.buscarPorCPF(cpf);
		
		if(clienteOpt.isPresent()) {
			return ResponseEntity.ok(new ClienteDTO(clienteOpt.get()));
		}
		
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/nova")
	public ModelAndView nova(ClienteForm clienteForm) {
		ModelAndView mv = new ModelAndView("cliente/CadastroCliente");
		return mv;
	}

	@PostMapping("/nova")
	public ModelAndView salvar(@Valid ClienteForm clienteForm, BindingResult result, Model model,
			RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			return nova(clienteForm);
		}

		Cliente cliente = clienteForm.converter(enderecoRepository);
		
		cadastroClienteService.salvar(cliente);
		attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso");

		return new ModelAndView("redirect:/cliente/nova");
	}
}
