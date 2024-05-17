package com.example.auction.controller;

import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
@ServerEndpoint("/websocket/{productId}")
public class MessageController {

    private static final Map<String, List<Session>> chatRooms = new ConcurrentHashMap<>();

    @GetMapping("/chat/{productId}")
    public String index(@PathVariable("productId") Long id) {
        return "/articles/index.html";
    }

    @OnOpen
    public void open(Session session, @PathParam("productId") String productId) {
        chatRooms.computeIfAbsent(productId, k -> new CopyOnWriteArrayList<>()).add(session);
        System.out.println("채팅방 [" + productId + "]에 연결되었습니다. 현재 접속중인 유저 수: " + chatRooms.get(productId).size());
    }

    @OnMessage
    public void handleMessage(Session session, @PathParam("productId") String productId, String message) {
        List<Session> sessions = chatRooms.get(productId);
        if (sessions != null) {
            for (Session s : sessions) {
                if (s.isOpen()) {
                    try {
                        s.getBasicRemote().sendText("유저 " + session.getId() + ": " + message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
