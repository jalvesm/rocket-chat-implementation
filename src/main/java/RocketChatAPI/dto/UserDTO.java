package RocketChatAPI.dto;

import java.util.List;

public class UserDTO {
    private String name;
    private String email;
    private String password;
    private String username;
    private boolean active = true;  // Required by Rocket.Chat
    private List<String> roles = List.of("user");  // Required

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    public List<String> getRoles() { return roles; }
    public void setRoles(List<String> roles) { this.roles = roles; }
}