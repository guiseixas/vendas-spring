package io.github.guiseixas.services.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class ItemPedidoDTO {

    private Integer produto;

    private Integer quantidade;
}
