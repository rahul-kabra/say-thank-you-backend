package io.saythankyoubackend.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Component
@Entity
@Table(name = "message")
public class MessageData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "msg_id", unique = true, nullable = false)
    private int msgId;

    @Column(name = "from_person")
    private String fromPerson;

    @Column(name = "to_person")
    private String toPerson;

    @Column(name = "challenge_question")
    private String challengeQuestion;

    @Column(name = "challenge_answer")
    private String challengeAnswer;

    @Lob
    @Column(name = "msg_body")
    private byte[] msgBody;

    @Column(name = "submitted_timestamp")
    private Date submittedTimestamp;

    @Column(name = "encoded_token")
    private String encodedToken;

    public MessageData() {

    }

    public MessageData(int msgId, String fromPerson, String toPerson, String challengeQuestion, String challengeAnswer, byte[] msgBody, Date submittedTimestamp, String encodedToken) {
        this.msgId = msgId;
        this.fromPerson = fromPerson;
        this.toPerson = toPerson;
        this.challengeQuestion = challengeQuestion;
        this.challengeAnswer = challengeAnswer;
        this.msgBody = msgBody;
        this.submittedTimestamp = submittedTimestamp;
        this.encodedToken = encodedToken;
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public String getFromPerson() {
        return fromPerson;
    }

    public void setFromPerson(String fromPerson) {
        this.fromPerson = fromPerson;
    }

    public String getToPerson() {
        return toPerson;
    }

    public void setToPerson(String toPerson) {
        this.toPerson = toPerson;
    }

    public String getChallengeQuestion() {
        return challengeQuestion;
    }

    public void setChallengeQuestion(String challengeQuestion) {
        this.challengeQuestion = challengeQuestion;
    }

    public String getChallengeAnswer() {
        return challengeAnswer;
    }

    public void setChallengeAnswer(String challengeAnswer) {
        this.challengeAnswer = challengeAnswer;
    }

    public byte[] getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(byte[] msgBody) {
        this.msgBody = msgBody;
    }

    public Date getSubmittedTimestamp() {
        return submittedTimestamp;
    }

    public void setSubmittedTimestamp(Date submittedTimestamp) {
        this.submittedTimestamp = submittedTimestamp;
    }

    public String getEncodedToken() {
        return encodedToken;
    }

    public void setEncodedToken(String encodedToken) {
        this.encodedToken = encodedToken;
    }

    @Override
    public String toString() {
        return "MessageData{" +
                "msgId=" + msgId +
                ", fromPerson='" + fromPerson + '\'' +
                ", toPerson='" + toPerson + '\'' +
                ", challengeQuestion='" + challengeQuestion + '\'' +
                ", challengeAnswer='" + challengeAnswer + '\'' +
                ", msgBody='" + msgBody + '\'' +
                ", submittedTimestamp=" + submittedTimestamp +
                ", encodedToken='" + encodedToken + '\'' +
                '}';
    }
}
