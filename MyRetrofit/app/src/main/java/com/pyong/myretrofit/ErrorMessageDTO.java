package com.pyong.myretrofit;

import com.google.gson.annotations.Expose;

public class ErrorMessageDTO {
    @Expose
    private String status;
    @Expose
    private String code;
    @Expose
    private String message;
    @Expose
    private String link;
    @Expose
    private String developerMessage;
    @Expose
    private String total;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ErrorMessageDTO{" +
                "status='" + status + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", link='" + link + '\'' +
                ", developerMessage='" + developerMessage + '\'' +
                ", total='" + total + '\'' +
                '}';
    }
}
