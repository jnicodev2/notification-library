package com.jnicodev.notification.validation;

import com.jnicodev.notification.dto.NotificationRequest;
import com.jnicodev.notification.exception.ValidationException;

public class NotificationValidator {

    public void validate(NotificationRequest request) {

        if (request == null) {
            throw new ValidationException("NotificationRequest no puede ser null.");
        }

        if (request.getRecipient() == null || request.getRecipient().isBlank()) {

            throw new ValidationException("El destinatario es obligatorio.");
        }

        if (request.getContent() == null) {
            throw new ValidationException("El contenido es obligatorio.");
        }

        if (request.getContent().getBody() == null || request.getContent().getBody().isBlank()) {

            throw new ValidationException("El mensaje es obligatorio.");
        }

    }

}
