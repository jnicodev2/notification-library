package com.jnicodev.notification.builder.push;

import com.jnicodev.notification.channel.NotificationChannel;
import com.jnicodev.notification.config.FirebaseConfiguration;
import com.jnicodev.notification.factory.ProviderFactory;

public class FirebaseBuilder {

    private String projectId;

    public FirebaseBuilder projectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public NotificationChannel build() {

        FirebaseConfiguration configuration =
                FirebaseConfiguration.builder()
                        .projectId(projectId)
                        .build();

        return ProviderFactory.push()
                .firebase(configuration);

    }

}
