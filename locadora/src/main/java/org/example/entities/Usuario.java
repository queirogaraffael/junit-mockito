package org.example.entities;

import java.util.Objects;

public class Usuario {

	private String nome;
	private String cpf;
	
	public Usuario() {
	}
	
	public Usuario(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Usuario usuario = (Usuario) o;
		return Objects.equals(cpf, usuario.cpf);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(cpf);
	}
}