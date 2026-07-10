package com.jnicodev.notification.channel.push;

import com.jnicodev.notification.dto.NotificationContent;
import com.jnicodev.notification.dto.NotificationRequest;
import com.jnicodev.notification.dto.NotificationResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PushNotificationChannelTest {

    @Test
    void shouldDelegateNotificationToPushSender() {

        PushSender sender = mock(PushSender.class);

        NotificationResult expected = new NotificationResult(
                        true,
                        "Firebase",
                        "Push enviado correctamente"
                );

        when(sender.send(any())).thenReturn(expected);

        PushNotificationChannel channel = new PushNotificationChannel(sender);

        NotificationRequest request = NotificationRequest.builder()
                        .recipient("DEVICE_TOKEN")
                        .content(
                                NotificationContent.builder()
                                        .title("Oferta")
                                        .body("50% de descuento")
                                        .attribute("screen", "offers")
                                        .build()
                        )
                        .build();

        NotificationResult result = channel.send(request);

        verify(sender).send(request);

        assertEquals(expected, result);

    }

}