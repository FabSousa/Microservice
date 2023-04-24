package br.com.fiap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import br.com.fiap.model.LojaModel;

@Component
public interface LojaRepository extends JpaRepository<LojaModel, Long>{

}
