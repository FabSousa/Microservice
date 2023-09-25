package br.com.fiap.mspedidos.service;

import br.com.fiap.mspedidos.dto.ItemDoPedidoDTO;
import br.com.fiap.mspedidos.dto.PedidoDTO;
import br.com.fiap.mspedidos.model.ItemDoPedido;
import br.com.fiap.mspedidos.model.Pedido;
import br.com.fiap.mspedidos.model.Status;
import br.com.fiap.mspedidos.repository.PedidoRepository;
import br.com.fiap.mspedidos.service.exceptions.DatabaseException;
import br.com.fiap.mspedidos.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    public List<PedidoDTO> findAll(){
        List<Pedido> list = repository.findAll();

        return list.stream().map(x -> new PedidoDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PedidoDTO findById(long id){
        Pedido pedido = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurson não encontrado: " + id));
        return new PedidoDTO(pedido);
    }

    @Transactional
    public PedidoDTO insert(PedidoDTO dto){
        Pedido entity = new Pedido();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new PedidoDTO(entity);
    }

    @Transactional
    public PedidoDTO update(long id, PedidoDTO dto){
        try {
            Pedido entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new PedidoDTO(entity);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso nao encontrado, ID: " + id);
        }
    }

    @Transactional
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Constraint violada");
        }
    }

    @Transactional
    public PedidoDTO updateStatus(Long id, Status status){
        try {
            Pedido entity = repository.getReferenceById(id);
            entity.setStatus(status);
            entity = repository.save(entity);
            return new PedidoDTO(entity);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso nao encontrado, ID: " + id);
        }
    }

    private void copyDtoToEntity(PedidoDTO dto, Pedido entity){
        entity.setDataHora(LocalDateTime.now());
        entity.setStatus(Status.CRIADO);
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
