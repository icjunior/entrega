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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.bigsupermercados.entrega.controller.form.TipoLancamentoForm;
import br.com.bigsupermercados.entrega.modelo.entrega.TipoLancamento;
import br.com.bigsupermercados.entrega.service.CadastroTipoLancamentoService;
import br.com.bigsupermercados.entrega.service.SelecaoTipoLancamentoService;
import groovyjarjarpicocli.CommandLine.Model;

@Controller
@RequestMapping("/tipoLancamento")
public class TipoLancamentoController {

	@Autowired
	private CadastroTipoLancamentoService service;

	@Autowired
	private SelecaoTipoLancamentoService selecaoTipoLancamentoService;

	@GetMapping
	public ModelAndView listar(@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		ModelAndView mv = new ModelAndView("tipoLancamento/PesquisaTipoLancamento");

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(20);

		Page<TipoLancamento> clientePage = selecaoTipoLancamentoService
				.buscarPaginado(PageRequest.of(currentPage - 1, pageSize));
		mv.addObject("tiposLancamento", clientePage);

		int totalPages = clientePage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			mv.addObject("pageNumbers", pageNumbers);
		}

		return mv;
	}

	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") TipoLancamento tipoLancamento) {
		TipoLancamentoForm tipoLancamentoForm = tipoLancamento.converter();

		ModelAndView mv = novo(tipoLancamentoForm);
		mv.addObject("tipoLancamentoForm", tipoLancamentoForm);

		return mv;
	}

	@GetMapping("/novo")
	public ModelAndView novo(TipoLancamentoForm tipoLancamentoForm) {
		ModelAndView mv = new ModelAndView("tipoLancamento/CadastroTipoLancamento");
		return mv;
	}

	@PostMapping("/novo")
	public ModelAndView salvar(@Valid TipoLancamentoForm tipoLancamentoForm, BindingResult result, Model model,
			RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return novo(tipoLancamentoForm);
		}

		TipoLancamento tipoLancamento = tipoLancamentoForm.converter();

		service.salvar(tipoLancamento);
		attributes.addFlashAttribute("mensagem", "Tipo de lançamento salvo com sucesso");

		return new ModelAndView("redirect:/tipoLancamento/novo");
	}
}
