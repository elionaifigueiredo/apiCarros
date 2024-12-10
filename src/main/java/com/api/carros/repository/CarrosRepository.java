package com.api.carros.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.carros.entity.Carro;

public interface CarrosRepository extends JpaRepository<Carro, Long>{

	List<Carro> getByTipo(String tipo);



}
