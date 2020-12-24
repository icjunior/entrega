package br.com.bigsupermercados.entrega.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.bigsupermercados.entrega.controller.form.RelatorioMotoristaPorLojaForm;
import br.com.bigsupermercados.entrega.modelo.entrega.Guia;
import br.com.bigsupermercados.entrega.modelo.entrega.Motorista;
import br.com.bigsupermercados.entrega.repository.entrega.Motoristas;
import br.com.bigsupermercados.entrega.service.GuiaPorMotoristaEPeriodoService;

@Controller
@RequestMapping("/relatorioMotoristaPorLoja")
public class RelatorioMotoristaPorLojaController {

	@Autowired
	private GuiaPorMotoristaEPeriodoService relatorioService;

	@Autowired
	private Motoristas motoristaRepository;

	@GetMapping
	public ModelAndView relatorioMotoristaPorLoja(RelatorioMotoristaPorLojaForm relatorioMotoristaPorLojaForm,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
		
		ModelAndView mv = new ModelAndView("/relatorios/RelatorioMotoristaPorLoja");
		List<Motorista> motoristas = motoristaRepository.findByAtivoTrue();

		mv.addObject("motoristas", motoristas);

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(20);

		Page<Guia> guiaPage = relatorioService.buscarPaginado(PageRequest.of(currentPage - 1, pageSize), relatorioMotoristaPorLojaForm);
		mv.addObject("guias", guiaPage);

		int totalPages = guiaPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			mv.addObject("pageNumbers", pageNumbers);
		}
		return mv;
	}

//	@GetMapping("/busca")
//	public ResponseEntity<byte[]> buscarDados(RelatorioMotoristaPorLojaForm form) {
//
//		byte[] relatorio = null;
//		List<Guia> guias;
//
//		try {
//			guias = relatorioService.guiaPorMotoristaEPeriodo(form);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		final HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.parseMediaType("application/pdf"));
//		headers.set("Content-disposition", "inline; filename=relatorio-motorista-por-loja.pdf");
//		return new ResponseEntity<>(relatorio, headers, HttpStatus.OK);
//
//	}
}
