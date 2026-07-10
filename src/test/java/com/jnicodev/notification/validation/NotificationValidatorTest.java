package com.jnicodev.notification.validation;

import com.jnicodev.notification.dto.NotificationContent;
import com.jnicodev.notification.dto.NotificationRequest;
import com.jnicodev.notification.exception.NotificationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotificationValidatorTest {

    @Test
    void shouldAcceptValidRequest() {

        NotificationRequest request =
                NotificationRequest.builder()
                        .recipient("user@test.com")
                        .content(
                                NotificationContent.builder()
                                        .title("Hello")
                                        .body("Message")
                                        .build()
                        )
                        .build();

        NotificationValidator validator = new NotificationValidator();

        assertDoesNotThrow(
                () -> validator.validate(request)
        );

    }

    @Test
    void shouldThrowExceptionWhenRecipientIsNull() {

        NotificationRequest request =
                NotificationRequest.builder()
                        .recipient(null)
                        .content(
                                NotificationContent.builder()
                                        .title("Hello")
                                        .body("Message")
                                        .build()
                        )
                        .build();

        NotificationValidator validator = new NotificationValidator();

        assertThrows(
                NotificationException.class,
                () -> validator.validate(request)
        );

    }

    @Test
    void shouldThrowExceptionWhenContentIsNull() {

        NotificationRequest request =
                NotificationRequest.builder()
                        .recipient("user@test.com")
                        .content(null)
                        .build();

        NotificationValidator validator = new NotificationValidator();

        assertThrows(
                NotificationException.class,
                () -> validator.validate(request)
        );

    }

    @Test
    void shouldThrowExceptionWhenBodyIsEmpty() {

        NotificationRequest request =
                NotificationRequest.builder()
                        .recipient("user@test.com")
                        .content(
                                NotificationContent.builder()
                                        .title("Hello")
                                        .body("")
                                        .build()
                        )
                        .build();

        NotificationValidator validator = new NotificationValidator();

        assertThrows(
                NotificationException.class,
                () -> validator.validate(request)
        );

    }

}