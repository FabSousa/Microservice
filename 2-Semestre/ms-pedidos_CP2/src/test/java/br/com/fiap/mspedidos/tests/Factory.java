package br.com.fiap.mspedidos.tests;

import br.com.fiap.mspedidos.dto.PedidoDTO;
import br.com.fiap.mspedidos.model.ItemDoPedido;
import br.com.fiap.mspedidos.model.Pedido;
import br.com.fiap.mspedidos.model.Status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Factory {

    public static Pedido createPedido() {

        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setDataHora(LocalDateTime.now());
        pedido.setStatus(Status.CRIADO);
        List<ItemDoPedido> itens = new ArrayList<>();

        ItemDoPedido itemDoPedido = new ItemDoPedido();
        itemDoPedido.setId(1L);
        itemDoPedido.setDescricao("Livro");
        itemDoPedido.setQuantidade(2);
        itemDoPedido.setPedido(pedido);
        itens.add(itemDoPedido);

        pedido.setItens(itens);

        return pedido;

    }

    public static PedidoDTO createPedidoDTO() {
        Pedido pedido = createPedido();
        return new PedidoDTO(pedido);
    }


}
