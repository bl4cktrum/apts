package dev.bl4cktrum.apts.config;

import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SocketIOConfig {

    @Value("${socket-server.port}")
    private int socketPort;
    @Value("${socket-server.hostname}")
    private String socketHostname;

    @Bean
    public SocketIOServer socketIOServer(){
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setHostname(socketHostname);
        config.setPort(socketPort);
        return new SocketIOServer(config);
    }
}
