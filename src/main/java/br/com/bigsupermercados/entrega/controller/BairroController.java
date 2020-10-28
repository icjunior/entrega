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

import br.com.bigsupermercados.entrega.controller.dto.BairroDTO;
import br.com.bigsupermercados.entrega.modelo.entrega.Bairro;
import br.com.bigsupermercados.entrega.service.BairroService;
import br.com.bigsupermercados.entrega.service.exception.RegistroJaCadastradoException;

@Controller
@RequestMapping("/bairro")
public class BairroController {

	@Autowired
	private BairroService service;
	
	@GetMapping
	public ModelAndView lista() {
		ModelAndView mv = new ModelAndView("bairro/PesquisaBairro");
		List<Bairro> bairros = service.lista();
		mv.addObject("bairros", BairroDTO.converter(bairros));
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

		return mv;
	}

	@PostMapping({ "/nova", "{\\+d}" })
	public ModelAndView salvar(Bairro bairro, BindingResult result, Model model,
			RedirectAttributes attributes) {

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
