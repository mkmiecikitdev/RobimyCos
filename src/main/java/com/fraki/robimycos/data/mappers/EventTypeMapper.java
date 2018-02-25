package com.fraki.robimycos.data.mappers;

import com.fraki.robimycos.data.businessmodels.EventType;
import com.fraki.robimycos.data.entities.EventTypeEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by bambo on 10.10.2017.
 */
public class EventTypeMapper {

    public static EventType convertToEventType(EventTypeEntity entity) {
        EventType eventType = new EventType();
        eventType.setId(entity.getId());
        eventType.setName(entity.getName());
        return eventType;
    }

    public static List<EventType> convertToEventTypeList(List<EventTypeEntity> entityList) {
        return entityList.stream().map(EventTypeMapper::convertToEventType).collect(Collectors.toList());
    }

}
