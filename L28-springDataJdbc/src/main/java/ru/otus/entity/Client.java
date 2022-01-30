package ru.otus.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("client")
public class Client {

    @Id
    private  Long id;
    private  String login;
    private  String role;

    public Client() {

    }

    public Client(String login, String role) {

        this(null, login, role);
    }

    @PersistenceConstructor
    public Client(Long id, String login, String role) {
        this.id = id;
        this.login = login;
        this.role = role;
    }

}
