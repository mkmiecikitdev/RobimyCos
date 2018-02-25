package com.fraki.robimycos.controllers;

import com.fraki.robimycos.config.security.TokenAuthenticationService;
import com.fraki.robimycos.data.businessmodels.Event;
import com.fraki.robimycos.data.businessmodels.EventForm;
import com.fraki.robimycos.data.businessmodels.EventType;
import com.fraki.robimycos.usecase.*;
import com.fraki.robimycos.usecase.base.UseCase;
import com.fraki.robimycos.usecase.base.UseCaseExecutor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by bambo on 10.10.2017.
 */

@RestController
public class EventsController {

    private UseCaseExecutor executor;
    private UseCase<GetEventTypes.Request, List<EventType>> getEventTypes;
    private UseCase<SendRequest.Request, Event> sendRequest;
    private UseCase<GetEvents.Request, List<Event>> getEvents;
    private UseCase<SendAnswer.Request, Event> sendAnswer;
    private UseCase<GetEvent.Request, Event> getEvent;
    private UseCase<GetActiveEventsCount.Request, Short> getActiveEventsCount;

    public EventsController(UseCaseExecutor executor, UseCase<GetEventTypes.Request, List<EventType>> getEventTypes, UseCase<SendRequest.Request, Event> sendRequest, UseCase<GetEvents.Request, List<Event>> getEvents, UseCase<SendAnswer.Request, Event> sendAnswer, UseCase<GetEvent.Request, Event> getEvent, UseCase<GetActiveEventsCount.Request, Short> getActiveEventsCount) {
        this.executor = executor;
        this.getEventTypes = getEventTypes;
        this.sendRequest = sendRequest;
        this.getEvents = getEvents;
        this.sendAnswer = sendAnswer;
        this.getEvent = getEvent;
        this.getActiveEventsCount = getActiveEventsCount;
    }

    @GetMapping("/eventtypes")
    public List<EventType> getEventTypes() {
        return executor.response(getEventTypes, new GetEventTypes.Request());
    }

    @GetMapping("/events")
    public List<Event> getEvents(HttpServletRequest request) {
        String login = TokenAuthenticationService.getUserLoginFromHeader(request);
        return executor.response(getEvents, new GetEvents.Request(login));
    }

    @GetMapping("/events/count")
    public Short getEventsCount(HttpServletRequest request) {
        String login = TokenAuthenticationService.getUserLoginFromHeader(request);
        return executor.response(getActiveEventsCount, new GetActiveEventsCount.Request(login));
    }

    @GetMapping("/event/{id}")
    public Event getEvent(@PathVariable long id) {
        return executor.response(getEvent, new GetEvent.Request(id));
    }

    @PostMapping("/send")
    public Event sendRequest(HttpServletRequest request, @RequestBody @Valid EventForm eventForm) {
        String login = TokenAuthenticationService.getUserLoginFromHeader(request);
        return executor.response(sendRequest, new SendRequest.Request(eventForm, login));
    }

    @GetMapping("/answer/{id}/{answer}")
    public Event sendAnswer(HttpServletRequest request, @PathVariable long id, @PathVariable String answer) {
        return executor.response(sendAnswer, new SendAnswer.Request(id, answer));
    }
}
