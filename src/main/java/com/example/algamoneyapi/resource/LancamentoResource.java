package com.example.algamoneyapi.resource;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoneyapi.exceptionhandler.AlgamoneyExcetionHandler.Erro;
import com.example.algamoneyapi.model.Lancamento;
import com.example.algamoneyapi.service.LancamentoService;
import com.example.algamoneyapi.service.exception.PessoaInexistenteOuInativaException;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@Autowired
	private MessageSource messageSource;
	
	
	public ResponseEntity<List<Lancamento>> listar(){
		return ResponseEntity.ok(lancamentoService.listar());
	}
		
	@GetMapping("/{codigo}")
	public ResponseEntity<Lancamento> buscarPorId(@Valid @PathVariable Long codigo){		
		return ResponseEntity.ok(lancamentoService.buscarPorId(codigo));
	}
	
	@PostMapping
	public ResponseEntity<Lancamento> criarLancamento(@Valid @RequestBody Lancamento lancamento){
		Lancamento lancamentoSalvo = lancamentoService.salvar(lancamento);
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
	}
	
	
	@ExceptionHandler({PessoaInexistenteOuInativaException.class})
	public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex){
		
		String mensagemUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null,LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();	
		
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));	
		
		return ResponseEntity.badRequest().body(erros);
	}
	
}
