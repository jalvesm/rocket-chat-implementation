package RocketChatAPI.dto;

import java.util.List;

import lombok.Value;

@Value
public class UserDTO {
    private String name;
    private String email;
    private String password;
    private String username;
    private boolean active = true;  // Required by Rocket.Chat
    private List<String> roles = List.of("user");  // Required
}