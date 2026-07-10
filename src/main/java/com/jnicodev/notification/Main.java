package com.jnicodev.notification;

import com.jnicodev.notification.dto.NotificationContent;
import com.jnicodev.notification.dto.NotificationRequest;
import com.jnicodev.notification.dto.NotificationResult;
import com.jnicodev.notification.service.NotificationService;
import com.jnicodev.notification.builder.NotificationServiceBuilder;

import com.jnicodev.notification.config.FirebaseConfiguration;
import com.jnicodev.notification.config.MailgunConfiguration;
import com.jnicodev.notification.config.SendGridConfiguration;
import com.jnicodev.notification.config.TwilioConfiguration;
import com.jnicodev.notification.exception.DeliveryException;
import com.jnicodev.notification.exception.ValidationException;
import com.jnicodev.notification.provider.email.mailgun.MailgunProvider;
import com.jnicodev.notification.provider.email.sendgrid.SendGridProvider;
import com.jnicodev.notification.provider.push.firebase.FirebaseProvider;
import com.jnicodev.notification.provider.sms.twilio.TwilioProvider;
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
            FirebaseConfiguration configuration = FirebaseConfiguration.builder()
                    .projectId("notification-demo")
                    .build();

            NotificationService service = NotificationServiceBuilder.builder()
                            .push(new FirebaseProvider(configuration))
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
            TwilioConfiguration config = TwilioConfiguration.builder()
                    .accountSid("ACxxxxxxxx")
                    .authToken("token")
                    .from("+15017122661")
                    .build();

            NotificationService service = NotificationServiceBuilder.builder()
                    .sms(new TwilioProvider(config))
                    .build();

            NotificationRequest request = NotificationRequest.builder()
                    .recipient("+5037777777")
                    .content(
                            NotificationContent.builder()
                                    .body("Tu codigo es 123456")
                                    .build()
                    )
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
            MailgunConfiguration configuration = MailgunConfiguration.builder()
                    .apiKey("key-123")
                    .domain("sandbox.mailgun.org")
                    .from("noreply@jnicodev.com")
                    .build();

            NotificationService service = NotificationServiceBuilder.builder()
                    .email(new MailgunProvider(configuration))
                    .build();

            NotificationRequest request =
                    NotificationRequest.builder()
                            .recipient("nico@test.com")
                            .content(
                                    NotificationContent.builder()
                                            .title("Prueba MailGun")
                                            .body("Gracias por registrarte.")
                                            .build()
                            )
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
            SendGridConfiguration configuration = SendGridConfiguration.builder()
                    .apiKey("SG.xxxxxxxxxxxxxxxxx")
                    .from("noreply@jnicodev.com")
                    .build();

            NotificationService service = NotificationServiceBuilder.builder()
                    .email(new SendGridProvider(configuration))
                    .build();

            NotificationRequest request =
                    NotificationRequest.builder()
                            .recipient("nico@test.com")
                            .content(
                                    NotificationContent.builder()
                                            .title("Prueba SendGrid")
                                            .body("Gracias por registrarte.")
                                            .build()
                            )
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
