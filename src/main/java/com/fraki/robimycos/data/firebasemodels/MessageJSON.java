package com.fraki.robimycos.data.firebasemodels;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fraki.robimycos.data.businessmodels.Message;

import java.text.SimpleDateFormat;

/**
 * Created by bambo on 14.10.2017.
 */
public class MessageJSON {

    private String text;
    private String author;
    private String date;
    private String conversationId;

    public MessageJSON(Message message, long conversationId) {
        this.text = message.getText();
        this.author = message.getAuthor();
        this.date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(message.getCreationDate());
        this.conversationId = String.valueOf(conversationId);
    }

    @Override
    public String toString() {
        String json = "";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        try {
            json = objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            System.out.print(e);
            e.printStackTrace();
        }

        return json;
    }
}
