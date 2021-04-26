package ru.server.models;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name="metro_stations")
public class Metro {
    public Metro(String name, String color, City city){
        this.name = name;
        this.color = color;
        this.city = city;
    }
    protected Metro() {}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "Уникльный идентификатор станции метро")
    @Column(name="id")
    private Long id;
    @ApiModelProperty(notes = "Название станции метро")
    @Column(name="name", nullable = false)
    private String name;
    @ApiModelProperty(notes="Цвет станции метро")
    @Column(name="color")
    private String color;
    @ApiModelProperty(notes = "Город станции метро")
    @ManyToOne(fetch = FetchType.EAGER)
    private City city;

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
