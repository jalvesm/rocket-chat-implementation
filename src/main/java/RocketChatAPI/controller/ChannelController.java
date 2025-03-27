package RocketChatAPI.controller;

import RocketChatAPI.dto.ChannelDTO;
import RocketChatAPI.service.RocketChatService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/channels")
public class ChannelController {
    private final RocketChatService rocketChatService;

    public ChannelController(RocketChatService rocketChatService) {
        this.rocketChatService = rocketChatService;
    }

    @PostMapping
    public Mono<ResponseEntity<String>> createChannel(@RequestBody ChannelDTO channelDTO) {
        return rocketChatService.createChannel(channelDTO)
                .map(response -> ResponseEntity.ok().body(response))
                .onErrorResume(e -> Mono.just(
                        ResponseEntity.internalServerError().body("Error: " + e.getMessage())
                ));
    }
}

/*
Method: POST
URL: http://localhost:3000/api/v1/channels.create

Headers:
Content-Type: application/json
X-Auth-Token: JEw3pe5Mp9VWLDs6
X-User-Id: zu94xTBj

Body (raw JSON):
{
    "name": "postman-channel"
}
*/