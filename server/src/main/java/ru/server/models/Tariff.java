package ru.server.models;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name="tariffs")
public class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "Уникальный идентификатор тарифа")
    @Column(name="id")
    private Long id;
    @ApiModelProperty(notes="Название услуги")
    @Column(name="name", nullable = false, unique = true)
    private String name;
    @ApiModelProperty(notes="Цена услуги")
    @Column(name="price")
    private Double price;
    @ApiModelProperty(notes = "Тип цены тарифа (проценты, рубли и т. п.).")
    @Column(name="price_type")
    @Enumerated(value=EnumType.STRING)
    private TariffPriceType priceType;
    public enum TariffPriceType {
        ROUBLES,
        PERCENT_OF_TRANSACTION_AMOUNT,
        PERCENT_OF_MONTHLY_RENT_RATE
    }

    public Tariff() {
    }

    public Tariff(String name, Double price, TariffPriceType priceType) {
        this.name = name;
        this.price = price;
        this.priceType = priceType;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public TariffPriceType getPriceType() {
        return priceType;
    }

    public void setPriceType(TariffPriceType priceType) {
        this.priceType = priceType;
    }
}
