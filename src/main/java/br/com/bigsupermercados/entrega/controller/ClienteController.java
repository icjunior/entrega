package br.com.bigsupermercados.entrega.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.bigsupermercados.entrega.controller.dto.ClienteDTO;
import br.com.bigsupermercados.entrega.controller.form.ClienteForm;
import br.com.bigsupermercados.entrega.modelo.entrega.Cidade;
import br.com.bigsupermercados.entrega.modelo.entrega.Cliente;
import br.com.bigsupermercados.entrega.repository.entrega.CidadeRepository;
import br.com.bigsupermercados.entrega.repository.entrega.EnderecoRepository;
import br.com.bigsupermercados.entrega.service.CadastroClienteService;
import br.com.bigsupermercados.entrega.service.SelecaoClienteService;
import br.com.bigsupermercados.entrega.service.exception.RegistroJaCadastradoException;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private CadastroClienteService cadastroClienteService;

	@Autowired
	private SelecaoClienteService selecaoClienteService;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@GetMapping
	public ModelAndView listar(@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		ModelAndView mv = new ModelAndView("cliente/PesquisaCliente");

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(20);

		Page<Cliente> clientePage = selecaoClienteService.buscarPaginado(PageRequest.of(currentPage - 1, pageSize));
		mv.addObject("clientes", clientePage);

		int totalPages = clientePage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			mv.addObject("pageNumbers", pageNumbers);
		}

		return mv;
	}

	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Cliente cliente) {
		ClienteForm clienteForm = cliente.converter();

		ModelAndView mv = nova(clienteForm);
		mv.addObject("clienteForm", clienteForm);

		return mv;
	}

	@GetMapping("/buscaPorCPF")
	public ResponseEntity<ClienteDTO> buscarPorCPF(@RequestParam("filtro") String cpf) {
		Optional<Cliente> clienteOpt = selecaoClienteService.buscaPorCPF(cpf);

		if (clienteOpt.isPresent()) {
			return ResponseEntity.ok(new ClienteDTO(clienteOpt.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@GetMapping("/buscaPorNome")
	public ResponseEntity<List<ClienteDTO>> buscaPorNome(@RequestParam("filtro") String nome) {
		List<Cliente> clientes = selecaoClienteService.buscaPorNome(nome);

		if (!clientes.isEmpty()) {
			return ResponseEntity.ok(ClienteDTO.converter(clientes));
		}

		return ResponseEntity.notFound().build();
	}

	@GetMapping("/nova")
	public ModelAndView nova(ClienteForm clienteForm) {
		ModelAndView mv = new ModelAndView("cliente/CadastroCliente");

		List<Cidade> cidades = cidadeRepository.cidadesAtendidas();

		mv.addObject("cidades", cidades);
		return mv;
	}

	@PostMapping({ "/nova", "{\\+d}" })
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
