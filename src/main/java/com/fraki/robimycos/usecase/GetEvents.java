package com.fraki.robimycos.usecase;

import com.fraki.robimycos.data.businessmodels.Event;
import com.fraki.robimycos.data.daos.EventsDAO;
import com.fraki.robimycos.data.mappers.EventsMapper;
import com.fraki.robimycos.usecase.base.UseCase;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

/**
 * Created by bambo on 10.10.2017.
 */

@Component
public class GetEvents extends UseCase<GetEvents.Request, List<Event>> {

    private EventsDAO eventsDAO;

    public GetEvents(EventsDAO eventsDAO) {
        this.eventsDAO = eventsDAO;
    }

    @Override
    public List<Event> response(Request request) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -30);
        return EventsMapper.convertToEventList(eventsDAO.findActiveEvents(request.login, calendar.getTime()));
    }

    public static class Request implements UseCase.Request {
        private String login;

        public Request(String login) {
            this.login = login;
        }
    }

}
