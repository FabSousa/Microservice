package br.com.fiap.mspedidos.dto;

import br.com.fiap.mspedidos.model.ItemDoPedido;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ItemDoPedidoDTO {

    private Long id;

    @NotNull
    @Positive
    private Integer quantidade;

    private String descricao;

    public ItemDoPedidoDTO(Long id, Integer quantidade, String descricao) {
        this.id = id;
        this.quantidade = quantidade;
        this.descricao = descricao;
    }

    public ItemDoPedidoDTO(ItemDoPedido entity) {
        id = entity.getId();
        quantidade = entity.getQuantidade();
        descricao = entity.getDescricao();
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
