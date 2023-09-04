package br.com.fiap.fabio89291.fabio89291.dto;

import br.com.fiap.fabio89291.fabio89291.modelFabio89291.ItemDoPedidoFabio89291;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ItemDoPedidoDTOFabio89291 {

    private Long id;
    @NotNull(message = "Campo requerido")
    @Positive(message = "o valor deve ser um numero positivo")
    private Integer quantidade;
    @NotNull(message = "Campo requerido")
    private String descricao;

    public ItemDoPedidoDTOFabio89291(ItemDoPedidoFabio89291 entity) {
        this.id = entity.getId();
        this.quantidade = entity.getQuantidade();
        this.descricao = entity.getDescricao();
    }

    public ItemDoPedidoDTOFabio89291(Long id, @NotNull(message = "Campo requerido") Integer quantidade, @NotNull(message = "Campo requerido") String descricao) {
        this.id = id;
        this.quantidade = quantidade;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }
}
