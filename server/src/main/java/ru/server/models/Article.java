package ru.server.models;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes="Уникальный идентификатор статьи блога")
    private Long id;
    @ApiModelProperty(notes = "Дата публикации статьи")
    @Column(name="publish_date")
    @Temporal(TemporalType.DATE)
    private Date publishDate;
    @ApiModelProperty("Илллюстрация к статье")
    @Column(name="image")
    private String image;
    @ApiModelProperty("Название статьи")
    @Column(name="title")
    private String title;
    @ApiModelProperty("Текст статьи")
    @Column(name="text", length = 5000)
    private String text;

    public Article() {
    }

    public Article(Date publishDate, String image, String title, String text) {
        this.publishDate = publishDate;
        this.image = image;
        this.title = title;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
