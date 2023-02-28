package io.github.guiseixas.repository;

import io.github.guiseixas.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ClienteRepository {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Cliente salvar(Cliente cliente){
        entityManager.persist(cliente);
        return cliente;
    }

    @Transactional
    public Cliente atualizar(Cliente cliente){
        entityManager.merge(cliente);
        return cliente;
    }

    @Transactional
    public void delete(Cliente cliente){
        if(!entityManager.contains(cliente)){
            cliente = entityManager.merge(cliente);
        }
        entityManager.remove(cliente);
    }

    @Transactional
    public void delete(Integer id){
        delete(entityManager.find(Cliente.class, id));
    }

    @Transactional(readOnly = true)
    public List<Cliente> searchByName(String nome){
        String jpql = " select c from Cliente c where c.nome like :nome ";
        TypedQuery<Cliente> typedQuery = entityManager.createQuery(jpql, Cliente.class);
        typedQuery.setParameter("nome", "%" + nome + "%");
        return typedQuery.getResultList();
    }

    @Transactional(readOnly = true)
    public List<Cliente> getClientes(){
        return entityManager.createQuery("from Cliente", Cliente.class).getResultList();
    }
}
