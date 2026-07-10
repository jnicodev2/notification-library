package com.jnicodev.notification.provider.push.firebase;

import com.jnicodev.notification.config.FirebaseConfiguration;
import com.jnicodev.notification.dto.firebase.FirebaseRequest;
import com.jnicodev.notification.dto.firebase.FirebaseResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class FirebaseClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(FirebaseClient.class);

    private final FirebaseConfiguration configuration;

    public FirebaseClient(FirebaseConfiguration configuration) {
        this.configuration = configuration;
    }

    public FirebaseResponse send(FirebaseRequest request) {

        LOGGER.info("POST {}", request.getEndpoint());

        LOGGER.debug("Request:\n{}", request.getBody());

        return new FirebaseResponse(
                200,
                UUID.randomUUID().toString()
        );

    }

}
