package com.jnicodev.notification.channel.sms;

import com.jnicodev.notification.dto.NotificationContent;
import com.jnicodev.notification.dto.NotificationRequest;
import com.jnicodev.notification.dto.NotificationResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SmsNotificationChannelTest {

    @Test
    void shouldDelegateToSmsSender() {

        SmsSender sender = mock(SmsSender.class);

        NotificationResult expected = new NotificationResult(
                        true,
                        "Twilio",
                        "SMS enviado correctamente"
                );

        when(sender.send(any())).thenReturn(expected);

        SmsNotificationChannel channel = new SmsNotificationChannel(sender);

        NotificationRequest request = NotificationRequest.builder()
                        .recipient("+50370000000")
                        .content(
                                NotificationContent.builder()
                                        .body("Código: 123456")
                                        .build()
                        )
                        .build();

        NotificationResult result = channel.send(request);

        verify(sender).send(request);

        assertEquals(expected, result);

    }

}