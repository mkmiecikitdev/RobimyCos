package com.fraki.robimycos.data.mappers;

import com.fraki.robimycos.data.businessmodels.Conversation;
import com.fraki.robimycos.data.entities.ConversationEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by bambo on 14.10.2017.
 */
public class ConversationsMapper {

    public static Conversation convertToConversation(ConversationEntity entity) {
        Conversation conversation = new Conversation();
        conversation.setId(entity.getId());
        conversation.setLastMsgDate(entity.getLastMsgDate());
        conversation.setUser1(UserMapper.convertToUser(entity.getUser1()));
        conversation.setUser2(UserMapper.convertToUser(entity.getUser2()));
        conversation.setMessages(MessagesMapper.convertToMessageList(new ArrayList<>(entity.getMessages())));
        return conversation;
    }

    public static List<Conversation> convertToConversationList(List<ConversationEntity> entityList) {
        return entityList.stream().map(ConversationsMapper::convertToConversation).collect(Collectors.toList());
    }

}

