package com.example.algamoneyapi.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoneyapi.model.Pessoa;
import com.example.algamoneyapi.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoa")
public class PessoaResource {
	
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	
	@GetMapping
	public ResponseEntity<List<Pessoa>> listar() {		
		return ResponseEntity.ok(pessoaRepository.findAll());
	}
	
	
	@PostMapping
	public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa) {		
		return ResponseEntity.ok(pessoaRepository.save(pessoa));
	}
}
