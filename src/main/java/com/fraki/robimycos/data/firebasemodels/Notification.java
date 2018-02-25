package com.fraki.robimycos.data.firebasemodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bambo on 10.10.2017.
 */
public class Notification {

    @JsonProperty("registration_ids")
    private Set<String> tokens = new HashSet<>();
    private String to;
    private NotificationMessage notification;
    private Data data;

    private Notification() {
    }

    public static Builder build() {
        return new Builder();
    }

    public Set<String> getTokens() {
        return tokens;
    }

    public String getTo() {
        return to;
    }
    public NotificationMessage getNotification() {
        return notification;
    }
    public Data getData() {
        return data;
    }

    public static class Builder {
        private Set<String> tokens = new HashSet<>();
        private String action = "defaultAction";
        private String body = "defaultMessage";
        private String title = "defaultTitle";
        private String value = "defaultValue";

        public Builder tokens(Set<String> tokens) {
            this.tokens = tokens;
            return this;
        }

        public Builder token(String token) {
            this.tokens.add(token);
            return this;
        }

        public Builder action(String action) {
            this.action = action;
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder value(String value) {
            this.value = value;
            return this;
        }

        public Notification apply() {
            Notification notification = new Notification();
            notification.tokens = this.tokens;

            NotificationMessage notificationMessage = new NotificationMessage();
            notificationMessage.setBody(this.body);
            notificationMessage.setTitle(this.title);
            notification.notification = notificationMessage;

            Data data = new Data();
            data.setActionType(this.action);
            data.setValue(this.value);
            notification.data = data;
            return notification;
        }

    }
}
