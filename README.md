# Notification Library

Una librería Java diseñada para unificar el envío de notificaciones a través de diferentes canales como **Email**, **SMS** y **Push Notifications**, aplicando principios **SOLID**, una arquitectura extensible y una API simple basada en Builders.

## Características

- ✅ Java 21+
- ✅ Maven
- ✅ Arquitectura desacoplada
- ✅ Interfaz unificada para todos los canales
- ✅ Fácil cambio entre proveedores
- ✅ Fácil agregar nuevos canales
- ✅ Manejo de errores
- ✅ Tests unitarios
- ✅ Logging mediante Log4j2
- ✅ Simulación de proveedores (sin conexiones HTTP reales)

---

# Instalación

## Maven

Agrega la dependencia al archivo `pom.xml`:

```xml
<dependency>
    <groupId>com.jnicodev</groupId>
    <artifactId>notification-library</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Gradle

```gradle
implementation 'com.jnicodev:notification-library:1.0.0'
```

---

# Quick Start

## Enviar un Email con SendGrid

```java
NotificationService notificationService = NotificationServiceBuilder.builder()
                .channel(
                        Providers.email()
                                .sendGrid()
                                .apiKey("SENDGRID_API_KEY")
                                .from("no-reply@example.com")
                                .build()
                )
                .build();

NotificationRequest request =
        NotificationRequest.builder()
                .recipient("user@example.com")
                .content(
                        NotificationContent.builder()
                                .title("Bienvenido")
                                .body("Gracias por registrarte.")
                                .build()
                )
                .build();

NotificationResult result = notificationService.send(request);

System.out.println(result.message());
```

---

# Configuración

## Email

### SendGrid

```java
NotificationService notificationService = NotificationServiceBuilder.builder()
                .channel(
                        Providers.email()
                                .sendGrid()
                                .apiKey("SENDGRID_API_KEY")
                                .from("no-reply@example.com")
                                .build()
                )
                .build();
```

Parámetros requeridos:

| Parámetro | Descripción |
|-----------|-------------|
| apiKey | API Key de SendGrid |
| from | Dirección remitente |

---

### Mailgun

```java
NotificationService notificationService =NotificationServiceBuilder.builder()
                .channel(
                        Providers.email()
                                .mailgun()
                                .apiKey("MAILGUN_API_KEY")
                                .domain("sandbox.mailgun.org")
                                .from("postmaster@sandbox.mailgun.org")
                                .build()
                )
                .build();
```

Parámetros requeridos:

| Parámetro | Descripción |
|-----------|-------------|
| apiKey | API Key |
| domain | Dominio Mailgun |
| from | Correo remitente |

---

# SMS

### Twilio

```java
NotificationService notificationService = NotificationServiceBuilder.builder()
                .channel(
                        Providers.sms()
                                .twilio()
                                .accountSid("ACCOUNT_SID")
                                .authToken("AUTH_TOKEN")
                                .from("+15017122661")
                                .build()
                )
                .build();
```

Parámetros requeridos:

| Parámetro | Descripción |
|-----------|-------------|
| accountSid | Account SID |
| authToken | Auth Token |
| from | Número remitente |

---

# Push Notifications

### Firebase

```java
NotificationService notificationService =NotificationServiceBuilder.builder()
                .channel(
                        Providers.push()
                                .firebase()
                                .projectId("notification-demo")
                                .build()
                )
                .build();
```

Parámetros requeridos:

| Parámetro | Descripción |
|-----------|-------------|
| projectId | Firebase Project ID |

---

# Enviar una notificación

La API es exactamente igual para todos los canales.

```java
NotificationRequest request =NotificationRequest.builder()
                .recipient("DESTINATION")
                .content(
                        NotificationContent.builder()
                                .title("Título")
                                .body("Contenido")
                                .attribute("key", "value")
                                .build()
                )
                .build();

