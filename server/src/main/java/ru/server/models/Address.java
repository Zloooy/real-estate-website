package ru.server.models;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name="addresses")
public class Address {
    public Address(String street, String house, District district, City city, Metro metro) {
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
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    private District district;
    @ApiModelProperty("Город")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private City city;
    @ApiModelProperty("Ближайшая станция метро")
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    private Metro metro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Metro getMetro() {
        return metro;
    }

    public void setMetro(Metro metro) {
        this.metro = metro;
    }
}
