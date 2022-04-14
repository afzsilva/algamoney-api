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
		Pessoa pessoaRetornada = buscarPessoaPeloCodigo(codigo);		
		
		BeanUtils.copyProperties(pessoa, pessoaRetornada,"codigo");
		pessoaRepository.save(pessoaRetornada);
		
		return pessoaRetornada;
		
	}


	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
			Pessoa pessoa = buscarPessoaPeloCodigo(codigo);
			pessoa.setAtivo(ativo);
			pessoaRepository.save(pessoa);
		
	}
	
	
	private Pessoa buscarPessoaPeloCodigo(Long codigo) {
		Optional<Pessoa> optPessoa = pessoaRepository.findById(codigo);
		if (!optPessoa.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return optPessoa.get();
	}
	
}
