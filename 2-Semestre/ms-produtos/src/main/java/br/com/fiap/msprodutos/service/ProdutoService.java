package br.com.fiap.msprodutos.service;

import br.com.fiap.msprodutos.dto.ProdutoCategoriaDTO;
import br.com.fiap.msprodutos.dto.ProdutoDTO;
import br.com.fiap.msprodutos.model.Categoria;
import br.com.fiap.msprodutos.model.Produto;
import br.com.fiap.msprodutos.repository.CategoriaRepository;
import br.com.fiap.msprodutos.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public List<ProdutoDTO> findAll() {
        List<Produto> list = repository.findAll();
        return list.stream().map(ProdutoDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ProdutoCategoriaDTO> findAllWithCategoriaNome() {
        List<Produto> list = repository.findAll();
        return list.stream().map(ProdutoCategoriaDTO::new).collect(Collectors.toList());
    }

    public Page<ProdutoCategoriaDTO> findAllPaged(Pageable pageable) {
        Page<Produto> page = repository.findAll(pageable);
        return page.map(ProdutoCategoriaDTO::new);
    }

    public ProdutoCategoriaDTO findById(Long id) {
        Produto entity = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Recurso não encontrado")
        );
        return new ProdutoCategoriaDTO(entity);
    }

    @Transactional
    public ProdutoDTO insert(ProdutoDTO dto) {
        Produto entity = new Produto();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ProdutoDTO(entity);
    }

    private void copyDtoToEntity(ProdutoDTO dto, Produto entity) {
        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        entity.setPreco(dto.getPreco());
        entity.setCaracteristica(dto.getCaracteristica());
        Categoria categoria = new Categoria();
        categoria = categoriaRepository.findById(dto.getCategoriaId()).orElseThrow(
                () -> new EntityNotFoundException("Categoria ID")
        );
        entity.setCategoria(categoria);
    }
}
