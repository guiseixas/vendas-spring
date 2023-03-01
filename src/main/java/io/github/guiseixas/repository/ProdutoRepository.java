package io.github.guiseixas.repository;

import io.github.guiseixas.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
