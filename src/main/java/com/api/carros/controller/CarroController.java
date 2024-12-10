package com.api.carros.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.carros.entity.Carro;
import com.api.carros.entity.CarroDto;
import com.api.carros.services.CarrosServices;

@RestController
@RequestMapping("/api/v1/carros")
public class CarroController {

	@Autowired
	private CarrosServices carrosServices;

	@GetMapping(produces = "application/json")
	public ResponseEntity getCarroDto() {

		return ResponseEntity.ok(carrosServices.getCarros());

	}

	@GetMapping("/{id}")
	public ResponseEntity getById(@PathVariable("id") Long id) {

		Optional<Carro> carro = carrosServices.getCarroById(id);

		/*
		 * if padrão ------------------------------------------
		 * 
		 * if(carro.isPresent()) {
		 * 
		 * return ResponseEntity.ok(carro.get()); } else { return
		 * ResponseEntity.noContent().build(); }
		 */

		// if ternario ------------------------------------------
		/*
		 * return carro.isPresent() ? ResponseEntity.ok(carro.get()) :
		 * ResponseEntity.notFound().build();
		 */

		// lambdas ----------------------------------------------------

		return carro.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

	}

	@GetMapping("/tipos/{tipo}")
	public ResponseEntity getCarroByTipo(@PathVariable("tipo") String tipo) {

		List<CarroDto> carros = carrosServices.getCarroByTipo(tipo);

		return carros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(carros);

	}

	@PostMapping
	public String post(@RequestBody Carro carro) {
		Carro c = carrosServices.insertCarro(carro);
		return "Carro inserido com Sucesso" + c.getId();
	}

	@PutMapping("/{id}")
	public String putCarro(@PathVariable("id") Long id, @RequestBody Carro carro) {
		Carro c = carrosServices.update(carro, id);
		return " Carro atualizado com Sucesso" + c.getId();
	}

	@DeleteMapping("/{id}")
	public String deleteCarro(@PathVariable("id") Long id) {
		carrosServices.delete(id);

		return "Carro deletado com Sucesso";
	}

}
