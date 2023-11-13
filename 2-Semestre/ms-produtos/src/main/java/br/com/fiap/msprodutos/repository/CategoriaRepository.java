package br.com.fiap.msprodutos.repository;

import br.com.fiap.msprodutos.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
