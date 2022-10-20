package dev.palhano.velsis.hotel.entity.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

public class HospedeForm {
	@NotNull
	@NotBlank
	private String nome;
	@NotNull
	@NotBlank
	private String documento;
	private String telefone;
	@Override
	public String toString() {
		return "HospedeForm [nome=" + nome + ", documento=" + documento + ", telefone=" + telefone + "]";
	}
	public String getNome() {
		return nome;
	}
	public String getDocumento() {
		return documento;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
}
