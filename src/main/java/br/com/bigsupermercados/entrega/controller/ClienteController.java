package br.com.bigsupermercados.entrega.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.bigsupermercados.entrega.controller.dto.ClienteDTO;
import br.com.bigsupermercados.entrega.controller.form.ClienteForm;
import br.com.bigsupermercados.entrega.modelo.entrega.Cliente;
import br.com.bigsupermercados.entrega.repository.entrega.Clientes;
import br.com.bigsupermercados.entrega.repository.entrega.EnderecoRepository;
import br.com.bigsupermercados.entrega.service.CadastroClienteService;
import br.com.bigsupermercados.entrega.service.exception.RegistroJaCadastradoException;

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

	@GetMapping("/buscaPorCPF")
	public ResponseEntity<ClienteDTO> buscarPorCPF(@RequestParam("filtro") String cpf) {
		Optional<Cliente> clienteOpt = cadastroClienteService.buscaPorCPF(cpf);

		if (clienteOpt.isPresent()) {
			return ResponseEntity.ok(new ClienteDTO(clienteOpt.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@GetMapping("/buscaPorNome")
	public ResponseEntity<List<ClienteDTO>> buscaPorNome(@RequestParam("filtro") String nome) {
		List<Cliente> clientes = cadastroClienteService.buscaPorNome(nome);

		if (!clientes.isEmpty()) {
			return ResponseEntity.ok(ClienteDTO.converter(clientes));
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

		try {
			cadastroClienteService.salvar(cliente);
		} catch (RegistroJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return nova(clienteForm);
		}

		attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso");
		return new ModelAndView("redirect:/cliente/nova");
	}
}
