package com.fraki.robimycos.data.entities.embeddable;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by bambo on 14.10.2017.
 */

@Embeddable
public class MessageEmbeddable {

    @NotNull
    @Column(nullable = false)
    @NotEmpty
    private String text;

    @NotNull
    @Column(nullable = false)
    private Date creationDate;

    @NotNull
    @Column(nullable = false)
    private String author;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

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
