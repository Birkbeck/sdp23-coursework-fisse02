package sml;

public class MainMessageProvider implements MessageProvider {

    private String message = "Hello...";

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }
}