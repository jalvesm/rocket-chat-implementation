package RocketChatAPI.controller;

import RocketChatAPI.dto.MessageDTO;
import RocketChatAPI.service.RocketChatService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final RocketChatService rocketChatService;

    public MessageController(RocketChatService rocketChatService) {
        this.rocketChatService = rocketChatService;
    }

    @PostMapping
    public Mono<String> sendMessage(@RequestBody MessageDTO messageDTO) {
        return rocketChatService.sendMessage(messageDTO);
    }
}

/*
Método: POST
ENDPOINT: http://localhost:3000/api/v1/chat.postMessage

Headers:
Content-Type: application/json
X-Auth-Token: JEw3pe5Mp9VWLDs6
X-User-Id: zu94xTBj

BODY:
{
    "channel": "test-channel",
    "text": "Testando direto na API"
}
*/