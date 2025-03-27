package RocketChatAPI.dto;

public class MessageDTO {
    private String channel;
    private String text;

    // Getters and Setters
    public String getChannel() { return channel; }
    public void setChannel(String channel) { this.channel = channel; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
}