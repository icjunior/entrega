package br.com.bigsupermercados.entrega.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.bigsupermercados.entrega.controller.dto.CidadeDTO;
import br.com.bigsupermercados.entrega.modelo.entrega.Cidade;
import br.com.bigsupermercados.entrega.service.CidadeService;
import br.com.bigsupermercados.entrega.service.exception.RegistroJaCadastradoException;

@Controller
@RequestMapping("/cidade")
public class CidadeController {

	@Autowired
	private CidadeService service;

	@GetMapping
	public ModelAndView lista() {
		ModelAndView mv = new ModelAndView("cidade/PesquisaCidade");
		List<Cidade> cidades = service.lista();
		mv.addObject("cidades", CidadeDTO.converter(cidades));
		return mv;
	}

	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Cidade cidade) {
		ModelAndView mv = cadastro(cidade);
		mv.addObject("cidade", cidade);

		return mv;
	}

	@GetMapping("/nova")
	public ModelAndView cadastro(Cidade cidade) {
		ModelAndView mv = new ModelAndView("cidade/CadastroCidade");

		return mv;
	}

	@PostMapping({ "/nova", "{\\+d}" })
	public ModelAndView salvar(Cidade cidade, BindingResult result, Model model,
			RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return cadastro(cidade);
		}

		try {
			service.salvar(cidade);
		} catch (RegistroJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return cadastro(cidade);
		}

		attributes.addFlashAttribute("mensagem", "Cidade salva com sucesso");
		return new ModelAndView("redirect:/cidade/nova");
	}

}
