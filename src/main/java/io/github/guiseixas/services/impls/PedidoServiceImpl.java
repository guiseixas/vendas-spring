package io.github.guiseixas.services.impls;

import io.github.guiseixas.repositories.PedidoRepository;
import io.github.guiseixas.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
}
