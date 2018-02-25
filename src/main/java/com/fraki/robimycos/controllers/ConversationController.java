package com.fraki.robimycos.controllers;

import com.fraki.robimycos.config.security.TokenAuthenticationService;
import com.fraki.robimycos.data.businessmodels.Conversation;
import com.fraki.robimycos.data.businessmodels.MessageForm;
import com.fraki.robimycos.usecase.GetActiveConversations;
import com.fraki.robimycos.usecase.GetConversation;
import com.fraki.robimycos.usecase.SendMessage;
import com.fraki.robimycos.usecase.base.UseCase;
import com.fraki.robimycos.usecase.base.UseCaseExecutor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by bambo on 14.10.2017.
 */

@RestController
public class ConversationController {

    private UseCaseExecutor executor;
    private UseCase<GetConversation.Request, Conversation> getNewConversation;
    private UseCase<SendMessage.Request, Conversation> sendMessage;
    private UseCase<GetActiveConversations.Request, List<Conversation>> getActiveConversations;

    public ConversationController(UseCaseExecutor executor, UseCase<GetConversation.Request, Conversation> getNewConversation, UseCase<SendMessage.Request, Conversation> sendMessage, UseCase<GetActiveConversations.Request, List<Conversation>> getActiveConversations) {
        this.executor = executor;
        this.getNewConversation = getNewConversation;
        this.sendMessage = sendMessage;
        this.getActiveConversations = getActiveConversations;
    }

    @GetMapping("/conversations")
    public List<Conversation> getActiveConversations(HttpServletRequest request) {
        String login = TokenAuthenticationService.getUserLoginFromHeader(request);
        return executor.response(getActiveConversations, new GetActiveConversations.Request(login));
    }

    @GetMapping("/conversationwith/{userId}")
    public Conversation getNewConversation(@PathVariable long userId, HttpServletRequest request) {
        String login = TokenAuthenticationService.getUserLoginFromHeader(request);
        return executor.response(getNewConversation, new GetConversation.Request(login, userId));
    }

    @GetMapping("/conversation/{id}")
    public Conversation getConversation(@PathVariable long id, HttpServletRequest request) {
        return executor.response(getNewConversation, new GetConversation.Request(id));
    }

    @PostMapping("conversation/{id}/addmessage")
    public Conversation addMessage(@RequestBody MessageForm form, @PathVariable long id, HttpServletRequest request) {
        String login = TokenAuthenticationService.getUserLoginFromHeader(request);
        return executor.response(sendMessage, new SendMessage.Request(form, id, login));
    }

}
