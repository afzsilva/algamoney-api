package com.example.algamoneyapi.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.algamoneyapi.model.Categoria;
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
	public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {		
		Pessoa pessoaSalva =  pessoaRepository.save(pessoa);		
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(pessoaSalva.getCodigo()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.ok(pessoaSalva);
	}
	
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Pessoa> buscarPeloCodigo(@PathVariable("codigo") Long codigo) {	
		
		Optional<Pessoa> optPessoa = pessoaRepository.findById(codigo); 
		
		if (optPessoa.isPresent()) {			
			return ResponseEntity.ok(pessoaRepository.findById(codigo).get());
		}else {			
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);			
		}
	}
	
	
}
