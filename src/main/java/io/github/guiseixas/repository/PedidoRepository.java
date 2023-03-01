package io.github.guiseixas.repository;

import io.github.guiseixas.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
