package ru.otus.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.entity.Client;

import java.util.List;

@Repository
public interface ClientRepository  extends CrudRepository<Client, Long> {

    @Override
    @Query(value = "select c.id, c.login, c.role from  client c order by c.id")
    List<Client> findAll();
}
