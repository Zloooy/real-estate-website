package ru.server.models;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name="addresses")
public class Address {
    public Address(String street, String house, String district, City city, Metro metro) {
        this.street = street;
        this.house = house;
        this.district = district;
        this.city = city;
        this.metro = metro;
    }

    protected Address(){}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "Униклальный идентификатор адреса")
    @Column(name="id")
    private Long id;
    @ApiModelProperty(notes = "Название улицы")
    @Column(name="street")
    private String street;
    @ApiModelProperty(notes = "Номер дома")
    @Column(name="house")
    private String house;
    @ApiModelProperty("Район")
    @Column(name="district",nullable = true)
    private String district;
    @ApiModelProperty("Город")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private City city;
    @ApiModelProperty("Ближайшая станция метро")
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    private Metro metro;
}
