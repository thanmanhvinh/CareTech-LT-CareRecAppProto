package com.base.mvvm.common.data.local.event;

import java.util.List;

public class ListSubscribleEvent {
    private List<SubscribleEvent> subscribleEvents;

    public List<SubscribleEvent> getSubscribleEvents() {
        return subscribleEvents;
    }

    public void setSubscribleEvents(List<SubscribleEvent> subscribleEvents) {
        this.subscribleEvents = subscribleEvents;
    }
}
