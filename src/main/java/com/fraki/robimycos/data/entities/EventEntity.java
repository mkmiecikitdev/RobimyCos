package com.fraki.robimycos.data.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by bambo on 09.10.2017.
 */

@Entity
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date date;

    @ManyToOne(optional = false)
    private EventTypeEntity eventType;

    @ManyToOne(optional = false)
    private UserEntity fromUser;

    @ManyToOne(optional = false)
    private UserEntity toUser;

    @Column(nullable = false)
    private String timeOption;

    private boolean accept;

    private boolean finish;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public EventTypeEntity getEventType() {
        return eventType;
    }

    public void setEventType(EventTypeEntity eventType) {
        this.eventType = eventType;
    }

    public UserEntity getFromUser() {
        return fromUser;
    }

    public void setFromUser(UserEntity fromUser) {
        this.fromUser = fromUser;
    }

    public UserEntity getToUser() {
        return toUser;
    }

    public void setToUser(UserEntity toUser) {
        this.toUser = toUser;
    }

    public String getTimeOption() {
        return timeOption;
    }

    public void setTimeOption(String timeOption) {
        this.timeOption = timeOption;
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }
}