NotificationResult result = notificationService.send(request);
```

---

# Proveedores soportados

| Canal | Proveedor | Estado |
|--------|-----------|--------|
| Email | SendGrid | ✅ |
| Email | Mailgun | ✅ |
| SMS | Twilio | ✅ |
| Push | Firebase Cloud Messaging | ✅ |

La arquitectura permite agregar fácilmente nuevos proveedores sin modificar el código cliente.

Ejemplos de futuras integraciones:

- Amazon SES
- Mailjet
- Brevo
- Vonage
- AWS SNS
- OneSignal
- Expo Push
- Azure Notification Hub

---

# API Reference

## NotificationService

Servicio principal para el envío de notificaciones.

```java
NotificationResult send(NotificationRequest request)
```

---

## NotificationServiceBuilder

Construye una instancia de `NotificationService`.

```java
NotificationServiceBuilder.builder()
        .channel(...)
        .build();
```

---

## Providers

Punto de entrada para seleccionar el canal y proveedor.

```java
Providers.email()

Providers.sms()

Providers.push()
```

---

## NotificationRequest

Representa la solicitud de envío.

```java
NotificationRequest.builder()
        .recipient(...)
        .content(...)
        .build();
```

---

## NotificationContent

Contiene la información de la notificación.

```java
NotificationContent.builder()
        .title(...)
        .body(...)
        .attribute(...)
        .build();
```

Campos:

| Campo | Descripción |
|--------|-------------|
| title | Título |
| body | Contenido |
| attributes | Datos adicionales |

---

## NotificationResult

Resultado del envío.

```java
boolean success()

String provider()

String message()
```

---

# Manejo de errores

La librería diferencia entre errores de validación y errores de envío.

## NotificationException

Se lanza cuando la solicitud contiene datos inválidos.

Ejemplo:

```java
try {

    notificationService.send(request);

} catch (NotificationValidationException ex) {

    System.out.println(ex.getMessage());

}
```

---

## DeliveryException

Se lanza cuando el proveedor falla al enviar la notificación.

```java
try {

    notificationService.send(request);

} catch (NotificationDeliveryException ex) {

    System.out.println(ex.getMessage());

}
```

---

# Logging

La librería utiliza Log4j2 para registrar:

- Inicio del envío
- Solicitudes simuladas
- Respuestas simuladas
- Errores
- Validaciones

---

# Arquitectura

```
NotificationService
        │
        ▼
NotificationChannel
        │
 ┌──────┼─────────┐
 │      │         │
 ▼      ▼         ▼
Email   SMS      Push
 │       │         │
 ▼       ▼         ▼
Provider Provider Provider
 │       │         │
 ▼       ▼         ▼
Client   Client   Client
```

Cada proveedor encapsula completamente la lógica específica de la plataforma, permitiendo cambiar de proveedor sin modificar el código cliente.

---

# Extender la librería

Para agregar un nuevo proveedor se recomienda:

1. Crear la configuración del proveedor.
2. Implementar el cliente del proveedor.
3. Implementar el proveedor.
4. Crear el Builder correspondiente.
5. Registrar el proveedor en `ProviderFactory`.
6. Exponer el proveedor mediante `Providers`.

No es necesario modificar el flujo principal de la librería.

---

# Seguridad

## No almacenar credenciales en el código

Evita escribir claves directamente en el código fuente.

❌ Incorrecto

```java
.apiKey("SG.xxxxxxxxxxxxxxxxx")
```

---

## Utilizar variables de entorno

```java
.apiKey(System.getenv("SENDGRID_API_KEY"))
```

```java
.authToken(System.getenv("TWILIO_AUTH_TOKEN"))
```

```java
.accountSid(System.getenv("TWILIO_ACCOUNT_SID"))
```

---

## No versionar credenciales

Nunca subir al repositorio:

- API Keys
- Tokens
- Passwords
- Credenciales de Firebase

---

## Rotar credenciales periódicamente

Se recomienda rotar las claves cuando:

- Un desarrollador abandona el proyecto.
- Existe sospecha de filtración.
- La política de seguridad de la organización lo requiera.

---

## Usar diferentes credenciales por ambiente

- Desarrollo
- QA
- Producción

Cada ambiente debe tener credenciales independientes.

---

# Requisitos

- Java 21 o superior
- Maven 3.9+
- JUnit 5
- Mockito
- Log4j2

---

# Licencia

Este proyecto fue desarrollado con fines educativos y como demostración de una arquitectura extensible para el envío de notificaciones mediante múltiples canales y proveedores.