package br.com.bigsupermercados.entrega.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

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

import br.com.bigsupermercados.entrega.modelo.entrega.Loja;
import br.com.bigsupermercados.entrega.service.CadastroLojaService;

@Controller
@RequestMapping("/loja")
public class LojaController {

	@Autowired
	private CadastroLojaService service;

	@GetMapping
	public ModelAndView listar(@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		ModelAndView mv = new ModelAndView("loja/PesquisaLoja");

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(20);

		Page<Loja> clientePage = service.buscarPaginado(PageRequest.of(currentPage - 1, pageSize));
		mv.addObject("lojas", clientePage);

		int totalPages = clientePage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			mv.addObject("pageNumbers", pageNumbers);
		}

		return mv;
	}

	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Loja loja) {
		ModelAndView mv = nova(loja);
		mv.addObject("loja", loja);

		return mv;
	}

	@GetMapping("/nova")
	public ModelAndView nova(Loja loja) {
		ModelAndView mv = new ModelAndView("loja/CadastroLoja");
		return mv;
	}

	@PostMapping("/nova")
	public ModelAndView salvar(@Valid Loja loja, BindingResult result, Model model, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return nova(loja);
		}

		service.salvar(loja);
		attributes.addFlashAttribute("mensagem", "Loja salva com sucesso");

		return new ModelAndView("redirect:/loja/nova");
	}
}
