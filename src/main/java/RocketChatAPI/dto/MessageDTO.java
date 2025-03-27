package RocketChatAPI.dto;

import lombok.Value;

@Value
public class MessageDTO {
    private String channel;
    private String text;
}