package com.fraki.robimycos.services;


import com.fraki.robimycos.data.businessmodels.Event;
import com.fraki.robimycos.data.businessmodels.Message;
import com.fraki.robimycos.data.firebasemodels.MessageJSON;
import com.fraki.robimycos.data.firebasemodels.Notification;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Created by bambo on 10.10.2017.
 */

@Service
public class FirebaseService {

    private static final String URL = "https://fcm.googleapis.com/fcm/send";
    private static final String SERV_KEY = "AAAAkNWI5zY:APA91bFAaslxHGbM1UkThEStMPihJXjPy0Q13nX-sIaTnHv7aBbpwZyK1iDeGyGoDtWFSm0Md4GypVgQRyAIvT4JiuduGR9OK3MUsV8U4f-dMOrM4RF5SDpjp9vTFfEbfuD-yGCRAvNh";
    private static final String AUTHORIZATION = "key=" + SERV_KEY;
    private static final String AUTHORIZATION_KEY = "Authorization";

    public void notifyGetEvent(Event event) {

        Notification notification =
                Notification.build()
                .token(event.getToUser().getTokenFirebase())
                .action("Request")
                .body("Od " +  event.getFromUser().getLogin())
                .title("Nowe zapytanie")
                .value(String.valueOf(event.getId()))
                .apply();

        send(notification);
    }

    public void notifyAnswerEvent(Event event) {
        Notification notification =
                Notification.build()
                        .token(event.getFromUser().getTokenFirebase())
                        .action("Response")
                        .body("Od " +  event.getToUser().getLogin())
                        .title("Odpowiedź na wydarzenie")
                        .value(String.valueOf(event.getId()))
                        .apply();

        send(notification);
    }

    public void notifyNewMessage(String tokenFirebase, String login, MessageJSON messageJSON) {

        Notification notification =
                Notification.build()
                        .token(tokenFirebase)
                        .action("New message")
                        .body("Od " +  login)
                        .title("Nowa wiadomość")
                        .value(messageJSON.toString())
                        .apply();

        send(notification);

    }


    private static void send(Notification notification) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set(AUTHORIZATION_KEY, AUTHORIZATION);

        HttpEntity<Notification> entity = new HttpEntity<>(notification, headers);
        restTemplate.exchange(URL, HttpMethod.POST, entity, Notification.class);
    }



}
