package com.fraki.robimycos.data.entities;

import com.fraki.robimycos.data.entities.embeddable.MessageEmbeddable;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by bambo on 14.10.2017.
 */

@Entity
public class ConversationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date lastMsgDate;

    @ElementCollection
    @CollectionId(
            columns = @Column(name = "MESSAGE_ID"),
            type = @Type(type = "long"),
            generator = "sequence"
    )
    @org.hibernate.annotations.OrderBy(clause = "creationDate ASC")
    private Collection<MessageEmbeddable> messages = new ArrayList<>();

    @ManyToOne
    private UserEntity user1;

    @ManyToOne
    private UserEntity user2;

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

    public Collection<MessageEmbeddable> getMessages() {
        return messages;
    }

    public void setMessages(Collection<MessageEmbeddable> messages) {
        this.messages = messages;
    }

    public UserEntity getUser1() {
        return user1;
    }

    public void setUser1(UserEntity user1) {
        this.user1 = user1;
    }

    public UserEntity getUser2() {
        return user2;
    }

    public void setUser2(UserEntity user2) {
        this.user2 = user2;
    }
}
