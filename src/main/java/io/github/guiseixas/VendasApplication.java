package io.github.guiseixas;

import io.github.guiseixas.entity.Cliente;
import io.github.guiseixas.entity.Pedido;
import io.github.guiseixas.repository.ClienteRepository;
import io.github.guiseixas.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@RestController
public class VendasApplication {
    public static void main( String[] args ) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
