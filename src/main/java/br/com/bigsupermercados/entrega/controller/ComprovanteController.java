package br.com.bigsupermercados.entrega.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.bigsupermercados.entrega.modelo.entrega.Motorista;
import br.com.bigsupermercados.entrega.service.ComprovanteService;

@Controller
@RequestMapping("/comprovante")
public class ComprovanteController {
	
	@Autowired
	private ComprovanteService service;

	@GetMapping
	public ModelAndView emitir(Motorista motorista) {
		ModelAndView mv = new ModelAndView("/comprovante/comprovante");
		return mv;
	}
	
	@GetMapping("/emitir")
	public ResponseEntity<byte[]> geraRelatorioAuditoria() {
		byte[] relatorio = null;

		try {
			relatorio = service.gerarComprovante();
		} catch (Exception e) {
			e.printStackTrace();
		}

		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		headers.set("Content-disposition", "inline; filename=comprovante.pdf");
		return new ResponseEntity<>(relatorio, headers, HttpStatus.OK);
	}
}
