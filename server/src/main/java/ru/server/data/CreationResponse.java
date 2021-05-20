package ru.server.data;

import io.swagger.annotations.ApiModelProperty;

public class CreationResponse {
    @ApiModelProperty(value = "Удачно ли создался")
    private boolean created;
    @ApiModelProperty(value = "id созданной сущности")
    private Long id;
    public CreationResponse(boolean created, Long id) {
        this.created = created;
        this.id = id;
    }

    public CreationResponse() {
    }

    public boolean getCreated() {
        return created;
    }

    public void setCreated(boolean created) {
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
