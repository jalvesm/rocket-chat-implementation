package RocketChatAPI.service;

import RocketChatAPI.dto.ChannelDTO;
import RocketChatAPI.dto.MessageDTO;
import RocketChatAPI.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class RocketChatService {
    private final WebClient webClient;

    public RocketChatService(
            @Value("${rocket.chat.url}") String baseUrl,
            @Value("${rocket.chat.user-id}") String userId,
            @Value("${rocket.chat.auth-token}") String authToken) {

        this.webClient = WebClient.builder()
                .baseUrl(baseUrl + "/api/v1")
                .defaultHeader("X-Auth-Token", authToken)
                .defaultHeader("X-User-Id", userId)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    public Mono<String> createChannel(ChannelDTO channelDTO) {
        return webClient.post()
                .uri("/channels.create")
                .bodyValue(channelDTO)
                .retrieve()
                .bodyToMono(String.class)
                .doOnSuccess(response -> System.out.println("Channel created: " + response))
                .doOnError(error -> System.err.println("Error creating channel: " + error.getMessage()));
    }

    // Send message to channel
    public Mono<String> sendMessage(MessageDTO messageDTO) {
        return webClient.post()
                .uri("/chat.postMessage")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Map.of(
                        "channel", messageDTO.getChannel(),
                        "text", messageDTO.getText()
                ))
                .retrieve()
                .onStatus(HttpStatusCode::isError, response ->
                        response.bodyToMono(String.class)
                                .flatMap(errorBody -> Mono.error(new RuntimeException(
                                        "Rocket.Chat API error: " + errorBody
                                )))
                )
                .bodyToMono(String.class);
    }

    // Create user
    public Mono<String> createUser(UserDTO userDTO) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", userDTO.getName());
        requestBody.put("email", userDTO.getEmail());
        requestBody.put("password", userDTO.getPassword());
        requestBody.put("username", userDTO.getUsername());
        requestBody.put("active", userDTO.isActive());
        requestBody.put("roles", userDTO.getRoles());
        requestBody.put("joinDefaultChannels", true);  // Important for new users

        return webClient.post()
                .uri("/users.create")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class);
    }
}