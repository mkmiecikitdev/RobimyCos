package com.fraki.robimycos.usecase;

import com.fraki.robimycos.data.businessmodels.Conversation;
import com.fraki.robimycos.data.businessmodels.Message;
import com.fraki.robimycos.data.businessmodels.MessageForm;
import com.fraki.robimycos.data.businessmodels.User;
import com.fraki.robimycos.data.daos.ConversationsDAO;
import com.fraki.robimycos.data.entities.ConversationEntity;
import com.fraki.robimycos.data.entities.embeddable.MessageEmbeddable;
import com.fraki.robimycos.data.firebasemodels.MessageJSON;
import com.fraki.robimycos.data.mappers.ConversationsMapper;
import com.fraki.robimycos.data.mappers.MessagesMapper;
import com.fraki.robimycos.exceptions.ResourceNotFoundException;
import com.fraki.robimycos.services.FirebaseService;
import com.fraki.robimycos.usecase.base.UseCase;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * Created by bambo on 14.10.2017.
 */

@Component
public class SendMessage extends UseCase<SendMessage.Request, Conversation>{

    private ConversationsDAO conversationsDAO;
    private FirebaseService firebaseService;

    public SendMessage(ConversationsDAO conversationsDAO, FirebaseService firebaseService) {
        this.conversationsDAO = conversationsDAO;
        this.firebaseService = firebaseService;
    }

    @Transactional
    @Override
    public Conversation response(Request request) {
        MessageForm form = request.messageForm;
        long id = request.conversationId;
        String login = request.login;

        ConversationEntity conversationEntity = conversationsDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException("Conversation", id));
        MessageEmbeddable message = new MessageEmbeddable();
        message.setCreationDate(new Date());
        message.setText(form.getText());
        message.setAuthor(login);
        conversationEntity.getMessages().add(message);
        conversationEntity.setLastMsgDate(new Date());

        Conversation conversation = ConversationsMapper.convertToConversation(conversationEntity);
        notifyMessage(conversation, MessagesMapper.convertToMessage(message), login, firebaseService);
        return conversation;
    }

    @Async
    private static void notifyMessage(Conversation conversation, Message message, String fromLogin, FirebaseService firebaseService) {
        User fromUser;
        User toUser;

        if(conversation.getUser1().getLogin().equals(fromLogin)) {
            fromUser = conversation.getUser1();
            toUser = conversation.getUser2();
        } else {
            fromUser = conversation.getUser2();
            toUser = conversation.getUser1();
        }

        MessageJSON json = new MessageJSON(message, conversation.getId());

        firebaseService.notifyNewMessage(toUser.getTokenFirebase(), fromUser.getLogin(), json);

    }

    public static class Request implements UseCase.Request {
        private MessageForm messageForm;
        private long conversationId;
        private String login;

        public Request(MessageForm messageForm, long conversationId, String login) {
            this.messageForm = messageForm;
            this.conversationId = conversationId;
            this.login = login;
        }
    }

}
