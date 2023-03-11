package io.github.guiseixas.services.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

    private Integer id_cliente;

    private BigDecimal total;

    private List<ItemPedidoDTO> itens;
}
