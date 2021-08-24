package response;

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

    @Override
    public String toString() {
        return "Message{" +
                "code=" + code +
                ", text='" + text + '\'' +
                '}';
    }
}
