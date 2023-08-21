package br.com.fiap.mspagamentos.service;

import br.com.fiap.mspagamentos.dto.PagamentoDTO;
import br.com.fiap.mspagamentos.model.Pagamento;
import br.com.fiap.mspagamentos.model.Status;
import br.com.fiap.mspagamentos.repository.PagamentoRepository;
import br.com.fiap.mspagamentos.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;

    @Transactional(readOnly = true)
    public List<PagamentoDTO> findAll(){
        List<Pagamento> list = repository.findAll();

        return list.stream().map(x -> new PagamentoDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PagamentoDTO findById(long id){
        Pagamento pagamento = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurson não encontrado: " + id));
        PagamentoDTO dto = new PagamentoDTO(pagamento);
        return dto;
    }

    @Transactional
    public PagamentoDTO insert(PagamentoDTO dto){
        Pagamento entity = new Pagamento();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new PagamentoDTO(entity);
    }

    @Transactional
    public PagamentoDTO update(long id, PagamentoDTO dto){
        try {
            Pagamento entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new PagamentoDTO(entity);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso nao encontrado, ID: " + id);
        }
    }

    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }

    private void copyDtoToEntity(PagamentoDTO dto, Pagamento entity){
        entity.setValor(dto.getValor());
        entity.setNome(dto.getNome());
        entity.setNumerocartao((dto.getNumeroDoCartao()));
        entity.setValidade(dto.getValidade());
        entity.setCodigo(dto.getCodigo());
        entity.setStatus((dto.getStatus()));
        entity.setPedidoId(dto.getPedidoId());
        entity.setFormaDePagamentoId(dto.getFormaDePagamento());
        entity.setStatus(Status.CRIADO);
    }
}
