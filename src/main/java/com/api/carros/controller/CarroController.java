package com.api.carros.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.carros.entity.Carro;
import com.api.carros.services.CarrosServices;

@RestController
@RequestMapping("/api/v1/carros")
public class CarroController {
	
	
	@Autowired
	private CarrosServices carrosServices;


	@GetMapping(produces = "application/json")
	public Iterable<Carro> get(){
		
		return carrosServices.getCarros();
	}
	
	@GetMapping("/{id}")
	public Optional<Carro> getById(@PathVariable("id") Long id){
		
		return carrosServices.getCarroById(id);
		
	}
	
	@GetMapping("/tipos/{tipo}")
	public Iterable<Carro> getByTipo(@PathVariable("tipo") String tipo) {
		return carrosServices.getCarroByTipo(tipo);
	}
	
	@PostMapping
	public String post(@RequestBody Carro carro) {
		Carro c = carrosServices.insertCarro(carro);
		return "Carro inserido com Sucesso" + c.getId();
	}
	
	@PutMapping("/{id}")
	public String putCarro(@PathVariable("id") Long id, @RequestBody Carro carro) {
		Carro c = carrosServices.update(carro , id);
		return " Carro atualizado com Sucesso" + c.getId();
	}
	
	@DeleteMapping("/{id}")
	public String deleteCarro(@PathVariable("id")Long id) {
		carrosServices.delete(id);
		
		return "Carro deletado com Sucesso";
	}
	
	
	
	
}
	
	
	



