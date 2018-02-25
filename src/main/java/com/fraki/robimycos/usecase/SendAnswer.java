package com.fraki.robimycos.usecase;

import com.fraki.robimycos.data.businessmodels.Event;
import com.fraki.robimycos.data.daos.EventsDAO;
import com.fraki.robimycos.data.entities.EventEntity;
import com.fraki.robimycos.data.mappers.EventsMapper;
import com.fraki.robimycos.exceptions.EventWithIdNotFoundException;
import com.fraki.robimycos.services.FirebaseService;
import com.fraki.robimycos.usecase.base.UseCase;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Created by bambo on 10.10.2017.
 */

@Component
public class SendAnswer extends UseCase<SendAnswer.Request, Event> {

    private EventsDAO eventsDAO;
    private FirebaseService firebaseService;

    public SendAnswer(EventsDAO eventsDAO, FirebaseService firebaseService) {
        this.eventsDAO = eventsDAO;
        this.firebaseService = firebaseService;
    }

    @Transactional
    @Override
    public Event response(Request request) {
        long id = request.eventId;
        Event event = update(id, request.answer);
        notify(event, firebaseService);
        return event;
    }


    private Event update(long id, String answer) {
        EventEntity eventEntity = eventsDAO.findById(id).orElseThrow(() -> new EventWithIdNotFoundException(id));
        eventEntity.setFinish(true);
        eventEntity.setAccept(answer.equals("yes"));
        return EventsMapper.convertToEvent(eventEntity);
    }

    @Async
    private static void notify(Event event, FirebaseService firebaseService) {
        firebaseService.notifyAnswerEvent(event);
    }

    public static class Request implements UseCase.Request {

        private long eventId;
        private String answer;

        public Request(long eventId, String answer) {
            this.eventId = eventId;
            this.answer = answer;
        }
    }

}
