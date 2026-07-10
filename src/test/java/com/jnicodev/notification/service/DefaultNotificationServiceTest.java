package com.jnicodev.notification.service;

import com.jnicodev.notification.channel.NotificationChannel;
import com.jnicodev.notification.dto.NotificationContent;
import com.jnicodev.notification.dto.NotificationRequest;
import com.jnicodev.notification.dto.NotificationResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DefaultNotificationServiceTest {

    @Test
    void shouldDelegateNotificationToChannel() {

        NotificationChannel channel = mock(NotificationChannel.class);

        NotificationResult expected = new NotificationResult(
                        true,
                        "SendGrid",
                        "OK"
                );

        when(channel.send(any())).thenReturn(expected);

        DefaultNotificationService service = new DefaultNotificationService(channel);

        NotificationRequest request = NotificationRequest.builder()
                        .recipient("user@test.com")
                        .content(
                                NotificationContent.builder()
                                        .title("Hello")
                                        .body("World")
                                        .build()
                        )
                        .build();

        NotificationResult result = service.send(request);

        verify(channel).send(request);

        assertEquals(expected, result);

    }

}