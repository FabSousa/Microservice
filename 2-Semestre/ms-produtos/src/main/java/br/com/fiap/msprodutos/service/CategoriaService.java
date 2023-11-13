package br.com.fiap.msprodutos.service;

import br.com.fiap.msprodutos.dto.CategoriaDTO;
import br.com.fiap.msprodutos.model.Categoria;
import br.com.fiap.msprodutos.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    @Transactional(readOnly = true)
    public List<CategoriaDTO> findAll() {
        List<Categoria> list = repository.findAll();

        return list.stream().map(CategoriaDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoriaDTO findById(Long id) {

        Categoria entity = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Recurso não encontrado"));

        return new CategoriaDTO(entity);
    }

    @Transactional
    public CategoriaDTO insert(CategoriaDTO dto) {

        Categoria entity = new Categoria();
        convertDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new CategoriaDTO(entity);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Recurso não encontrado");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Falha de integridade referencial");
        }
    }

    private void convertDtoToEntity(CategoriaDTO dto, Categoria entity) {
        entity.setNome(dto.getNome());
    }
}
