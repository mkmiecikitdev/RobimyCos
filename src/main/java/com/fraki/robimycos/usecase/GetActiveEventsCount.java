package com.fraki.robimycos.usecase;

import com.fraki.robimycos.data.daos.EventsDAO;
import com.fraki.robimycos.data.mappers.EventsMapper;
import com.fraki.robimycos.usecase.base.UseCase;
import org.springframework.stereotype.Component;

import java.util.Calendar;

/**
 * Created by bambo on 11.10.2017.
 */
@Component
public class GetActiveEventsCount extends UseCase<GetActiveEventsCount.Request, Short> {

    private EventsDAO eventsDAO;

    public GetActiveEventsCount(EventsDAO eventsDAO) {
        this.eventsDAO = eventsDAO;
    }

    @Override
    public Short response(Request request) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -30);
        return eventsDAO.getActiveEventsCount(request.login, calendar.getTime());
    }

    public static class Request implements UseCase.Request {
        private String login;

        public Request(String login) {
            this.login = login;
        }
    }

}
