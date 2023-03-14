package io.github.guiseixas.services.impls;

import io.github.guiseixas.entity.Cliente;
import io.github.guiseixas.entity.ItemPedido;
import io.github.guiseixas.entity.Pedido;
import io.github.guiseixas.entity.Produto;
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

import java.time.LocalDate;
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
        Cliente cliente = clienteRepository
                .findById(pedidoDTO.getId_cliente())
                .orElseThrow(() -> new RegraNegocioException("Código do cliente inválido."));

        Pedido pedido = new Pedido();
        pedido.setDataPedido(LocalDate.now());
        pedido.setTotal(pedidoDTO.getTotal());
        pedido.setCliente(cliente);

        pedidoRepository.save(pedido);
        List<ItemPedido> itensPedidos = ItensDtoToEntity(pedido, pedidoDTO.getItens());
        itemPedidoRepository.saveAll(itensPedidos);

        return pedido;
    }

    private List<ItemPedido> ItensDtoToEntity(Pedido pedido, List<ItemPedidoDTO> itens) {
        if(itens.isEmpty()){
            throw new RegraNegocioException("Não é possível realizar um pedido sem itens.");
        }
        return itens
                .stream()
                .map(dto -> {
                    Produto produto = produtoRepository
                            .findById(dto.getId_produto())
                            .orElseThrow(() -> new RegraNegocioException("Código do produto inválido."));
                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }
}
