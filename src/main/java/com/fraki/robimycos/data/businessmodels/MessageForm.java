package com.fraki.robimycos.data.businessmodels;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

/**
 * Created by bambo on 14.10.2017.
 */
public class MessageForm {

    @Column(nullable = false)
    @NotNull
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
