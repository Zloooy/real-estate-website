package ru.server.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import ru.server.models.Complex;

import java.util.Date;

public class ComplexQuery {

    @ApiModelProperty(notes = "Уникальный идентификатор комплекса")
    private Long id;
    @ApiModelProperty(notes = "Количество комнат")
    private Integer amountOfRooms;
    @ApiModelProperty(notes = "Категория недвижимости: новостройка, вторичка...")
    private Complex.EstateCategory estateCategory;
    @ApiModelProperty("Маска названия комплекса")
    private String name;
    @ApiModelProperty(notes = "Минимальная цена")
    private Integer priceMin;
    @ApiModelProperty(notes = "Максимальная цена")
    private Integer priceMax;
    @ApiModelProperty(notes = "Площадь, нижний порог")
    private Integer spaceMin;
    @ApiModelProperty(notes = "Площадь, верхний порог")
    private Integer spaceMax;
    @ApiModelProperty(notes = "id автора")
    private Long authorId;
    @ApiModelProperty("id контакта")
    private Long contactsId;
    @ApiModelProperty(notes = "id станции метро")
    private Long metroId;
    @ApiModelProperty(notes = "id района")
    private Long districtId;
    @ApiModelProperty(notes = "id города", required = true)
    private Long cityId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy")
    @ApiModelProperty(notes = "Стартовая дата сдачи")
    private Date deliveryDate;
    @ApiModelProperty(notes = "Рекламируется")
    private Boolean advertized;
    protected ComplexQuery(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAmountOfRooms() {
        return amountOfRooms;
    }

    public void setAmountOfRooms(Integer amountOfRooms) {
        this.amountOfRooms = amountOfRooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(Integer priceMin) {
        this.priceMin = priceMin;
    }

    public Integer getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(Integer priceMax) {
        this.priceMax = priceMax;
    }

    public Integer getSpaceMin() {
        return spaceMin;
    }

    public void setSpaceMin(Integer spaceMin) {
        this.spaceMin = spaceMin;
    }

    public Integer getSpaceMax() {
        return spaceMax;
    }

    public void setSpaceMax(Integer spaceMax) {
        this.spaceMax = spaceMax;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getContactsId() {
        return contactsId;
    }

    public void setContactsId(Long contactsId) {
        this.contactsId = contactsId;
    }

    public Long getMetroId() {
        return metroId;
    }

    public void setMetroId(Long metroId) {
        this.metroId = metroId;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Complex.EstateCategory getEstateCategory() {
        return estateCategory;
    }

    public void setEstateCategory(Complex.EstateCategory estateCategory) {
        this.estateCategory = estateCategory;
    }

    public Boolean getAdvertized() {
        return advertized;
    }

    public void setAdvertized(Boolean advertized) {
        this.advertized = advertized;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
