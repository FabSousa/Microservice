package br.com.fiap.mspedidos.repository;

import br.com.fiap.mspedidos.model.Pedido;
import br.com.fiap.mspedidos.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Pedido p SET p.status = :status WHERE p = :pedido")
    void updateStatus(Status status, Pedido pedido);

    @Query(value = "SELECT p FROM Pedido p LEFT JOIN FETCH p.itens WHERE p.id = :id")
    Pedido getByIdWithItems(Long id);
}
