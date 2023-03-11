package io.github.guiseixas.services.impls;

import io.github.guiseixas.entity.ItemPedido;
import io.github.guiseixas.entity.Pedido;
import io.github.guiseixas.repositories.ClienteRepository;
import io.github.guiseixas.repositories.ItemPedidoRepository;
import io.github.guiseixas.repositories.PedidoRepository;
import io.github.guiseixas.repositories.ProdutoRepository;
import io.github.guiseixas.services.PedidoService;
import io.github.guiseixas.services.dtos.ItemPedidoDTO;
import io.github.guiseixas.services.dtos.PedidoDTO;
import io.github.guiseixas.services.exceptions.RegraNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Override
    @Transactional
    public Pedido save(PedidoDTO pedidoDTO) {
        Pedido pedido = Pedido.dtoToEntity(pedidoDTO);
        pedido.setCliente(clienteRepository
                        .findById(pedido.getId())
                        .orElseThrow(() -> new RegraNegocioException("Código do cliente inválido."))
        );
        List<ItemPedido> itensPedidos = convertItensDtoToList(pedido, pedidoDTO.getItens());
        itemPedidoRepository.saveAll(itensPedidos);
        pedido.setItensPedidos(itensPedidos);
        pedidoRepository.save(pedido);
        return pedido;
    }

    private List<ItemPedido> convertItensDtoToList(Pedido pedido, List<ItemPedidoDTO> itens) {
        if(itens.isEmpty()){
            throw new RegraNegocioException("Não é possível realizar um pedido sem itens.");
        }
        return itens
                .stream()
                .map(dto -> {
                   ItemPedido itemPedido = new ItemPedido();
                   itemPedido.setQuantidade(dto.getQuantidade());
                   itemPedido.setPedido(pedido);
                   itemPedido.setProduto(produtoRepository
                           .findById(dto.getId_produto())
                           .orElseThrow(() -> new RegraNegocioException("Código do produto inválido.")));
                   return itemPedido;
                }).collect(Collectors.toList());
    }
}
