package com.surajnshah.sandbox.websocket;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * @author surajshah on 12/08/2018
 * @project surajnshah.com
 */

@ServerEndpoint("/websocket")
public class WebSocketTest {

    @OnMessage
    public void onMessage (String message, Session session) throws IOException, InterruptedException {

        session.getBasicRemote().sendText("Hello" + message);

    }

    @OnOpen
    public void onOpen() {

        System.out.println("Client connected");

    }

    @OnClose
    public void onClose() {
        System.out.println("Connection closed");

    }

}
