package ru.server.models;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name="contacts")
public class Contacts {
    public Contacts(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
    protected Contacts() {}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "Уникальный идентификатор контактов")
    @Column(name="id")
    private Long id;
    @ApiModelProperty(notes = "Имя контактного лица")
    @Column(name="name")
    private String name;
    @ApiModelProperty(notes = "Телефон")
    @Column(name = "phone")
    private String phone;
    @ApiModelProperty(name="Эл. почта")
    @Column(name="email")
    private String email;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
