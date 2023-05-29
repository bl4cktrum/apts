package dev.bl4cktrum.apts.socketIO;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import dev.bl4cktrum.apts.api.services.LocationService;
import dev.bl4cktrum.apts.api.services.RelevantService;
import dev.bl4cktrum.apts.security.JwtService;
import dev.bl4cktrum.apts.socketIO.models.SetSessionModel;
import dev.bl4cktrum.apts.socketIO.models.WatchLocationModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SocketModule {

    private final SocketIOServer socketIOServer;
    private final RelevantService relevantService;
    private final LocationService locationService;
    private final JwtService jwtService;

    public SocketModule(SocketIOServer socketIOServer, RelevantService relevantService, LocationService locationService, JwtService jwtService) {
        this.socketIOServer = socketIOServer;
        this.relevantService = relevantService;
        this.locationService = locationService;
        this.jwtService = jwtService;
        socketIOServer.addConnectListener(onConnected());
        socketIOServer.addDisconnectListener(onDisconnected());
        socketIOServer.addEventListener("watch_location", WatchLocationModel.class, onGetWatchLocation());
        socketIOServer.addEventListener("set_session", SetSessionModel.class, onSetSession());
    }

    private DataListener<SetSessionModel> onSetSession() {
        return (client, data, ackSender) -> {
            String email = jwtService.extractUserEmail(data.getToken());
            this.relevantService.setSession(email,client.getSessionId());
            log.info(String.format("User: %s | Session: %s Setted Successfully",email,client.getSessionId().toString()));
        };
    }

    private DataListener<WatchLocationModel> onGetWatchLocation() {
        return (client, data, ackSender) -> {
            locationService.processNewLocation(data.getDevice_id(),data.getLatitude(),data.getLongitude());
            ackSender.sendAckData("ok");
        };
    }

    private ConnectListener onConnected() {
        return socketIOClient -> {
            log.info(String.format("Connected SockedID: %s",socketIOClient.getSessionId().toString()));
        };
    }

    private DisconnectListener onDisconnected() {
        return socketIOClient -> {
            log.info(String.format("Disconnected SockedID: %s",socketIOClient.getSessionId().toString()));
        };
    }
}
