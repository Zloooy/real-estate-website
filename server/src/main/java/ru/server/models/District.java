package ru.server.models;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name="districts")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "Уникальный идентификатор района")
    @Column(name = "id")
    private Long id;
    @ApiModelProperty(notes = "Название района")
    @Column(name = "name")
    private String name;
    @ApiModelProperty(notes = "Город района")
    @ManyToOne(fetch = FetchType.EAGER)
    private City city;

    public District(String name, City city) {
        this.name = name;
        this.city = city;
    }

    public District() {
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
