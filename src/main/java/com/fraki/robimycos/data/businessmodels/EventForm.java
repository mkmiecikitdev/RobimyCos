package com.fraki.robimycos.data.businessmodels;

import javax.validation.constraints.NotNull;

/**
 * Created by bambo on 10.10.2017.
 */
public class EventForm {

    @NotNull
    private long userId;

    @NotNull
    private long eventTypeId;

    @NotNull
    private String timeOption;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(long eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public String getTimeOption() {
        return timeOption;
    }

    public void setTimeOption(String timeOption) {
        this.timeOption = timeOption;
    }
}
