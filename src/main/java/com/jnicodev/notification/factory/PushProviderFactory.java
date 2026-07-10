package com.jnicodev.notification.factory;

import com.jnicodev.notification.channel.NotificationChannel;
import com.jnicodev.notification.channel.push.PushNotificationChannel;
import com.jnicodev.notification.config.FirebaseConfiguration;
import com.jnicodev.notification.provider.push.firebase.FirebaseProvider;

public class PushProviderFactory {

    public NotificationChannel firebase(
            FirebaseConfiguration configuration) {

        return new PushNotificationChannel(
                new FirebaseProvider(configuration)
        );

    }

}
