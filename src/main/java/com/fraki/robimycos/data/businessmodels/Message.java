package com.fraki.robimycos.data.businessmodels;

import java.util.Date;

/**
 * Created by bambo on 14.10.2017.
 */
public class Message extends MessageForm {

    private Date creationDate;
    private String author;

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
