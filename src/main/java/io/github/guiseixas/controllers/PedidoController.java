package io.github.guiseixas.controllers;

import io.github.guiseixas.entity.Pedido;
import io.github.guiseixas.services.PedidoService;
import io.github.guiseixas.services.dtos.PedidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody PedidoDTO pedidoDTO){
        return pedidoService.save(pedidoDTO).getId();
    }
}
