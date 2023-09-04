package br.com.fiap.fabio89291.fabio89291.dto;

import br.com.fiap.fabio89291.fabio89291.modelFabio89291.ItemDoPedidoFabio89291;
import br.com.fiap.fabio89291.fabio89291.modelFabio89291.PedidoFabio89291;
import br.com.fiap.fabio89291.fabio89291.modelFabio89291.StatusFabio89291;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PedidoDTOFabio89291 {
    private Long id;
    @NotNull(message = "Campo requerido")
    private LocalDateTime dataHora;
    @NotNull(message = "Campo requerido")
    private StatusFabio89291 status;
    private List<ItemDoPedidoDTOFabio89291> itens = new ArrayList<>();

    public PedidoDTOFabio89291(PedidoFabio89291 entity) {
        this.id = entity.getId();
        this.dataHora = entity.getDataHora();
        this.status = entity.getStatus();
        for (ItemDoPedidoFabio89291 item : entity.getItens()) {
            itens.add(new ItemDoPedidoDTOFabio89291 (item));
        }
    }

    public PedidoDTOFabio89291(Long id, @NotNull(message = "Campo requerido") LocalDateTime dataHora, @NotNull(message = "Campo requerido") StatusFabio89291 status, List<ItemDoPedidoDTOFabio89291> itens) {
        this.id = id;
        this.dataHora = dataHora;
        this.status = status;
        this.itens = itens;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public StatusFabio89291 getStatus() {
        return status;
    }

    public List<ItemDoPedidoDTOFabio89291> getItens() {
        return itens;
    }
}
