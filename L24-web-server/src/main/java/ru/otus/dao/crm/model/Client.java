package ru.otus.dao.crm.model;


import com.google.gson.annotations.Expose;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "clients")
public class Client implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Expose
    @Column(name = "id")
    private Long id;

    @Expose
    @Column(name = "name")
    private String name;

    @Expose
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @OneToOne(targetEntity = Address.class, fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Address address;

    @Expose
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Phone> phones = new HashSet<>();

    public Client() {
    }

    public Client(String name) {
        this.id = null;
        this.name = name;
    }

    public Client(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Client(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public Client(Long id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }

    public Client addPhone(Phone phone) {
        phone.setClient(this);
        phones.add(phone);
        return this;
    }

    public Client addAllPhones(Set<Phone> phones) {
        phones.forEach(this::addPhone);
        return this;
    }

    @Override
    public Client clone() {
        Client client = new Client(id, name);
        if (address != null) {
            client.setAddress(address.clone());
        }
        if (phones != null && !phones.isEmpty()) {
            Set<Phone> clonedPhones = new HashSet<>();
            for (Phone phone : phones) {
                final Phone clone = phone.clone();
                clonedPhones.add(clone);
            }
            client.addAllPhones(clonedPhones);
        }
        return client;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
