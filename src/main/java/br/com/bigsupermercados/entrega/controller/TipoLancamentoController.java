package br.com.bigsupermercados.entrega.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.bigsupermercados.entrega.controller.dto.TipoLancamentoDTO;
import br.com.bigsupermercados.entrega.controller.form.TipoLancamentoForm;
import br.com.bigsupermercados.entrega.modelo.entrega.TipoLancamento;
import br.com.bigsupermercados.entrega.service.TipoLancamentoService;

@Controller
@RequestMapping("/tipoLancamento")
public class TipoLancamentoController {

	@Autowired
	TipoLancamentoService service;

	@GetMapping
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("tipoLancamento/PesquisaTipoLancamento");
		List<TipoLancamento> tiposLancamento = service.listar();
		mv.addObject("tiposLancamento", TipoLancamentoDTO.converter(tiposLancamento));
		return mv;
	}
	
	@GetMapping("/novo")
	public ModelAndView novo(TipoLancamentoForm tipoLancamentoForm) {
		ModelAndView mv = new ModelAndView("tipoLancamento/CadastroTipoLancamento");
		return mv;
	}

	@PostMapping("/novo")
	public ModelAndView salvar(TipoLancamentoForm tipoLancamentoForm, BindingResult result, Model model,
			RedirectAttributes attributes) {
		
		TipoLancamento tipoLancamento = tipoLancamentoForm.converter();

		if (result.hasErrors()) {
			return novo(tipoLancamentoForm);
		}

		service.salvar(tipoLancamento);
		attributes.addFlashAttribute("mensagem", "Tipo de lançamento salvo com sucesso");

		return new ModelAndView("redirect:/tipoLancamento/novo");
	}
}
