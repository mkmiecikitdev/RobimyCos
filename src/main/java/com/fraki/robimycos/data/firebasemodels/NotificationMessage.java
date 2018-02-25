package com.fraki.robimycos.data.firebasemodels;

/**
 * Created by bambo on 10.10.2017.
 */
public class NotificationMessage {

    private String body;

    private String title;

    private String icon = "default";

    private String sound = "default";

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }
}
