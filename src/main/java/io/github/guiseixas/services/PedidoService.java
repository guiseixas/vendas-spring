package io.github.guiseixas.services;

import io.github.guiseixas.entity.Pedido;
import io.github.guiseixas.services.dtos.PedidoDTO;

public interface PedidoService {
    Pedido save(PedidoDTO pedido);
}
