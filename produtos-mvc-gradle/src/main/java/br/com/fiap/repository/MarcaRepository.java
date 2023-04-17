package br.com.fiap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import br.com.fiap.model.MarcaModel;

@Component
public interface MarcaRepository extends JpaRepository<MarcaModel, Long> {

}
