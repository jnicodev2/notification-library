package com.jnicodev.notification.provider.email.sendgrid;

import com.jnicodev.notification.config.SendGridConfiguration;
import com.jnicodev.notification.dto.NotificationContent;
import com.jnicodev.notification.dto.NotificationRequest;
import com.jnicodev.notification.dto.NotificationResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SendGridProviderTest {

    @Test
    void shouldReturnSuccessWhenEmailIsSent() {

        SendGridConfiguration configuration = SendGridConfiguration.builder()
                        .apiKey("KEY")
                        .from("test@test.com")
                        .build();

        SendGridProvider provider = new SendGridProvider(configuration);

        NotificationRequest request = NotificationRequest.builder()
                        .recipient("user@test.com")
                        .content(
                                NotificationContent.builder()
                                        .title("Hello")
                                        .body("World")
                                        .build()
                        )
                        .build();

        NotificationResult result = provider.send(request);

        assertTrue(result.isSuccess());

        assertEquals("SendGrid", result.getProvider());

    }

}