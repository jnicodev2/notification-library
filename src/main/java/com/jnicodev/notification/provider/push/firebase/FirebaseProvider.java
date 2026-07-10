package com.jnicodev.notification.provider.push.firebase;

import com.jnicodev.notification.dto.NotificationRequest;
import com.jnicodev.notification.dto.NotificationResult;
import com.jnicodev.notification.channel.push.PushSender;
import com.jnicodev.notification.config.FirebaseConfiguration;
import com.jnicodev.notification.dto.firebase.FirebaseMessage;
import com.jnicodev.notification.dto.firebase.FirebaseNotification;
import com.jnicodev.notification.dto.firebase.FirebaseRequest;
import com.jnicodev.notification.dto.firebase.FirebaseResponse;
import com.jnicodev.notification.exception.DeliveryException;

public class FirebaseProvider implements PushSender {

    private final FirebaseClient client;
    private final FirebaseConfiguration configuration;

    public FirebaseProvider(FirebaseConfiguration configuration) {

        this.configuration = configuration;
        this.client = new FirebaseClient(configuration);

    }

    @Override
    public NotificationResult send(NotificationRequest request) {

        FirebaseNotification notification =
                FirebaseNotification.builder()
                        .title(request.getContent().getTitle())
                        .body(request.getContent().getBody())
                        .build();

        FirebaseMessage.Builder builder =
                FirebaseMessage.builder()
                        .token(request.getRecipient())
                        .notification(notification);

        request.getContent().getAttributes()
                .forEach((key, value) ->
                        builder.data(key, String.valueOf(value)));

        FirebaseRequest firebaseRequest =
                new FirebaseRequest(
                        "https://fcm.googleapis.com/v1/projects/"
                                + configuration.getProjectId()
                                + "/messages:send",
                        builder.build().toJson()
                );

        FirebaseResponse response =
                client.send(firebaseRequest);

        if (!response.isSuccess()) {
            throw new DeliveryException(
                    "Firebase",
                    "Error enviando notificación Push."
            );
        }

        return new NotificationResult(
                true,
                "Firebase",
                response.getMessageId()
        );

    }

}
