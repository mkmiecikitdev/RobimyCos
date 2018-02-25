package com.fraki.robimycos.usecase;

import com.fraki.robimycos.data.businessmodels.Conversation;
import com.fraki.robimycos.data.daos.ConversationsDAO;
import com.fraki.robimycos.data.daos.UsersDAO;
import com.fraki.robimycos.data.entities.ConversationEntity;
import com.fraki.robimycos.data.entities.UserEntity;
import com.fraki.robimycos.data.mappers.ConversationsMapper;
import com.fraki.robimycos.exceptions.ResourceNotFoundException;
import com.fraki.robimycos.exceptions.UserWithIdNotFoundException;
import com.fraki.robimycos.exceptions.UserWithLoginNotFoundException;
import com.fraki.robimycos.usecase.base.UseCase;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by bambo on 14.10.2017.
 */

@Component
public class GetConversation extends UseCase<GetConversation.Request, Conversation> {

    private ConversationsDAO conversationsDAO;
    private UsersDAO usersDAO;

    public GetConversation(ConversationsDAO conversationsDAO, UsersDAO usersDAO) {
        this.conversationsDAO = conversationsDAO;
        this.usersDAO = usersDAO;
    }

    @Override
    public Conversation response(Request request) {
        long id = request.id;
        if(id > 0) {
            return ConversationsMapper.convertToConversation(conversationsDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException("Conversation", id)));
        }

        String login = request.login;
        long userId = request.userId;

        ConversationEntity conversationEntity = conversationsDAO.findByUsers(login, userId);
        if(conversationEntity == null) {
            conversationEntity = new ConversationEntity();
            UserEntity user1 = usersDAO.getByLogin(login).orElseThrow(() -> new UserWithLoginNotFoundException(login));
            UserEntity user2 = usersDAO.getById(userId).orElseThrow(() -> new UserWithIdNotFoundException(userId));
            conversationEntity.setUser1(user1);
            conversationEntity.setUser2(user2);
        }

//        conversationEntity.getMessages().clear();
        conversationEntity.setLastMsgDate(new Date());

        return ConversationsMapper.convertToConversation(conversationsDAO.save(conversationEntity));
    }

    public static class Request implements UseCase.Request {
        private String login;
        private long userId;

        private long id;

        public Request(String login, long userId) {
            this.login = login;
            this.userId = userId;
        }

        public Request(long id) {
            this.id = id;
        }
    }
}
