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
    @Query("Update Pedido p Set p.status = :status Where p = :pedido")
    void updateStatus(Status status, Pedido pedido);

    @Query(value = "Select p from Pedido p Left Join Fetch p.itens Where p.id = :id")
    Pedido getByIdWithItems(Long id);
}
