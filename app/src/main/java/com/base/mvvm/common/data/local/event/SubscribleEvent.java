package com.base.mvvm.common.data.local.event;

public class SubscribleEvent {
    private int eventId;
    private String startDate;

    public SubscribleEvent (int id , String startDate){
        this.eventId = id;
        this.startDate = startDate;
    }
    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
