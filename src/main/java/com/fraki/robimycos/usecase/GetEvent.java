package com.fraki.robimycos.usecase;

import com.fraki.robimycos.data.businessmodels.Event;
import com.fraki.robimycos.data.daos.EventsDAO;
import com.fraki.robimycos.data.mappers.EventsMapper;
import com.fraki.robimycos.exceptions.EventWithIdNotFoundException;
import com.fraki.robimycos.usecase.base.UseCase;
import org.springframework.stereotype.Component;

/**
 * Created by bambo on 11.10.2017.
 */

@Component
public class GetEvent extends UseCase<GetEvent.Request, Event> {

    private EventsDAO eventsDAO;

    public GetEvent(EventsDAO eventsDAO) {
        this.eventsDAO = eventsDAO;
    }

    @Override
    public Event response(Request request) {
        return EventsMapper.convertToEvent(eventsDAO.findById(request.id).orElseThrow(() -> new EventWithIdNotFoundException(request.id)));
    }

    public static class Request implements UseCase.Request {
        private long id;

        public Request(long id) {
            this.id = id;
        }
    }
}
