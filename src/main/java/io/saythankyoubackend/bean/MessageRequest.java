package io.saythankyoubackend.bean;

import java.io.Serializable;

public class MessageRequest implements Serializable {
    private String fromPerson;
    private String toPerson;
    private String challengeQuestion;
    private String challengeAnswer;
    private String msgBody;

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

    public String getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }
}