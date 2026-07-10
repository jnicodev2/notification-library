package com.jnicodev.notification.provider.email.sendgrid;

import com.jnicodev.notification.config.SendGridConfiguration;
import com.jnicodev.notification.dto.sendgrid.SendGridRequest;
import com.jnicodev.notification.dto.sendgrid.SendGridResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.UUID;

public class SendGridClient {

    private final SendGridConfiguration configuration;

    public SendGridClient(SendGridConfiguration configuration) {
        this.configuration = configuration;
    }

    Logger logger = LoggerFactory.getLogger(SendGridClient.class);

    public SendGridResponse send(SendGridRequest request) {

        logRequest(request);

        if (request.getBody().contains("ERROR")) {
            return new SendGridResponse(
                    500,
                    "Internal Server Error",
                    null
            );
        }

        SendGridResponse response = new SendGridResponse(202, "Accepted", UUID.randomUUID().toString());

        logResponse(response);

        return response;
    }

    private String mask(String apiKey) {
        if (apiKey.length() < 8) {
            return "********";
        }

        return apiKey.substring(0, 4) + "********";
    }

    private void logRequest(SendGridRequest request) {
        logger.info("========== REQUEST ==========");
        logger.debug("POST {}", request.getEndpoint());
        logger.debug("Headers: Authorization=Bearer {}", mask(configuration.getApiKey()));
        logger.debug("Body:\n{}", request.getBody());
    }

    private void logResponse(SendGridResponse response) {
        logger.info("========== RESPONSE ==========");
        logger.debug("Response Status: {}", response.getStatusCode());
        logger.debug("Response Body: {}", response.getBody());
        logger.debug("Response MessageId: {}", response.getMessageId());
    }

}
