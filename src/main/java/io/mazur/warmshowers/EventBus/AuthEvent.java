package io.mazur.warmshowers.EventBus;

import io.mazur.warmshowers.Model.Session;

public class AuthEvent {
    public Session session;

    public AuthEvent(Session session) {
        this.session = session;
    }
}
