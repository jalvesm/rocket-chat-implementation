package RocketChatAPI.controller;

import RocketChatAPI.dto.UserDTO;
import RocketChatAPI.service.RocketChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final RocketChatService rocketChatService;

    public UserController(RocketChatService rocketChatService) {
        this.rocketChatService = rocketChatService;
    }

    @PostMapping
    public Mono<ResponseEntity<String>> createUser(@Validated @RequestBody UserDTO userDTO) {
        return rocketChatService.createUser(userDTO)
                .map(response -> ResponseEntity.ok().body(response))
                .onErrorResume(e -> Mono.just(
                        ResponseEntity.status(500).body("Error: " + e.getMessage())
                ));
    }
}

/*
Método: POST

Headers:
Content-Type: application/json
X-Auth-Token: JEw3pe5Mp9VWLDs6
X-User-Id: zu94xTBj

Endpoint: http://localhost:3000/api/v1/users.create
{
    "name": "New User",
    "email": "user@example.com",
    "password": "securePassword123",
    "username": "newuser"
}
*/