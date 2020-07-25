package io.saythankyoubackend.service;

import io.saythankyoubackend.bean.MessageRequest;
import io.saythankyoubackend.response.MessageQuestionnaireResponse;
import io.saythankyoubackend.response.MessageReceiveResponse;
import io.saythankyoubackend.response.MessageSendResponse;
import org.springframework.stereotype.Service;

@Service
public interface MessageService {

    MessageSendResponse processMessage(MessageRequest messageRequest);

    MessageQuestionnaireResponse fetchQuestions(String confirmationToken);

    MessageReceiveResponse getMessage(String challengeAnswer, String token);

    void deleteRecords();
}
