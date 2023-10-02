package br.com.fiap.mspedidos.service;

import br.com.fiap.mspedidos.dto.ItemDoPedidoDTO;
import br.com.fiap.mspedidos.dto.PedidoDTO;
import br.com.fiap.mspedidos.dto.StatusDTO;
import br.com.fiap.mspedidos.model.ItemDoPedido;
import br.com.fiap.mspedidos.model.Pedido;
import br.com.fiap.mspedidos.model.Status;
import br.com.fiap.mspedidos.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Transactional(readOnly = true)
    public List<PedidoDTO> findAll() {
        List<Pedido> list = repository.findAll();

        return list.stream().map(x -> new PedidoDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PedidoDTO findById(Long id){
        Pedido pedido = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Recurso não encontrado")
        ) ;

        return new PedidoDTO(pedido);

    }

    @Transactional
    public PedidoDTO insert(PedidoDTO dto){
        Pedido pedido = new Pedido();
        copyDtoToEntity(dto, pedido);
        pedido = repository.save(pedido);
        return new PedidoDTO(pedido);
    }

    @Transactional
    public void aprovarPagamentoPedido (Long id){
        Pedido pedido = repository.getByIdWithItems(id);
        if(pedido == null){
            throw new EntityNotFoundException("Recurso não encontrado");
        }

        pedido.setStatus(Status.PAGO);
        repository.updateStatus(Status.PAGO, pedido);
    }

    public PedidoDTO uodateStatus(Long id, StatusDTO statusDTO){
        Pedido pedido = repository.getByIdWithItems(id);
        if(pedido == null){
            throw new EntityNotFoundException("Recurso não encontrado");
        }

        pedido.setStatus(statusDTO.getStatus());
        repository.updateStatus(statusDTO.getStatus(), pedido);
        return new PedidoDTO(pedido);
    }

    private void copyDtoToEntity(PedidoDTO dto, Pedido entity){
        entity.setDataHora(LocalDateTime.now());
        entity.setStatus(Status.REALIZADO);
        List<ItemDoPedido> itens = new ArrayList<>();
        for (ItemDoPedidoDTO item : dto.getItens()){
            ItemDoPedido itemDoPedido = new ItemDoPedido();
            itemDoPedido.setDescricao(item.getDescricao());
            itemDoPedido.setQuantidade(item.getQuantidade());
            itemDoPedido.setPedido(entity);
            itens.add(itemDoPedido);
        }
        entity.setItens(itens);
    }

}
