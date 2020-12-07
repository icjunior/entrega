package br.com.bigsupermercados.entrega.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

import br.com.bigsupermercados.entrega.modelo.entrega.Bairro;
import br.com.bigsupermercados.entrega.repository.entrega.CidadeRepository;
import br.com.bigsupermercados.entrega.service.BairroService;
import br.com.bigsupermercados.entrega.service.exception.RegistroJaCadastradoException;

@Controller
@RequestMapping("/bairro")
public class BairroController {

	@Autowired
	private BairroService service;

	@Autowired
	private CidadeRepository cidadeRepository;

	@GetMapping
	public ModelAndView lista(@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		ModelAndView mv = new ModelAndView("bairro/PesquisaBairro");

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(20);

		Page<Bairro> clientePage = service.buscarPaginado(PageRequest.of(currentPage - 1, pageSize));
		mv.addObject("bairros", clientePage);

		int totalPages = clientePage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			mv.addObject("pageNumbers", pageNumbers);
		}

		return mv;
	}

	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Bairro bairro) {
		ModelAndView mv = cadastro(bairro);
		mv.addObject("bairro", bairro);

		return mv;
	}

	@GetMapping("/novo")
	public ModelAndView cadastro(Bairro bairro) {
		ModelAndView mv = new ModelAndView("bairro/CadastroBairro");
		mv.addObject("cidades", cidadeRepository.cidadesAtendidas());
		return mv;
	}

	@PostMapping({ "/novo", "{\\+d}" })
	public ModelAndView salvar(Bairro bairro, BindingResult result, Model model, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return cadastro(bairro);
		}

		try {
			service.salvar(bairro);
		} catch (RegistroJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return cadastro(bairro);
		}

		attributes.addFlashAttribute("mensagem", "Bairro salvo com sucesso");
		return new ModelAndView("redirect:/bairro/novo");
	}

}
