package com.polimigo.benevolent.views.events;

public interface BaseEvent {
    public abstract void onStartedL();
    public abstract void onSuccessL();
    public abstract void onFailerL();

}
