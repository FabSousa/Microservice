package br.com.fiap.fabio89291.fabio89291.modelFabio89291;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_pedido_fabio89291")
public class PedidoFabio89291 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @NotNull(message = "Campo requerido")
    private LocalDateTime dataHora;
    @NotNull(message = "Campo requerido")
    @Enumerated(EnumType.STRING)
    private StatusFabio89291 status;
    //Relacionamento
    @OneToMany(cascade=CascadeType.PERSIST, mappedBy="pedido")
    private List<ItemDoPedidoFabio89291> itens = new ArrayList<>();

    public PedidoFabio89291() {
    }

    public PedidoFabio89291(Long id, LocalDateTime dataHora, StatusFabio89291 status) {
        this.id = id;
        this.dataHora = dataHora;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public StatusFabio89291 getStatus() {
        return status;
    }

    public void setStatus(StatusFabio89291 status) {
        this.status = status;
    }

    public List<ItemDoPedidoFabio89291> getItens() {
        return itens;
    }
}
