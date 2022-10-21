package dev.palhano.velsis.hotel.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Hospede implements Comparable<Hospede> {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nome;
	private String documento;
	private String telefone;
	@Column(columnDefinition = "boolean default false",nullable = false)
	private boolean estaHospedado;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "hospede")
	private List<CheckIn> checkIns;
	@Override
	public String toString() {
		return "Hospede [id=" + id + ", nome=" + nome + ", documento=" + documento + ", telefone=" + telefone
				+  "]";
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	@Override
	public int compareTo(Hospede o) {
		return this.getNome().toLowerCase().compareTo(o.getNome().toLowerCase());
	}
	public boolean isEstaHospedado() {
		return estaHospedado;
	}
	public void setEstaHospedado(boolean estaHospedado) {
		this.estaHospedado = estaHospedado;
	}
	
	
	
}
