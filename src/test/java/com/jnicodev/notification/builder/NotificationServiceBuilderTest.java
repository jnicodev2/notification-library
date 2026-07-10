package com.jnicodev.notification.builder;

import com.jnicodev.notification.channel.NotificationChannel;
import com.jnicodev.notification.service.NotificationService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class NotificationServiceBuilderTest {

    @Test
    void shouldBuildNotificationService() {

        NotificationChannel channel =
                mock(NotificationChannel.class);

        NotificationService service =
                NotificationServiceBuilder.builder()
                        .channel(channel)
                        .build();

        assertNotNull(service);

    }

    @Test
    void shouldThrowExceptionWhenChannelIsMissing() {

        assertThrows(
                NullPointerException.class,
                () -> NotificationServiceBuilder.builder()
                        .build()
        );

    }

}