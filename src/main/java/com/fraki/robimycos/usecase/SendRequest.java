package com.fraki.robimycos.usecase;

import com.fraki.robimycos.data.businessmodels.Event;
import com.fraki.robimycos.data.businessmodels.EventForm;
import com.fraki.robimycos.data.daos.EventTypeDAO;
import com.fraki.robimycos.data.daos.EventsDAO;
import com.fraki.robimycos.data.daos.UsersDAO;
import com.fraki.robimycos.data.entities.EventEntity;
import com.fraki.robimycos.data.entities.EventTypeEntity;
import com.fraki.robimycos.data.entities.UserEntity;
import com.fraki.robimycos.data.mappers.EventsMapper;
import com.fraki.robimycos.exceptions.EventTypeWithIdNotFoundException;
import com.fraki.robimycos.exceptions.UserWithIdNotFoundException;
import com.fraki.robimycos.exceptions.UserWithLoginNotFoundException;
import com.fraki.robimycos.services.FirebaseService;
import com.fraki.robimycos.usecase.base.UseCase;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by bambo on 10.10.2017.
 */

@Component
public class SendRequest extends UseCase<SendRequest.Request, Event>{

    private UsersDAO usersDAO;
    private EventTypeDAO eventTypeDAO;
    private EventsDAO eventsDAO;
    private FirebaseService firebaseService;

    public SendRequest(UsersDAO usersDAO, EventTypeDAO eventTypeDAO, EventsDAO eventsDAO, FirebaseService firebaseService) {
        this.usersDAO = usersDAO;
        this.eventTypeDAO = eventTypeDAO;
        this.eventsDAO = eventsDAO;
        this.firebaseService = firebaseService;
    }

    @Override
    public Event response(Request request) {
        String login = request.login;
        long userId = request.eventForm.getUserId();
        long typeId = request.eventForm.getEventTypeId();
        UserEntity me = usersDAO.getByLogin(login).orElseThrow(() -> new UserWithLoginNotFoundException(login));
        UserEntity to = usersDAO.findById(userId).orElseThrow(() -> new UserWithIdNotFoundException(userId));
        EventTypeEntity type = eventTypeDAO.getById(typeId).orElseThrow(() -> new EventTypeWithIdNotFoundException(typeId));

        EventEntity eventEntity = new EventEntity();
        eventEntity.setFromUser(me);
        eventEntity.setToUser(to);
        eventEntity.setDate(new Date());
        eventEntity.setEventType(type);
        eventEntity.setTimeOption(request.eventForm.getTimeOption());
        Event event = EventsMapper.convertToEvent(eventsDAO.save(eventEntity));
        notifyEvent(event, firebaseService);
        return event;
    }

    @Async
    private static void notifyEvent(Event event, FirebaseService firebaseService) {
        firebaseService.notifyGetEvent(event);
    }

    public static class Request implements UseCase.Request {
        private EventForm eventForm;
        private String login;

        public Request(EventForm eventForm, String login) {
            this.eventForm = eventForm;
            this.login = login;
        }
    }

}
