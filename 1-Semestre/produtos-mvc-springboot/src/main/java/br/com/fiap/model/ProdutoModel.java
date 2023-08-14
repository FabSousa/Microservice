package br.com.fiap.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;

public class ProdutoModel {

	private Long id;
	private String nome;
	private String sku;
	private String descricao;
	private Double preco;
	private String caracteristicas;
	private CategoriaModel categoria;
	private MarcaModel marca;

	public ProdutoModel() {
		super();
	}

	public ProdutoModel(Long id, String nome, String sku, String descricao, Double preco, String caracteristicas,
			CategoriaModel categoria) {
		super();
		this.id = id;
		this.nome = nome;
		this.sku = sku;
		this.descricao = descricao;
		this.preco = preco;
		this.caracteristicas = caracteristicas;
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@Size(min=2,max=40, message = "Nome deve ter no mínimo 2 e no máximo 40 caracteres")
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Size(min = 1, max = 8, message="SKU deve conter 8 caracteres")
	public String getSku() {
		return sku;
	}
	
	public void setSku(String sku) {
		this.sku = sku;
	}

	@Size(min=1,max=200, message="Descrição deve ter no mínimo 1 e no máximo 200 caracteres")
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@DecimalMin(value="0.1", message = "Preço deve ser acima de 0.0")
	public Double getPreco() {
		return preco;
	}
	
	public void setPreco(Double preco) {
		this.preco = preco;
	}

	@Size(min=1, max = 200, message="Caracteristicas deve ter no mínimo 1 e no máximo 200 caracteres")
	public String getCaracteristicas() {
		return caracteristicas;
	}
	
	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public CategoriaModel getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaModel categoria) {
		this.categoria = categoria;
	}

	public MarcaModel getMarca() {
		return marca;
	}

	public void setMarca(MarcaModel marca) {
		this.marca = marca;
	}

}
