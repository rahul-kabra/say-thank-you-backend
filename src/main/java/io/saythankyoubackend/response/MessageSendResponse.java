package io.saythankyoubackend.response;

import java.io.Serializable;

public class MessageSendResponse implements Serializable {
    private String confirmationToken;
    private String secretMessageToken;
    private String message;

    public MessageSendResponse() {

    }

    public MessageSendResponse(String confirmationToken, String secretMessageToken, String message) {
        this.confirmationToken = confirmationToken;
        this.secretMessageToken = secretMessageToken;
        this.message = message;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public String getSecretMessageToken() {
        return secretMessageToken;
    }

    public void setSecretMessageToken(String secretMessageToken) {
        this.secretMessageToken = secretMessageToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
