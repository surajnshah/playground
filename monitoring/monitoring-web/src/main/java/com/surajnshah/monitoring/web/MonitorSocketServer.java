package com.surajnshah.monitoring.web;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONObject;

import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author surajshah on 13/08/2018
 * @project surajnshah.com
 */

@ServerEndpoint("/monitorserver")
public class MonitorSocketServer {

    // Queue holds the list of connected clients.
    private static Queue<Session> queue = new ConcurrentLinkedQueue<Session>();
    // Monitor publisher thread.
    private static Thread monitorThread;

    static {

        // Monitor publisher thread generates a new set of values for Monitor every 5 seconds.
        monitorThread = new Thread() {

            public void run() {

                MonitorService monitorService = new MonitorService();
                ObjectMapper mapper = new ObjectMapper();
                String json = "";
                while (true) {
                    Monitor monitor = null;
                    try {
                        monitor = monitorService.getMonitor();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (queue != null) {
                        try {
                            json = mapper.writeValueAsString(monitor);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        sendAll(json);
                    }
                    try {
                        sleep(5000);
                    } catch (InterruptedException e) {

                    }
                }

            }

        };
        monitorThread.start();

    }

    private static void sendAll(String message) {
        try {
            // Send the new monitor to all open WebSocket sessions.
            ArrayList<Session> closedSessions = new ArrayList<>();
            for (Session session : queue)
                if (!session.isOpen()) {
                    System.err.println("Closed session: " + session.getId());
                    closedSessions.add(session);
                } else {
                    session.getBasicRemote().sendText(message);
                }
            queue.removeAll(closedSessions);
            System.out.println("Sending " + message + " to " + queue.size() + " clients");
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        try {
            System.out.println("Received msg " + message + " from " + session.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnOpen
    public void open(Session session) {
        queue.add(session);
        System.out.println("New session opened: " + session.getId());
    }

    @OnError
    public void error(Session session, Throwable t) {
        queue.remove(session);
        System.err.println("Session closed: " + session.getId());
    }

}
