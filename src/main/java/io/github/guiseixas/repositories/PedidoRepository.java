package io.github.guiseixas.repositories;

import io.github.guiseixas.entity.Cliente;
import io.github.guiseixas.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    Set<Pedido> findByCliente(Cliente cliente);
}
