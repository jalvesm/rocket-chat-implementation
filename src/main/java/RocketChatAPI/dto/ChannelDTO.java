package RocketChatAPI.dto;

// lombok pra gerar getter & setter
// repositório git
// quais endpoints são interessantes de implementar com websocket
// verificar essa questão do proxy reverso
// autenticação SSO;
public class ChannelDTO {
    private String name;

    // Constructors
    public ChannelDTO() {}
    public ChannelDTO(String name) { this.name = name; }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}