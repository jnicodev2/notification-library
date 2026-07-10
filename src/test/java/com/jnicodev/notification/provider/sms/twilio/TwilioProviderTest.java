package com.jnicodev.notification.provider.sms.twilio;

import com.jnicodev.notification.config.TwilioConfiguration;
import com.jnicodev.notification.dto.NotificationContent;
import com.jnicodev.notification.dto.NotificationRequest;
import com.jnicodev.notification.dto.NotificationResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TwilioProviderTest {

    @Test
    void shouldSendSmsSuccessfully() {

        TwilioConfiguration configuration =
                TwilioConfiguration.builder()
                        .accountSid("AC123456789")
                        .authToken("TOKEN123")
                        .from("+15017122661")
                        .build();

        TwilioProvider provider =
                new TwilioProvider(configuration);

        NotificationRequest request =
                NotificationRequest.builder()
                        .recipient("+50370000000")
                        .content(
                                NotificationContent.builder()
                                        .body("Su código es 123456")
                                        .build()
                        )
                        .build();

        NotificationResult result =
                provider.send(request);

        assertNotNull(result);
        assertTrue(result.isSuccess());
        assertEquals("Twilio", result.getProvider());

    }

    @Test
    void shouldReturnSuccessResult() {

        TwilioConfiguration configuration =
                TwilioConfiguration.builder()
                        .accountSid("AC123456789")
                        .authToken("TOKEN123")
                        .from("+15017122661")
                        .build();

        TwilioProvider provider =
                new TwilioProvider(configuration);

        NotificationRequest request =
                NotificationRequest.builder()
                        .recipient("+50370000000")
                        .content(
                                NotificationContent.builder()
                                        .body("Mensaje de prueba")
                                        .build()
                        )
                        .build();

        NotificationResult result =
                provider.send(request);

        assertTrue(result.isSuccess());
        assertNotNull(result.getMessage());

    }

    @Test
    void shouldReturnTwilioAsProvider() {

        TwilioConfiguration configuration =
                TwilioConfiguration.builder()
                        .accountSid("AC123456789")
                        .authToken("TOKEN123")
                        .from("+15017122661")
                        .build();

        TwilioProvider provider =
                new TwilioProvider(configuration);

        NotificationRequest request =
                NotificationRequest.builder()
                        .recipient("+50370000000")
                        .content(
                                NotificationContent.builder()
                                        .body("Hola")
                                        .build()
                        )
                        .build();

        NotificationResult result =
                provider.send(request);

        assertEquals("Twilio", result.getProvider());

    }

}