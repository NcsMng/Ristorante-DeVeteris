package ristorante.de.veteris.response;

public class Message {
    private final int code;
    private final String text;

    public Message(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

}
