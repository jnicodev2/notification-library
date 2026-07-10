package com.jnicodev.notification;

import com.jnicodev.notification.builder.Providers;
import com.jnicodev.notification.channel.NotificationChannel;
import com.jnicodev.notification.dto.NotificationContent;
import com.jnicodev.notification.dto.NotificationRequest;
import com.jnicodev.notification.dto.NotificationResult;
import com.jnicodev.notification.service.NotificationService;
import com.jnicodev.notification.builder.NotificationServiceBuilder;

import com.jnicodev.notification.exception.DeliveryException;
import com.jnicodev.notification.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static NotificationResult result;

    static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("***************************************** TEST ENVIO DE NOTIFICACIONES *****************************************");
        logger.info("\n");
        logger.info("*************** Enviando email SendGrid provider ***************");
        sendGridEmail();
        logger.info("*************** Finaliza email SendGrid provider ***************");
        logger.info("\n");

        logger.info("*************** Enviando email Mailgun provider ***************");
        sendMailGunEmail();
        logger.info("*************** Finaliza email Mailgun provider ***************");
        logger.info("\n");

        logger.info("*************** Enviando SMS Twilio ***************");
        sendSmsTwilio();
        logger.info("*************** Finaliza SMS Twilio ***************");
        logger.info("\n");

        logger.info("*************** Enviando Notificacion push Firebase ***************");
        sendPushNotificationFirebase();
        logger.info("*************** Finaliza Notificacion push Firebase ***************");

    }

    static void sendPushNotificationFirebase(){
        try{
            NotificationChannel channel = Providers.push()
                    .firebase()
                    .projectId("notification-demo")
                    .build();

            NotificationService service = NotificationServiceBuilder.builder()
                            .channel(channel)
                            .build();

            NotificationContent content = NotificationContent.builder()
                    .title("Nueva promoción")
                    .body("Obtén un 50% de descuento.")
                    .attribute("screen", "offers")
                    .attribute("campaign", "summer")
                    .build();

            NotificationRequest request = NotificationRequest.builder()
                            .recipient("DEVICE_TOKEN")
                            .content(content)
                            .build();

            service.send(request);
        } catch (ValidationException ex) {
            logger.error("Error de validación: {}", ex.getMessage());
        } catch (DeliveryException ex) {
            logger.error("Proveedor: {}", ex.getProvider());
            logger.error(ex.getMessage());
        }
    }

    static void sendSmsTwilio(){
        try{
            NotificationChannel channel = Providers.sms()
                    .twilio()
                    .accountSid("AC123456789")
                    .authToken("TOKEN123")
                    .from("+15017122661")
                    .build();

            NotificationService service = NotificationServiceBuilder.builder()
                            .channel(channel)
                            .build();

            NotificationContent content = NotificationContent.builder()
                    .body("Tu codigo es 123456")
                    .build();

            NotificationRequest request = NotificationRequest.builder()
                    .recipient("+5037777777")
                    .content(content)
                    .build();

            service.send(request);
        } catch (ValidationException ex) {
            logger.error("Error de validación: {}", ex.getMessage());
        } catch (DeliveryException ex) {
            logger.error("Proveedor: {}", ex.getProvider());
            logger.error(ex.getMessage());
        }

    }

    static void sendMailGunEmail(){
        try{
            NotificationChannel channel = Providers.email()
                    .mailgun()
                    .apiKey("key-123456")
                    .domain("sandbox.mailgun.org")
                    .from("noreply@jnicodev.com")
                    .build();

            NotificationService service = NotificationServiceBuilder.builder()
                            .channel(channel)
                            .build();

            NotificationContent content = NotificationContent.builder()
                    .title("Prueba MailGun")
                    .body("Gracias por registrarte.")
                    .build();

            NotificationRequest request =
                    NotificationRequest.builder()
                            .recipient("nico@test.com")
                            .content(content)
                            .build();

            service.send(request);
        } catch (ValidationException ex) {
            logger.error("Error de validación: {}", ex.getMessage());
        } catch (DeliveryException ex) {
            logger.error("Proveedor: {}", ex.getProvider());
            logger.error(ex.getMessage());
        }

    }

    static void sendGridEmail() {
        try {
            NotificationChannel channel = Providers.email()
                    .sendGrid()
                    .apiKey("SG-123456")
                    .from("noreply@jnicodev.com")
                    .build();

            NotificationService service = NotificationServiceBuilder.builder()
                            .channel(channel)
                            .build();

            NotificationContent content = NotificationContent.builder()
                    .title("Prueba SendGrid")
                    .body("Gracias por registrarte.")
                    .build();

            NotificationRequest request = NotificationRequest.builder()
                            .recipient("nico@test.com")
                            .content(content)
                            .build();

            service.send(request);

        } catch (ValidationException ex) {
            logger.error("Error de validación: {}", ex.getMessage());
        } catch (DeliveryException ex) {
            logger.error("Proveedor: {}", ex.getProvider());
            logger.error(ex.getMessage());
        }
    }

}
