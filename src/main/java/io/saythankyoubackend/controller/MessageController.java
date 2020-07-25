package io.saythankyoubackend.controller;

import io.saythankyoubackend.bean.MessageRequest;
import io.saythankyoubackend.response.MessageQuestionnaireResponse;
import io.saythankyoubackend.response.MessageReceiveResponse;
import io.saythankyoubackend.response.MessageSendResponse;
import io.saythankyoubackend.response.Response;
import io.saythankyoubackend.service.MessageService;
import io.saythankyoubackend.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
public class MessageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    MessageService messageService;

    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<MessageSendResponse> sendMessage(@RequestBody MessageRequest messageRequest) {
        MessageSendResponse messageSendResponse;
        try {
            messageSendResponse = messageService.processMessage(messageRequest);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            messageSendResponse = new MessageSendResponse();
        }
        return new ResponseEntity<MessageSendResponse>(messageSendResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/fetch", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> fetch(@RequestParam(value = "token", required = true) String token) {
        Response response = new Response();
        if (!token.equalsIgnoreCase("")) {
            MessageQuestionnaireResponse messageQuestionnaireResponse = messageService.fetchQuestions(token);
            if (messageQuestionnaireResponse.getFromPerson() == null) {
                response.setMessage("wrong or an unauthorized token.");
                return new ResponseEntity<Response>(response, HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<MessageQuestionnaireResponse>(messageQuestionnaireResponse, HttpStatus.OK);
        }
        return new ResponseEntity<Response>(response, HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getMessage(@RequestParam(value = "secretCode", required = true) String secretCode, @RequestParam(value = "challengeAnswer", defaultValue = "Absolutely.") String challengeAnswer) {
        Response response = new Response();
        MessageReceiveResponse messageReceiveResponse = messageService.getMessage(challengeAnswer, secretCode);
        if (Constants.WRONG.equals(messageReceiveResponse.getMessage())) {
            response.setMessage("Wrong answer.");
            return new ResponseEntity<Response>(response, HttpStatus.EXPECTATION_FAILED);
        }
        if (Constants.UNAUTHORIZED.equals(messageReceiveResponse.getMessage())) {
            response.setMessage("Unauthorized secret code.");
            return new ResponseEntity<Response>(response, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<MessageReceiveResponse>(messageReceiveResponse, HttpStatus.OK);
    }

    @Scheduled(cron = "0 0 */3 ? * *")
    public void performCleanup() {
        LOGGER.info("Performing cleanup of records which have been existing for more than 3 hours.");
        messageService.deleteRecords();
    }

}
