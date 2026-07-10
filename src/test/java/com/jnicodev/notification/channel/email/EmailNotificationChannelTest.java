package com.jnicodev.notification.channel.email;

import com.jnicodev.notification.dto.NotificationContent;
import com.jnicodev.notification.dto.NotificationRequest;
import com.jnicodev.notification.dto.NotificationResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EmailNotificationChannelTest {

    @Test
    void shouldDelegateSendToEmailSender() {

        EmailSender sender = mock(EmailSender.class);

        NotificationResult expected =
                new NotificationResult(
                        true,
                        "SendGrid",
                        "OK"
                );

        when(sender.send(any())).thenReturn(expected);

        EmailNotificationChannel channel = new EmailNotificationChannel(sender);

        NotificationRequest request = NotificationRequest.builder()
                        .recipient("test@test.com")
                        .content(
                                NotificationContent.builder()
                                        .title("Hello")
                                        .body("World")
                                        .build()
                        )
                        .build();

        NotificationResult result = channel.send(request);

        verify(sender).send(request);

        assertEquals(expected, result);
    }
}
