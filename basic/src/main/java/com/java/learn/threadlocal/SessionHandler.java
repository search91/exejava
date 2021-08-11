package com.java.learn.threadlocal;

import lombok.Data;

/**
 * @author hzliuzhujie
 * @date 2021-01-27
 **/
public class SessionHandler {
    public static ThreadLocal<Session> session = new ThreadLocal<Session>();

    @Data
    public static class Session {
        private String id;
        private String user;
        private String status;
    }

    public void createSession() {
        session.set(new Session());
    }

    public String getUser() {
        return session.get().getUser();
    }

    public String getStatus() {
        return session.get().getStatus();
    }

    public void setStatus(String status) {
        session.get().setStatus(status);
    }

    public static void main(String[] args) {
        new Thread(() -> {
            SessionHandler handler = new SessionHandler();
            handler.getStatus();
            handler.getUser();
            handler.setStatus("close");
            handler.getStatus();
        }).start();
    }
}