package RocketChatAPI.dto;

// quais endpoints são interessantes de implementar com websocket
// verificar essa questão do proxy reverso
// autenticação SSO;
// Alterar para não verificar email quando o usuário é criado;

import lombok.Value;

@Value
public class ChannelDTO {
    private String name;
}