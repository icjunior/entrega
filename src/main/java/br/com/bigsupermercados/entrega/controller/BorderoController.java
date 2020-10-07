package br.com.bigsupermercados.entrega.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.bigsupermercados.entrega.controller.dto.BorderoDTO;
import br.com.bigsupermercados.entrega.controller.dto.BorderoDetalheDTO;
import br.com.bigsupermercados.entrega.controller.dto.TipoLancamentoDTO;
import br.com.bigsupermercados.entrega.modelo.entrega.Bordero;
import br.com.bigsupermercados.entrega.modelo.entrega.TipoLancamento;
import br.com.bigsupermercados.entrega.service.BorderoService;
import br.com.bigsupermercados.entrega.service.TipoLancamentoService;

@Controller
@RequestMapping("/bordero")
public class BorderoController {

	@Autowired
	private BorderoService service;

	@Autowired
	private TipoLancamentoService tipoLancamentoService;

	@GetMapping
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("bordero/PesquisaBordero");

		List<Bordero> borderos = service.listar();

		mv.addObject("borderos", BorderoDTO.converter(borderos));
		return mv;
	}

	@GetMapping("/fechados")
	public ModelAndView listarFechados() {
		ModelAndView mv = new ModelAndView("bordero/PesquisaFechados");
		List<Bordero> borderos = service.listaFechados();
		mv.addObject("borderos", BorderoDTO.converter(borderos));
		return mv;
	}

	@GetMapping("/edita/{codigoBordero}")
	public ModelAndView pesquisaPorBodero(@PathVariable Long codigoBordero) {
		ModelAndView mv = new ModelAndView("bordero/DetalheBordero");
		Optional<Bordero> bordero = service.buscarBordero(codigoBordero);
		List<TipoLancamento> tiposLancamento = tipoLancamentoService.listar();

		mv.addObject("bordero", BorderoDetalheDTO.converter(bordero));
		mv.addObject("tiposLancamento", TipoLancamentoDTO.converter(tiposLancamento));

		return mv;
	}

	@PutMapping("/reabrir/{codigo}")
	public ResponseEntity<?> reabrir(@PathVariable Long codigo) {
		service.reabrir(codigo);
		return ResponseEntity.ok("Reaberto");
	}

	@PutMapping("/fechar/{codigo}")
	public ResponseEntity<BorderoDTO> fechar(@PathVariable Long codigo) {
		Bordero bordero = service.fechar(codigo);

		return ResponseEntity.ok(new BorderoDTO(bordero));
	}
}
