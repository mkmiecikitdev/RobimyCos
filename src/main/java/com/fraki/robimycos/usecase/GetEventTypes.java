package com.fraki.robimycos.usecase;

import com.fraki.robimycos.data.businessmodels.EventType;
import com.fraki.robimycos.data.daos.EventTypeDAO;
import com.fraki.robimycos.data.mappers.EventTypeMapper;
import com.fraki.robimycos.usecase.base.UseCase;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by bambo on 10.10.2017.
 */

@Component
public class GetEventTypes extends UseCase<GetEventTypes.Request, List<EventType>> {

    private EventTypeDAO eventTypeDAO;

    public GetEventTypes(EventTypeDAO eventTypeDAO) {
        this.eventTypeDAO = eventTypeDAO;
    }

    @Override
    public List<EventType> response(Request request) {
        return EventTypeMapper.convertToEventTypeList(eventTypeDAO.findAll());
    }

    public static class Request implements UseCase.Request {

    }

}
