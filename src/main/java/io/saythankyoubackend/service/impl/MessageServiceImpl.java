package io.saythankyoubackend.service.impl;

import io.saythankyoubackend.bean.MessageRequest;
import io.saythankyoubackend.entity.ConfirmationToken;
import io.saythankyoubackend.entity.MessageData;
import io.saythankyoubackend.repository.ConfirmationTokenRepository;
import io.saythankyoubackend.repository.MessageDataRepository;
import io.saythankyoubackend.response.MessageQuestionnaireResponse;
import io.saythankyoubackend.response.MessageReceiveResponse;
import io.saythankyoubackend.response.MessageSendResponse;
import io.saythankyoubackend.service.MessageService;
import io.saythankyoubackend.util.AESUtil;
import io.saythankyoubackend.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class MessageServiceImpl implements MessageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Autowired
    MessageDataRepository messageDataRepository;

    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;

    @Override
    public MessageSendResponse processMessage(MessageRequest messageRequest) {
        MessageSendResponse messageSendResponse;
        try {
            MessageData messageData = new MessageData();
            messageData.setFromPerson(messageRequest.getFromPerson());
            messageData.setToPerson(messageRequest.getToPerson());
            messageData.setChallengeQuestion(messageRequest.getChallengeQuestion());
            messageData.setChallengeAnswer(messageRequest.getChallengeAnswer());
            messageData.setMsgBody(messageRequest.getMsgBody().getBytes("UTF-8"));
            messageData.setSubmittedTimestamp(new Date());
            String toBeEncryptedData = new StringBuilder(messageRequest.getFromPerson()).append("~")
                    .append(messageRequest.getToPerson()).append("~")
                    .append(messageData.getSubmittedTimestamp()).toString();
            String encryptedToken = AESUtil.encrypt(toBeEncryptedData);
            messageData.setEncodedToken(encryptedToken);
            MessageData returnObj = messageDataRepository.save(messageData);

            ConfirmationToken confirmationToken = new ConfirmationToken(returnObj);
            ConfirmationToken returnToken = confirmationTokenRepository.save(confirmationToken);

            messageSendResponse = new MessageSendResponse(URLEncoder.encode(returnToken.getConfirmationToken(), "UTF-8"), URLEncoder.encode(returnObj.getEncodedToken(), "UTF-8"), Constants.SUCCESS);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            messageSendResponse = new MessageSendResponse(null, null, Constants.FAILURE);
        }
        return messageSendResponse;
    }

    @Override
    public MessageQuestionnaireResponse fetchQuestions(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        MessageQuestionnaireResponse response;
        if (token != null) {
            MessageData obj = token.getMessageData();
            response = new MessageQuestionnaireResponse(obj.getFromPerson(), obj.getToPerson(), obj.getChallengeQuestion(), Constants.SUCCESS);
        } else {
            response = new MessageQuestionnaireResponse(null, null, null, Constants.FAILURE);
        }
        return response;
    }

    @Override
    public MessageReceiveResponse getMessage(String challengeAnswer, String token) {
        MessageReceiveResponse response = new MessageReceiveResponse();
        MessageData obj = messageDataRepository.findByEncodedToken(token);
        if (obj != null) {
            if (challengeAnswer.equalsIgnoreCase(obj.getChallengeAnswer())) {
                String data = new String(obj.getMsgBody(), StandardCharsets.UTF_8);
                response.setData(data);
                response.setMessage(Constants.SUCCESS);
            } else {
                response.setData(null);
                response.setMessage(Constants.WRONG);
            }
        } else {
            response.setData(null);
            response.setMessage(Constants.UNAUTHORIZED);
        }
        return response;
    }

    @Override
    public void deleteRecords() {
        messageDataRepository.deleteRecords();
    }
}
