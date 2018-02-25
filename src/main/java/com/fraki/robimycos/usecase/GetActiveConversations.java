package com.fraki.robimycos.usecase;

import com.fraki.robimycos.data.businessmodels.Conversation;
import com.fraki.robimycos.data.daos.ConversationsDAO;
import com.fraki.robimycos.data.mappers.ConversationsMapper;
import com.fraki.robimycos.usecase.base.UseCase;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by bambo on 15.10.2017.
 */

@Component
public class GetActiveConversations extends UseCase<GetActiveConversations.Request, List<Conversation>> {

    private ConversationsDAO conversationsDAO;

    public GetActiveConversations(ConversationsDAO conversationsDAO) {
        this.conversationsDAO = conversationsDAO;
    }

    @Override
    public List<Conversation> response(Request request) {
        return ConversationsMapper.convertToConversationList(conversationsDAO.findConversationsByLogin(request.login));
    }

    public static class Request implements UseCase.Request {
        private String login;

        public Request(String login) {
            this.login = login;
        }
    }
}
