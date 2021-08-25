package com.deveteris.commons.response;

import java.util.ArrayList;
import java.util.List;

public class DeVeterisResponse<T> {
    private List<Message> messages = new ArrayList<>();
    private T body;

    public DeVeterisResponse(T body) {
        this.body = body;
    }

    public DeVeterisResponse(final Message message) {
        this.messages.add(message);
    }

    public DeVeterisResponse(final List<Message> messages) {
        this.messages.addAll(messages);
    }

    public DeVeterisResponse(){}


    public static <T> DeVeterisResponse<T> error(final int code, final String text) {
        return new DeVeterisResponse<>(new Message(code, text));
    }
    public List<Message> getMessages() {        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
