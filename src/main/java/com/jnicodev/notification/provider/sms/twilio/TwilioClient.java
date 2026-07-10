package com.jnicodev.notification.provider.sms.twilio;

import com.jnicodev.notification.config.TwilioConfiguration;
import com.jnicodev.notification.dto.twilio.TwilioRequest;
import com.jnicodev.notification.dto.twilio.TwilioResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class TwilioClient {

    private final TwilioConfiguration configuration;

    Logger logger = LoggerFactory.getLogger(TwilioClient.class);

    public TwilioClient(TwilioConfiguration configuration) {
        this.configuration = configuration;
    }

    public TwilioResponse send(TwilioRequest request) {

        logRequest(request);

        String sid = "SM" + UUID.randomUUID().toString().replace("-", "");

        TwilioResponse response = new TwilioResponse(201, sid, "queued");

        logResponse(response);

        return response;
    }

    private void logRequest(TwilioRequest request) {
        logger.info("========== REQUEST ==========");
        logger.info("Account SID :  {}", configuration.getAccountSid());
        logger.debug("From : {}", request.getMessage().getFrom().getValue());
        logger.debug("To : {}", request.getMessage().getTo().getValue());
        logger.debug("Body:\n{}", request.getMessage().getBody());
    }

    private void logResponse(TwilioResponse response){
        logger.info("========== RESPONSE ==========");
        logger.debug("Status Code: {}", response.getStatusCode());
        logger.debug("SID : {}", response.getSid());
        logger.debug("Status : {}", response.getStatus());
    }

}
