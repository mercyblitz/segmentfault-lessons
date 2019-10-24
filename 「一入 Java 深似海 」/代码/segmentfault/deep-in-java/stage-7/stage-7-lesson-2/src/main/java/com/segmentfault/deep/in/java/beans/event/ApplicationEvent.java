package com.segmentfault.deep.in.java.beans.event;

import java.util.EventObject;

public class ApplicationEvent extends EventObject {

    private final long timestamp;

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ApplicationEvent(Object source) {
        super(source);
        this.timestamp = System.currentTimeMillis();
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "ApplicationEvent{" +
                "timestamp=" + timestamp +
                ", source=" + source +
                '}';
    }
}
