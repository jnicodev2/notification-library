package com.jnicodev.notification.provider.push.firebase;

import com.jnicodev.notification.config.FirebaseConfiguration;
import com.jnicodev.notification.dto.NotificationContent;
import com.jnicodev.notification.dto.NotificationRequest;
import com.jnicodev.notification.dto.NotificationResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FirebaseProviderTest {

    @Test
    void shouldSendPushSuccessfully() {

        FirebaseConfiguration configuration = FirebaseConfiguration.builder()
                        .projectId("notification-demo")
                        .build();

        FirebaseProvider provider = new FirebaseProvider(configuration);

        NotificationRequest request = NotificationRequest.builder()
                        .recipient("DEVICE_TOKEN")
                        .content(
                                NotificationContent.builder()
                                        .title("Bienvenido")
                                        .body("Gracias por instalar la aplicación")
                                        .build()
                        )
                        .build();

        NotificationResult result = provider.send(request);

        assertNotNull(result);

        assertTrue(result.isSuccess());

        assertEquals("Firebase", result.getProvider());

    }

    @Test
    void shouldReturnProviderName() {

        FirebaseConfiguration configuration =
                FirebaseConfiguration.builder()
                        .projectId("notification-demo")
                        .build();

        FirebaseProvider provider =
                new FirebaseProvider(configuration);

        NotificationRequest request =
                NotificationRequest.builder()
                        .recipient("DEVICE_TOKEN")
                        .content(
                                NotificationContent.builder()
                                        .title("Hola")
                                        .body("Mensaje")
                                        .build()
                        )
                        .build();

        NotificationResult result =
                provider.send(request);

        assertEquals("Firebase", result.getProvider());

    }

    @Test
    void shouldReturnSuccessResult() {

        FirebaseConfiguration configuration =
                FirebaseConfiguration.builder()
                        .projectId("notification-demo")
                        .build();

        FirebaseProvider provider =
                new FirebaseProvider(configuration);

        NotificationRequest request =
                NotificationRequest.builder()
                        .recipient("DEVICE_TOKEN")
                        .content(
                                NotificationContent.builder()
                                        .title("Título")
                                        .body("Contenido")
                                        .build()
                        )
                        .build();

        NotificationResult result =
                provider.send(request);

        assertTrue(result.isSuccess());

        assertNotNull(result.getMessage());

    }

}