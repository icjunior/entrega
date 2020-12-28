package br.com.bigsupermercados.entrega.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bigsupermercados.entrega.controller.dto.LancamentoBorderoDTO;
import br.com.bigsupermercados.entrega.controller.form.LancamentoBorderoForm;
import br.com.bigsupermercados.entrega.modelo.entrega.LancamentoBordero;
import br.com.bigsupermercados.entrega.service.EliminaLancamentoBorderoService;
import br.com.bigsupermercados.entrega.service.LancamentoBorderoService;

@CrossOrigin
@RestController
@RequestMapping("/lancamentoBordero")
public class LancamentoBorderoController {

	@Autowired
	private LancamentoBorderoService services;
	
	@Autowired
	private EliminaLancamentoBorderoService eliminaLancamentoBorderoService;

	@GetMapping("/lancamento")
	public ResponseEntity<List<LancamentoBorderoDTO>> listarPorBordero(@PathVariable Long bordero) {
		List<LancamentoBordero> lancamentos = services.buscarPorBordero(bordero);

		return ResponseEntity.ok(LancamentoBorderoDTO.converter(lancamentos));
	}

	@PostMapping("/lancar/{bordero}")
	public ResponseEntity<?> lancar(@PathVariable Long bordero, @RequestBody List<LancamentoBorderoForm> form) {
		List<LancamentoBordero> lancamentos = services.gravar(bordero, form);

		return ResponseEntity.ok(LancamentoBorderoDTO.converter(lancamentos));
	}
	
	@DeleteMapping("/eliminaLancamento/{codigo}")
	public ResponseEntity<String> eliminaLancamento(@PathVariable Long codigo) {
		eliminaLancamentoBorderoService.eliminaLancamento(codigo);
		
		return ResponseEntity.ok("Excluido");
	}
}
