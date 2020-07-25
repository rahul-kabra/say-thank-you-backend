package io.saythankyoubackend.response;

import java.io.Serializable;

public class MessageQuestionnaireResponse implements Serializable {
    private String fromPerson;
    private String toPerson;
    private String challengeQuestion;
    private String message;

    public MessageQuestionnaireResponse() {

    }

    public MessageQuestionnaireResponse(String fromPerson, String toPerson, String challengeQuestion, String message) {
        this.fromPerson = fromPerson;
        this.toPerson = toPerson;
        this.challengeQuestion = challengeQuestion;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
