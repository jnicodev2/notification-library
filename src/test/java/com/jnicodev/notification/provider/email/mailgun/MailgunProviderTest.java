package com.jnicodev.notification.provider.email.mailgun;

import com.jnicodev.notification.config.MailgunConfiguration;
import com.jnicodev.notification.dto.NotificationContent;
import com.jnicodev.notification.dto.NotificationRequest;
import com.jnicodev.notification.dto.NotificationResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MailgunProviderTest {

    @Test
    void shouldSendEmailSuccessfully() {

        MailgunConfiguration configuration =
                MailgunConfiguration.builder()
                        .apiKey("key-test")
                        .domain("sandbox.mailgun.org")
                        .from("Test <postmaster@sandbox.mailgun.org>")
                        .build();

        MailgunProvider provider =
                new MailgunProvider(configuration);

        NotificationRequest request =
                NotificationRequest.builder()
                        .recipient("user@test.com")
                        .content(
                                NotificationContent.builder()
                                        .title("Hello")
                                        .body("Mailgun test")
                                        .build()
                        )
                        .build();

        NotificationResult result = provider.send(request);

        assertNotNull(result);
        assertTrue(result.isSuccess());
        assertEquals("Mailgun", result.getProvider());

    }

}