package br.com.bigsupermercados.entrega.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bigsupermercados.entrega.controller.dto.LancamentoBorderoDTO;
import br.com.bigsupermercados.entrega.controller.form.LancamentoBorderoForm;
import br.com.bigsupermercados.entrega.modelo.entrega.LancamentoBordero;
import br.com.bigsupermercados.entrega.service.LancamentoBorderoService;

@RestController
@RequestMapping("/lancamentoBordero")
public class LancamentoBorderoController {

	@Autowired
	private LancamentoBorderoService services;

	@GetMapping("/busca/{bordero}")
	public ResponseEntity<List<LancamentoBorderoDTO>> listarPorBordero(@PathVariable Long bordero) {
		List<LancamentoBordero> lancamentos = services.buscarPorBordero(bordero);

		return ResponseEntity.ok(LancamentoBorderoDTO.converter(lancamentos));
	}

	@PostMapping("/lancar")
	public ResponseEntity<?> lancar(@RequestBody List<LancamentoBorderoForm> form) {
		services.gravar(form);
		
		return ResponseEntity.ok("Gravado");
	}
}
