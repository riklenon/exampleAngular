package com.riklenon.exampleangular.rest;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.riklenon.exampleangular.model.ExampleAngular;
import com.riklenon.exampleangular.repository.ExampleAngularRepository;

@RestController
@RequestMapping("/api/jogadores")
@CrossOrigin("*")
public class ExampleController {
	@Autowired
	private ExampleAngularRepository repository;
	
	@PostMapping
	public ExampleAngular save(@RequestBody ExampleAngular exampleAngular) {
		return repository.save(exampleAngular);
	}
	
	@GetMapping("{id}")
	public ExampleAngular getById (@PathVariable Long id) {
		return repository.findById(id)
						 .orElseThrow (()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping
	public List<ExampleAngular> getAll(){
		return repository.findAll();
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
	
	
	@PatchMapping("{id}/done")
	public ExampleAngular concluir(@PathVariable Long id) {
		return repository.findById(id).map(exampleAngular ->{
			exampleAngular.setDone(true);
			exampleAngular.setDataConclusao(LocalDateTime.now());
			repository.save(exampleAngular);
			return exampleAngular;
		}).orElse(null);
	}
}
