package com.example.macosx.ltm.network;

import com.example.macosx.ltm.ultils.Constant;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

@ClientEndpoint
public class Socket {

    private Session session = null;
    private Gson gson = new Gson();

    public Socket() throws Exception {
        URI uri = new URI(Constant.BASE_URL_SOCKET);
        session = ContainerProvider.getWebSocketContainer().connectToServer(this, uri);
    }



    public Session getSession() {
        return session;
    }

    @OnOpen
    public void handleOpen(Session session) {
        System.out.println("Connected to Server!");
    }

    @OnMessage
    public void handleMessage(String message) {
        System.err.println(message);

    }

    @OnClose
    public void handleClose() throws IOException {

        System.out.println("Disconnected to Server!");
    }

    @OnError
    public void handleError(Throwable t) {
        t.printStackTrace();
    }

    public void sendMessage(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException ex) {
            Logger.getLogger(Socket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
