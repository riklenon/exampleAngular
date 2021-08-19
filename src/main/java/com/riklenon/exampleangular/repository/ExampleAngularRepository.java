package com.riklenon.exampleangular.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.riklenon.exampleangular.model.ExampleAngular;

public interface ExampleAngularRepository extends JpaRepository<ExampleAngular, Long> {
	
}
