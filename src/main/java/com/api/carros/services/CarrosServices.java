package com.api.carros.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.carros.entity.Carro;
import com.api.carros.entity.CarroDto;
import com.api.carros.repository.CarrosRepository;

@Service
public class CarrosServices {

	@Autowired
	private CarrosRepository carrosRepository;

	public List<CarroDto> getCarros() {

		return carrosRepository.findAll().
		// .map diz que a função irá chamar o Contrutor do CarroDtO passando o Carro
		// como Parametro
				stream().map(CarroDto::new).collect(Collectors.toList());

	}

	public Optional<Carro> getCarroById(Long id) {
		return carrosRepository.findById(id);
	}

	public List<CarroDto> getCarroByTipo(String tipo) {
		return carrosRepository.getByTipo(tipo).stream().map(CarroDto::new).collect(Collectors.toList());
	}

	public Carro insertCarro(Carro carro) {
		return carrosRepository.save(carro);

	}

	public Carro update(Carro carro, Long id) {

		Optional<Carro> optional = getCarroById(id);
		if (optional.isPresent()) {
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
		Optional<Carro> carro = getCarroById(id);
		if (carro.isPresent()) {
			carrosRepository.deleteById(id);
		}

	}

}
