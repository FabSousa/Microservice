package br.com.fiap.msprodutos.dto;

import br.com.fiap.msprodutos.model.Produto;

import java.math.BigDecimal;

public class ProdutoCategoriaDTO {

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String caracteristica;

    private CategoriaDTO categoria;

    public ProdutoCategoriaDTO(Long id, String nome, String descricao, BigDecimal preco,
                               String caracteristica, CategoriaDTO categoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.caracteristica = caracteristica;
        this.categoria = categoria;
    }

    public ProdutoCategoriaDTO(Produto entity) {
       id = entity.getId();
       nome = entity.getNome();
       descricao = entity.getDescricao();
       preco = entity.getPreco();
       caracteristica = entity.getDescricao();
       categoria = new CategoriaDTO(entity.getCategoria());
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getCaracteristica() {
        return caracteristica;
    }

    public CategoriaDTO getCategoria() {
        return categoria;
    }
}
