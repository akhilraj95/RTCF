package com.csrg.dsynk.manager.services.session;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
public class SessionService {

    private static final String TOPIC_PREFIX = "/topic/";
    private Map<String, Session> sessionMap;

    public SessionService() {

        this.sessionMap = new HashMap<>();
    }

    public Session createSession() {

        String id = getRandomId();
        if(sessionMap.containsKey(id)) {
            throw new RuntimeException("Duplicate Session Id Created");
        }

        Session session = new Session(id, TOPIC_PREFIX + id);
        sessionMap.put(id, session);
        return session;
    }

    public Optional<Session> getSession(String id) {

        if(!sessionMap.containsKey(id)) {
            return Optional.empty();
        }

        return Optional.ofNullable(sessionMap.get(id));
    }

    private String getRandomId() {

        return UUID.randomUUID().toString();
    }
}




