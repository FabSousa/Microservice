package br.com.fiap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import br.com.fiap.model.CategoriaModel;

@Component
public interface CategoriaRepository extends JpaRepository<CategoriaModel, Long> {

}
