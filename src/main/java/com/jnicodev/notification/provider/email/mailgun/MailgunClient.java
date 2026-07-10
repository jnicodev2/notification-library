package com.jnicodev.notification.provider.email.mailgun;

import com.jnicodev.notification.config.MailgunConfiguration;
import com.jnicodev.notification.dto.mailgun.MailgunRequest;
import com.jnicodev.notification.dto.mailgun.MailgunResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

public class MailgunClient {

    private final MailgunConfiguration configuration;

    Logger logger = LoggerFactory.getLogger(MailgunClient.class);

    public MailgunClient(MailgunConfiguration configuration) {
        this.configuration = configuration;
    }

    public MailgunResponse send(MailgunRequest request) {

        logRequest(request);

        String id = UUID.randomUUID().toString();

        MailgunResponse response = new MailgunResponse(200, "Queued. Thank you.", id);

        logResponse(response);

        return response;

    }

    public String authorizationHeader() {

        String credentials =
                "api:" + configuration.getApiKey();

        return Base64.getEncoder()
                .encodeToString(
                        credentials.getBytes(StandardCharsets.UTF_8));

    }

    private String mask(String auth) {

        return auth.substring(0, 6) + "********";

    }
    private void logRequest(MailgunRequest request) {
        logger.info("POST {}", request.getEndpoint());
        logger.debug("Headers: Authorization: Basic {}", mask(configuration.getApiKey()));
        logger.debug("Content-Type: application/x-www-form-urlencoded");
        logger.debug("Body:\n{}", request.getBody());
    }

    private void logResponse(MailgunResponse response) {
        logger.info("========== RESPONSE ==========");
        logger.debug("Response Status: {}", response.getStatusCode());
        logger.debug("Response Body: {}", response.getBody());
        logger.debug("Response MessageId: {}", response.getId());
    }
}
