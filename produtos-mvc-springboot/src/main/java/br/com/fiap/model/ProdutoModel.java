package br.com.fiap.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;

public class ProdutoModel {
	private Long id;
	private double preco;
	private String nome, sku, descricao, caracteristica;
	
	public ProdutoModel() {
		super();
	}
	
	public ProdutoModel(Long id, double preco, String nome, String sku, String descricao, String caracteristica) {
		super();
		this.id = id;
		this.preco = preco;
		this.nome = nome;
		this.sku = sku;
		this.descricao = descricao;
		this.caracteristica = caracteristica;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@DecimalMin(value = "0.1", message = "Preço deve ser acima de 0.0")
	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	@Size(min = 2, max = 40, message = "Nome deve ter no minimo 2 e no maximo 40 caracteres")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Size(min = 2, max = 8, message = "SKU deve conter 8 caracteres")
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}
	
	@Size(min = 1, max = 200, message = "Descrção deve ter no minimo 1 e no maximo 200 caracteres")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Size(min = 1, max = 200, message = "Caracteristicas deve ter no minimo 1 e no maximo 200 caracteres")
	public String getCaracteristica() {
		return caracteristica;
	}

	public void setCaracteristica(String caracteristica) {
		this.caracteristica = caracteristica;
	}

}
