package br.com.fiap.mscliente.repository;

import br.com.fiap.mscliente.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
