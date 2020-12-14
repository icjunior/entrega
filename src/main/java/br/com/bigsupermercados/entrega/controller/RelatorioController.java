package br.com.bigsupermercados.entrega.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.bigsupermercados.entrega.modelo.entrega.Bordero;
import br.com.bigsupermercados.entrega.service.HoleriteService;

@Controller
@RequestMapping("/relatorio")
public class RelatorioController {
	
	@Autowired
	private HoleriteService holeriteService;

	@GetMapping("/holerite")
	public ResponseEntity<byte[]> holerite(@RequestParam("codigoBordero") Bordero bordero){
		byte[] relatorio = null;
		
		try {
			relatorio = holeriteService.gerar(bordero);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		headers.set("Content-disposition", "inline; filename=bordero_" + bordero.getCodigo() + ".pdf");
		return new ResponseEntity<>(relatorio, headers, HttpStatus.OK);
	}
}
