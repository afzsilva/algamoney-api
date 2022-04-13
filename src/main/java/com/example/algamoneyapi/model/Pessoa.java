package com.example.algamoneyapi.model;

import java.util.Objects;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "pessoa")
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NonNull
	private String nome;
	
	@Embedded
	@AttributeOverrides(
			@AttributeOverride(name="logradouro", column = @Column(name = "logradouro"))
			)
	
	private Endereco endereco;
	
	@NotNull
	private Boolean ativo;
	
	public Boolean getAtivo() {
		return ativo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	/*
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}*/

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ativo, codigo, endereco, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(ativo, other.ativo) && Objects.equals(codigo, other.codigo)
				&& Objects.equals(endereco, other.endereco) && Objects.equals(nome, other.nome);
	}

	
		
}
