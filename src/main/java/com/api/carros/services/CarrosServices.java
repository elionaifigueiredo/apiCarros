package com.api.carros.services;


import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.carros.entity.Carro;
import com.api.carros.repository.CarrosRepository;

@Service
public class CarrosServices {

	
	@Autowired
	private CarrosRepository carrosRepository;
	
		
	public  Iterable<Carro> getCarros(){
		return carrosRepository.findAll();
	}



	public Optional<Carro> getCarroById(Long id) {
		return carrosRepository.findById(id);
	}



	public Iterable<Carro> getCarroByTipo(String tipo) {
		return carrosRepository.getByTipo(tipo);
	}



	public Carro insertCarro(Carro carro) {
		return carrosRepository.save(carro);
		
	}



	public Carro update(Carro carro, Long id) {
		
		Optional<Carro> optional = getCarroById(id);
		if(optional.isPresent()) {
			  Carro db = optional.get();
			  db.setNome(carro.getNome());
			  db.setTipo(carro.getTipo());
			  System.out.println("Carro ID" + db.getId());
			  carrosRepository.save(db);
			  
			  return db;
		
		}
		
		else {
			throw new RuntimeException("Não foi possivel atualizar ");
		}

	}



	public void delete(Long id) {
		Optional<Carro> carro  = getCarroById(id);
		if(carro.isPresent()) {
			carrosRepository.deleteById(id);
		}
		
		
	}


	
}
