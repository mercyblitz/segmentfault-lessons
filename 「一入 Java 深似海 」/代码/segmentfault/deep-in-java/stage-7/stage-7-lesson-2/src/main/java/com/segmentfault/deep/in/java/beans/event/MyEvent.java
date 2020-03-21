package com.segmentfault.deep.in.java.beans.event;

public class MyEvent extends ApplicationEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public MyEvent(Object source) {
        super(source);
    }
}
