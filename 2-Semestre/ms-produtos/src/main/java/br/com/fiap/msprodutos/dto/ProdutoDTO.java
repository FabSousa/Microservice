package br.com.fiap.msprodutos.dto;

import br.com.fiap.msprodutos.model.Produto;

import java.math.BigDecimal;

public class ProdutoDTO {

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String caracteristica;

    private Long categoriaId;

    public ProdutoDTO(Long id, String nome, String descricao,
                      BigDecimal preco, String caracteristica, Long categoriaId) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.caracteristica = caracteristica;
        this.categoriaId = categoriaId;
    }

    public ProdutoDTO(Produto entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.descricao = entity.getDescricao();
        this.preco = entity.getPreco();
        this.caracteristica = entity.getCaracteristica();
        this.categoriaId = entity.getCategoria().getId();
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

    public Long getCategoriaId() {
        return categoriaId;
    }
}
