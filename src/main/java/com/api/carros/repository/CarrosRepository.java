package com.api.carros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.carros.entity.Carro;

public interface CarrosRepository extends JpaRepository<Carro, Long>{

	Iterable<Carro> getByTipo(String tipo);



}
