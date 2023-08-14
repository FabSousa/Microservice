package com.fiap.ClientesProdutos.rest.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.ClientesProdutos.rest.api.model.ClientesModel;

@Repository
public interface ClientesRepository extends JpaRepository<ClientesModel, Long> {
	
}
