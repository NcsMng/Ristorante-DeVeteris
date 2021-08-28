package com.deveteris.commons.response;

public class Message {
    private final String code;
    private final String text;

    public Message(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

}
