package com.fraki.robimycos.data.mappers;

import com.fraki.robimycos.data.businessmodels.Event;
import com.fraki.robimycos.data.entities.EventEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by bambo on 10.10.2017.
 */
public class EventsMapper {

    public static Event convertToEvent(EventEntity entity) {
        Event event = new Event();
        event.setId(entity.getId());
        event.setDate(entity.getDate());
        event.setTimeOption(entity.getTimeOption());
        event.setAccept(entity.isAccept());
        event.setFinish(entity.isFinish());
        event.setEventType(EventTypeMapper.convertToEventType(entity.getEventType()));
        event.setFromUser(UserMapper.convertToUser(entity.getFromUser()));
        event.setToUser(UserMapper.convertToUser(entity.getToUser()));
        return event;
    }

    public static List<Event> convertToEventList(List<EventEntity> entityList) {
        return entityList.stream().map(EventsMapper::convertToEvent).collect(Collectors.toList());
    }

}
