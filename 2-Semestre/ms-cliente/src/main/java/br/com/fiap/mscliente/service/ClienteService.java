package br.com.fiap.mscliente.service;

import br.com.fiap.mscliente.dto.ClienteDTO;
import br.com.fiap.mscliente.model.Cliente;
import br.com.fiap.mscliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Transactional(readOnly = true)
    public List<ClienteDTO> findAll(){
        List<Cliente> list = repository.findAll();
        return list.stream().map(x -> new ClienteDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ClienteDTO findById(Long id){
        Cliente entity = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Recurso não encontrado: "+id));
        return new ClienteDTO(entity);
    }

    @Transactional
    public ClienteDTO insert(ClienteDTO dto){
        Cliente entity = new Cliente();
        copyDTOToEntity(dto, entity);
        entity = repository.save(entity);
        return new ClienteDTO(entity);
    }

    @Transactional
    public ClienteDTO update(Long id, ClienteDTO dto){
        try {
            Cliente entity = repository.getReferenceById(id);
            copyDTOToEntity(dto, entity);
            entity = repository.save(entity);
            return new ClienteDTO(entity);
        }catch (EntityNotFoundException e){
            throw new RuntimeException("Recurso não encontrado: "+id);
        }
    }

    @Transactional
    public void delete(Long id){
        if (!repository.existsById(id)){
            throw new RuntimeException("Recurso não encontrado: "+id);
        }
        try {
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new RuntimeException("Integridade do dado violado");
        }
    }

    private void copyDTOToEntity(ClienteDTO dto, Cliente entity){
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setEndereco(dto.getEndereco());
        entity.setTelefone(dto.getTelefone());
        entity.setSenha(dto.getSenha());
    }
}
