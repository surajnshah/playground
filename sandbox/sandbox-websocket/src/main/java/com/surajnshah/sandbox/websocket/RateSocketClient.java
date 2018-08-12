package com.surajnshah.sandbox.websocket;

import javax.websocket.*;
import java.net.URI;

/**
 * @author surajshah on 12/08/2018
 * @project surajnshah.com
 */

@ClientEndpoint
public class RateSocketClient {

    private static Object waitLock = new Object();

    @OnMessage
    public void onMessage(String message) {
        // The new USD rate arrives from the websocket server.
        System.out.println("Received msg: " + message);
    }

    private static void waitForTerminatedSignal() {
        synchronized (waitLock) {
            try {
                waitLock.wait();
            } catch (InterruptedException e) {

            }
        }
    }

    public static void main(String[] args) {

        WebSocketContainer container = null;
        Session session = null;

        try {
            container = ContainerProvider.getWebSocketContainer();
            session = container.connectToServer(RateSocketClient.class, URI.create("ws://localhost:8080/rateserver"));
            waitForTerminatedSignal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
