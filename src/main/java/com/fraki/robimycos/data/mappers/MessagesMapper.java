package com.fraki.robimycos.data.mappers;

import com.fraki.robimycos.data.businessmodels.Message;
import com.fraki.robimycos.data.entities.embeddable.MessageEmbeddable;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by bambo on 14.10.2017.
 */
public class MessagesMapper {

    public static Message convertToMessage(MessageEmbeddable messageEmbeddable) {
        Message message = new Message();
        message.setText(messageEmbeddable.getText());
        message.setAuthor(messageEmbeddable.getAuthor());
        message.setCreationDate(messageEmbeddable.getCreationDate());
        return message;
    }

    public static List<Message> convertToMessageList(List<MessageEmbeddable> messageEmbeddableList) {
        return messageEmbeddableList.stream().map(MessagesMapper::convertToMessage).collect(Collectors.toList());
    }

}
