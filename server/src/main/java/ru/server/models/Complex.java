package ru.server.models;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name="complexes")
public class Complex {
    public Complex(User author, String name, Address address, EstateType estateType, int amountOfRooms, int price, int space, String image, String comment, Contacts contacts, EstateStatus status, EstateCategory category, boolean advertized) {
        this.author = author;
        this.name = name;
        this.address = address;
        this.estateType = estateType;
        this.amountOfRooms = amountOfRooms;
        this.price = price;
        this.space = space;
        this.image = image;
        this.comment = comment;
        this.contacts = contacts;
        this.status = status;
        this.category = category;
        this.advertized = advertized;
    }

    protected Complex() {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "Униветсальный идентификатор комплекса")
    @Column(name="id")
    private Long id;
    @ApiModelProperty(name="Пользователь, опубликовавший информациб об обьекте")
    @ManyToOne(fetch = FetchType.EAGER)
    private User author;
    @ApiModelProperty(notes = "Название комплекса")
    @Column(name = "name", nullable = false)
    private String name;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @ApiModelProperty(name="Адрес комплекса")
    private Address address;
    @ApiModelProperty(notes = "Тип недвижимости")
    @Enumerated(value = EnumType.STRING)
    @Column(name="estate_type", nullable = false)
    EstateType estateType;
    @ApiModelProperty(notes="Количество комнат")
    @Column(name="amount_of_rooms", nullable = false)
    private int amountOfRooms;
    @ApiModelProperty("Цена, руб")
    @Column(name = "price", nullable = false)
    private int price;
    @ApiModelProperty("Площадь, м. кв.")
    @Column(name = "space", nullable = false)
    private int space;
    @ApiModelProperty("image")
    @Column(name = "image", length = 400)
    private String image;
    @ApiModelProperty("Комментарий")
    @Column(name="comment", length = 1000)
    private String comment;
    @ApiModelProperty(notes = "Контакты")
    @ManyToOne(fetch = FetchType.EAGER)
    private Contacts contacts;
    @ApiModelProperty(notes = "Статус объекта: принят, отклонен, сдается")
    @Enumerated(value = EnumType.STRING)
    @Column(name="status", nullable = false)
    private EstateStatus status;
    @ApiModelProperty(notes = "Категория надвижимости: новостройки, вторичка, аренда")
    @Enumerated(value = EnumType.STRING)
    @Column(name="category", nullable = false)
    private EstateCategory category;
    @ApiModelProperty("Реклама - отображать ли в блоке рекомендаций")
    @Column(name = "advertized", nullable = false)
    private boolean advertized;
    public static enum EstateType {
        ROOM,
        FLAT,
        HOUSE;
    }

    public static enum EstateStatus {
        ACCEPTED,
        DECLINED,
        RENTED;
    }

    public static enum EstateCategory {
        NEW,
        SECONDARY,
        RENT;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
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

    public EstateType getEstateType() {
        return estateType;
    }

    public void setEstateType(EstateType estateType) {
        this.estateType = estateType;
    }

    public int getAmountOfRooms() {
        return amountOfRooms;
    }

    public void setAmountOfRooms(int amountOfRooms) {
        this.amountOfRooms = amountOfRooms;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Contacts getContacts() {
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    public EstateStatus getStatus() {
        return status;
    }

    public void setStatus(EstateStatus status) {
        this.status = status;
    }

    public EstateCategory getCategory() {
        return category;
    }

    public void setCategory(EstateCategory category) {
        this.category = category;
    }

    public boolean isAdvertized() {
        return advertized;
    }

    public void setAdvertized(boolean advertized) {
        this.advertized = advertized;
    }
}