package com.example.homework.model;

import com.example.homework.annotations.Id;

public class Manager {

    @Id
    private Long id;
    private String name;
    private String position;

    public Manager(Long id, String name, String position) {
        this.id = id;
        this.name = name;
        this.position = position;
    }

    public Manager() {
    }

    public Manager(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
