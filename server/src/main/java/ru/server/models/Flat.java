package ru.server.models;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name="flats")
public class Flat {
    public Flat(){}

    public Flat(String image, Complex complex, String about, double price, double numberOfRooms, EstateType estateType, float square, int floor) {
        this.image = image;
        this.complex = complex;
        this.about = about;
        this.price = price;
        this.numberOfRooms = numberOfRooms;
        this.estateType = estateType;
        this.square = square;
        this.floor = floor;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "Уникальный идентификатор квартиры")
    @Column(name="id")
    private Long id;
    @ApiModelProperty(name="Картинка квартиры")
    @Column(name="image")
    private String image;
    @ApiModelProperty("Комплекс, к которому относится квартира")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Complex complex;
    @ApiModelProperty("Описание квартиры")
    @Column(name="about", length=1000)
    private String about;
    @ApiModelProperty("Цена")
    @Column(name="price")
    private double price;
    @ApiModelProperty("Количество комнат")
    @Column(name="number_of_rooms")
    private double numberOfRooms;
    @ApiModelProperty(notes = "Тип недвижимости")
    @Enumerated(value = EnumType.STRING)
    @Column(name="estate_type", nullable = false)
    EstateType estateType;
    @ApiModelProperty(notes="Площадь квартиры")
    @Column(name="square")
    private float square;
    @ApiModelProperty(notes="Этаж квартиры")
    @Column(name="floor")
    private int floor;
    public static enum EstateType {
        ROOM,
        FLAT,
        HOUSE
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Complex getComplex() {
        return complex;
    }

    public void setComplex(Complex complex) {
        this.complex = complex;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(double numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public EstateType getEstateType() {
        return estateType;
    }

    public void setEstateType(EstateType estateType) {
        this.estateType = estateType;
    }

    public float getSquare() {
        return square;
    }

    public void setSquare(float square) {
        this.square = square;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }
}
