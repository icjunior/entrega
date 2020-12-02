package br.com.bigsupermercados.entrega.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.bigsupermercados.entrega.controller.dto.EnderecoDTO;
import br.com.bigsupermercados.entrega.modelo.entrega.Endereco;
import br.com.bigsupermercados.entrega.service.EnderecoService;
import br.com.bigsupermercados.entrega.service.exception.RegistroJaCadastradoException;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	private EnderecoService service;

	@GetMapping
	public ResponseEntity<EnderecoDTO> findByCEP(@RequestParam String cep) {
		Optional<Endereco> enderecoOpt = service.findByCEP(cep);

		if (enderecoOpt.isPresent()) {
			return ResponseEntity.ok(EnderecoDTO.converter(enderecoOpt.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@GetMapping("/pesquisaEnderecoPorLogradouro")
	public ResponseEntity<List<EnderecoDTO>> findByLogradouro(@RequestParam String logradouro,
			@RequestParam(defaultValue = "8928") Long cidade) {
		List<Endereco> enderecos = service.findByLogradouro(logradouro, cidade);

		if (enderecos.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(EnderecoDTO.converter(enderecos));
	}

	@GetMapping("/cadastro")
	public ModelAndView findAll(@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		ModelAndView mv = new ModelAndView("endereco/PesquisaEnderecoPadrao");

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(20);

		Page<Endereco> clientePage = service.buscarPaginado(PageRequest.of(currentPage - 1, pageSize));
		mv.addObject("enderecos", clientePage);

		int totalPages = clientePage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			mv.addObject("pageNumbers", pageNumbers);
		}

		return mv;
	}

	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Endereco endereco) {
		ModelAndView mv = cadastro(endereco);
		mv.addObject("endereco", endereco);
		return mv;
	}

	@GetMapping("/novo")
	public ModelAndView cadastro(Endereco endereco) {
		ModelAndView mv = new ModelAndView("endereco/CadastroEndereco");
		return mv;
	}

	@PostMapping({ "/novo", "{\\+d}" })
	public ModelAndView salvar(Endereco endereco, BindingResult result, Model model, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return cadastro(endereco);
		}

		try {
			service.salvar(endereco);
		} catch (RegistroJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return cadastro(endereco);
		}

		attributes.addFlashAttribute("mensagem", "Endereço salvo com sucesso");
		return new ModelAndView("redirect:/endereco/novo");
	}
}
