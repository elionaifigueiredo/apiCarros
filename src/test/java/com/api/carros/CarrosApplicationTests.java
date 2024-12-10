package com.api.carros;

import java.io.Serial;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.carros.entity.Carro;
import com.api.carros.services.CarrosServices;

@SpringBootTest
class CarrosApplicationTests {
	
	@Autowired
	private CarrosServices  services;

	@Test
	void contextLoads() {
		
		Carro carro = new Carro();

		carro.setNome("ONIX");
		carro.setTipo("esportivos");
		
		services.insertCarro(carro);
	}

}
