package br.com.fiap.fabio89291.fabio89291.modelFabio89291;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "tb_item_do_pedido_fabio89291")
public class ItemDoPedidoFabio89291 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Campo requerido")
    @Positive(message = "o valor deve ser um numero positivo")
    private Integer quantidade;
    @NotNull(message = "Campo requerido")
    private String descricao;
    //Relacionamento
    @ManyToOne(optional = false)
    @JoinColumn(name = "pedido_id")
    private PedidoFabio89291 pedido;


    public ItemDoPedidoFabio89291() {
    }

    public ItemDoPedidoFabio89291(Long id, @NotNull(message = "Campo requerido") Integer quantidade, @NotNull(message = "Campo requerido") String descricao) {
        this.id = id;
        this.quantidade = quantidade;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public PedidoFabio89291 getPedido() {
        return pedido;
    }
}
