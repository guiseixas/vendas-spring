package io.github.guiseixas.controller;

import io.github.guiseixas.entity.Cliente;
import io.github.guiseixas.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getClientes(Cliente cliente) {
        ExampleMatcher matcher = ExampleMatcher
                                    .matching()
                                    .withIgnoreCase()
                                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(cliente, matcher);
        List<Cliente> clientes = clienteRepository.findAll(example);

        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getClienteById(@PathVariable("id") Integer id){
        Optional<Cliente> cliente  = clienteRepository.findById(id);
        if(cliente.isPresent()){
            return ResponseEntity.ok(cliente.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> saveCliente(@RequestBody Cliente cliente) {
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteCliente(@PathVariable("id") Integer id) {
        Optional<Cliente> cliente  = clienteRepository.findById(id);
        if(cliente.isPresent()){
            clienteRepository.delete(cliente.get());
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> updateCliente(@PathVariable("id") Integer id,
                                           @RequestBody Cliente cliente) {
        Optional<Cliente> c  = clienteRepository.findById(id);
        if(c.isPresent()){
            c.get().setNome(cliente.getNome());
            c.get().setCpf(cliente.getCpf());
            Cliente clienteSalvo = clienteRepository.save(c.get());
            return ResponseEntity.ok(clienteSalvo);
        }

        return ResponseEntity.notFound().build();
    }

}
