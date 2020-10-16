package br.com.bigsupermercados.entrega.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.bigsupermercados.entrega.controller.dto.GuiaDTO;
import br.com.bigsupermercados.entrega.controller.filter.GuiaFilter;
import br.com.bigsupermercados.entrega.controller.form.GuiaForm;
import br.com.bigsupermercados.entrega.controller.form.GuiaLiberarForm;
import br.com.bigsupermercados.entrega.modelo.entrega.Guia;
import br.com.bigsupermercados.entrega.modelo.entrega.Loja;
import br.com.bigsupermercados.entrega.modelo.entrega.Motorista;
import br.com.bigsupermercados.entrega.repository.entrega.Clientes;
import br.com.bigsupermercados.entrega.repository.entrega.GuiaRepository;
import br.com.bigsupermercados.entrega.repository.entrega.Lojas;
import br.com.bigsupermercados.entrega.repository.entrega.Motoristas;
import br.com.bigsupermercados.entrega.service.GuiaService;

@Controller
@RequestMapping("/guia")
public class GuiaController {

	@Autowired
	private GuiaService service;

	@Autowired
	private Clientes clienteRepository;

	@Autowired
	private Lojas lojaRepository;

	@Autowired
	private GuiaRepository repository;

	@Autowired
	private Motoristas motoristaRepository;

	@GetMapping("/nova")
	public ModelAndView nova(GuiaForm guiaForm) {
		ModelAndView mv = new ModelAndView("guia/EmitirGuia");
		List<Loja> lojas = lojaRepository.findAll();

		mv.addObject("lojas", lojas);
		return mv;
	}

	@PostMapping("/nova")
	public ModelAndView salvar(@Valid GuiaForm form, BindingResult result, Model model, RedirectAttributes attributes) {

		Optional<Guia> guiaOpt = repository.buscarCupom(form.getData(), form.getCodigoLoja(), form.getPdv(),
				form.getCupom(), form.getValor());

		if (result.hasErrors()) {
			return nova(form);
		}

		if (guiaOpt.isPresent()) {
			model.addAttribute("erro",
					"Guia de entregas não lançada, pois já existe esse cupom fiscal já foi lançado anteriormente");
			return nova(form);
		}

		Guia guia = form.converter(clienteRepository, lojaRepository);

		service.salvar(guia);
		attributes.addFlashAttribute("mensagem", "Guia lançada com sucesso");

		return new ModelAndView("redirect:/guia/nova");
	}

	@GetMapping("/liberar")
	public ModelAndView listaGuiasALiberar(GuiaLiberarForm guiaLiberarForm) {
		ModelAndView mv = new ModelAndView("guia/LiberarGuia");
		List<Motorista> motoristas = motoristaRepository.findByAtivoTrue();
		List<Guia> guiasALiberar = repository.findByMotoristaIsNullAndExcluidoFalse();
		mv.addObject("guiasALiberar", guiasALiberar);
		mv.addObject("motoristas", motoristas);
		return mv;
	}

	@PostMapping("/liberar")
	public ModelAndView liberarGuias(@Valid GuiaLiberarForm guiaLiberarForm, BindingResult result, Model model,
			RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return listaGuiasALiberar(guiaLiberarForm);
		}

		service.liberarGuia(guiaLiberarForm);
		attributes.addFlashAttribute("mensagem", "Guias vinculadas com sucesso");

		return new ModelAndView("redirect:/guia/liberar");
	}

	@PatchMapping("/tiraCupomBordero/{codigo}")
	@Transactional
	public ResponseEntity<?> eliminarCupomBordero(@PathVariable(value = "codigo") Guia guia) {
		service.eliminarCupomBordero(guia);

		return ResponseEntity.ok("Excluido");
	}

	@GetMapping
	public ModelAndView pesquisa(GuiaFilter guiaFilter, BindingResult result, HttpServletRequest httpServletRequest,
			@PageableDefault(sort = "codigo", direction = Direction.DESC) Pageable paginacao) {
		ModelAndView mv = new ModelAndView("guia/PesquisaGuia");
		List<Loja> lojas = lojaRepository.findAll();
		Page<Guia> guias = Page.empty();

		if (guiaFilter != null) {
			guias = service.buscar(guiaFilter, paginacao);
		}

		mv.addObject("guias", GuiaDTO.converter(guias));
		mv.addObject("lojas", lojas);
		return mv;
	}

	@PatchMapping("/excluir/{codigo}")
	@Transactional
	public ResponseEntity<?> excluir(@PathVariable("codigo") Guia guia) {
		if (guia.getBordero() != null) {
			return ResponseEntity.badRequest()
					.body("Impossível excluir cupom fiscal. Ele já faz parte do borderô "
							+ guia.getBordero().getCodigo()
							+ ". Caso queira eliminar, desvincule do borderô e refaça o processo.");
		}

		guia.setExcluido(true);
		return ResponseEntity.ok(GuiaDTO.converter(guia));
	}
}