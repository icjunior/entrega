package br.com.bigsupermercados.entrega.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bigsupermercados.entrega.controller.dto.ZanM45DTO;
import br.com.bigsupermercados.entrega.modelo.zanthus.ZanM45;
import br.com.bigsupermercados.entrega.service.ZanM45Service;

@RestController
@RequestMapping("/cupom")
public class CupomController {

	@Autowired
	private ZanM45Service zanM45Service;

	@GetMapping("/busca/{data}/{loja}/{pdv}/{cupom}")
	public ResponseEntity<ZanM45DTO> buscarCupom(@PathVariable String data, @PathVariable Long loja,
			@PathVariable Integer pdv, @PathVariable Integer cupom) {

		LocalDate dataConvertida = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		Optional<ZanM45> cupomRetornado = zanM45Service.buscarCupom(dataConvertida, loja, pdv, cupom);

		if (cupomRetornado.isPresent()) {
			return ResponseEntity.ok(ZanM45DTO.converter(cupomRetornado.get()));
		}

		return ResponseEntity.notFound().build();
	}
}
