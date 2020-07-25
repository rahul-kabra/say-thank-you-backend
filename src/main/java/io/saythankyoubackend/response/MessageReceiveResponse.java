package io.saythankyoubackend.response;

import io.saythankyoubackend.entity.MessageData;

import java.io.Serializable;

public class MessageReceiveResponse implements Serializable {
    private String data;
    private String message;

    public MessageReceiveResponse() {

    }

    public MessageReceiveResponse(String data, String message) {
        this.data = data;
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
