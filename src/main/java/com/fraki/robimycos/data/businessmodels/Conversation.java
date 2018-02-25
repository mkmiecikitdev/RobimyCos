package com.fraki.robimycos.data.businessmodels;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bambo on 14.10.2017.
 */
public class Conversation {

    private long id;
    private Date lastMsgDate;
    private List<Message> messages = new ArrayList<>();
    private User user1;
    private User user2;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getLastMsgDate() {
        return lastMsgDate;
    }

    public void setLastMsgDate(Date lastMsgDate) {
        this.lastMsgDate = lastMsgDate;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }
}
