package io.saythankyoubackend.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Component
@Entity
@Table(name = "confirmation_token")
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "token_id", unique = true, nullable = false)
    private int tokenId;

    @Column(name = "confirmation_token")
    private String confirmationToken;

    @Column(name = "created_timestamp")
    private Date createdTimestamp;

    @OneToOne(targetEntity = MessageData.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "msg_id")
    private MessageData messageData;

    public ConfirmationToken() {

    }

    public ConfirmationToken(MessageData messageData) {
        this.confirmationToken = UUID.randomUUID().toString();
        ;
        this.createdTimestamp = new Date();
        this.messageData = messageData;
    }

    public int getTokenId() {
        return tokenId;
    }

    public void setTokenId(int tokenId) {
        this.tokenId = tokenId;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public MessageData getMessageData() {
        return messageData;
    }

    public void setMessageData(MessageData messageData) {
        this.messageData = messageData;
    }
}
