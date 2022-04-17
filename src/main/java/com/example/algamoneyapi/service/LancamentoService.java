package com.example.algamoneyapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamoneyapi.model.Lancamento;
import com.example.algamoneyapi.model.Pessoa;
import com.example.algamoneyapi.repository.LancamentoRepository;
import com.example.algamoneyapi.repository.PessoaRepository;
import com.example.algamoneyapi.repository.filter.LancamentoFilter;
import com.example.algamoneyapi.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	
	public List<Lancamento> pesquisar(LancamentoFilter lancamentoFilter){		
		return lancamentoRepository.filtrar(lancamentoFilter);
	}
	
	public List<Lancamento> listar(){		
		return lancamentoRepository.findAll();
	}
	
	
	public Lancamento salvar(Lancamento lancamento) {
		Pessoa pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo()).get();
		
		if (pessoa == null || pessoa.isInativo()) {
			
			throw new PessoaInexistenteOuInativaException();			
		}
		
		return lancamentoRepository.save(lancamento);
	}
		
	public Lancamento buscarPorId(Long codigo) {		
		return buscarLancamentoPeloCodigo(codigo);
	}
		
	public Lancamento cadastrarLancamento(Lancamento lancamento) {
		Lancamento lancamentoRetornado = lancamentoRepository.save(lancamento);		
		return lancamentoRetornado;
	}
		
	private Lancamento buscarLancamentoPeloCodigo(Long codigo) {
		Optional<Lancamento> optlancamento = lancamentoRepository.findById(codigo);
		if (!optlancamento.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return optlancamento.get();
	}	

}
