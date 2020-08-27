package br.com.bigsupermercados.entrega.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bigsupermercados.entrega.controller.dto.EnderecoDTO;
import br.com.bigsupermercados.entrega.modelo.entrega.Endereco;
import br.com.bigsupermercados.entrega.service.EnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	private EnderecoService service;

	@GetMapping("/{cep}")
	public ResponseEntity<EnderecoDTO> findByCEP(@PathVariable String cep) {
		Optional<Endereco> enderecoOpt = service.findByCEP(cep);

		if (enderecoOpt.isPresent()) {
			return ResponseEntity.ok(EnderecoDTO.converter(enderecoOpt.get()));
		}

		return ResponseEntity.notFound().build();
	}

}
