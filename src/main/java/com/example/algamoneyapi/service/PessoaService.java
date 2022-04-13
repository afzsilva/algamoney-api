package com.example.algamoneyapi.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamoneyapi.model.Pessoa;
import com.example.algamoneyapi.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	public Pessoa atualizar(Long codigo,Pessoa pessoa) {
		Optional<Pessoa> optPessoa = pessoaRepository.findById(codigo);
		if (!optPessoa.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}		
		
		BeanUtils.copyProperties(pessoa, optPessoa.get(),"codigo");
		pessoaRepository.save(optPessoa.get());
		
		return optPessoa.get();
		
	}
	
	
	
}
