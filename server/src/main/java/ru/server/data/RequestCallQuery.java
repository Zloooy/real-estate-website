package ru.server.data;

public class RequestCallQuery {
    private Long placeId;
    private CallRequestType callRequestType;
    private String name;
    private String contactPhone;
    private String contactEmail;
    private String comment;
    public enum CallRequestType {
        COMPLEX,
        FLAT,
        WANT_TO_SELL,
        WANT_TO_BUY
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public CallRequestType getCallRequestType() {
        return callRequestType;
    }

    public void setCallRequestType(CallRequestType callRequestType) {
        this.callRequestType = callRequestType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
