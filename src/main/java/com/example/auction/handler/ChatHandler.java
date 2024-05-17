package com.example.auction.handler;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.UriTemplate;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ConcurrentMap;

public class ChatHandler extends TextWebSocketHandler {
    private final ConcurrentMap<String, List<WebSocketSession>> chatRooms = new ConcurrentHashMap<>();
    private final UriTemplate template = new UriTemplate("/websocket/{productId}");

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String productId = template.match(session.getUri().getPath()).get("productId");
        chatRooms.computeIfAbsent(productId, k -> new CopyOnWriteArrayList<>()).add(session);
        System.out.println("채팅방 [" + productId + "]에 연결되었습니다. 현재 접속중인 유저 수: " + chatRooms.get(productId).size());
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String productId = template.match(session.getUri().getPath()).get("productId");
        List<WebSocketSession> sessions = chatRooms.get(productId);
        if (sessions != null) {
            for (WebSocketSession s : sessions) {
                if (s.isOpen()) {
                    s.sendMessage(new TextMessage("유저 " + session.getId() + ": " + message.getPayload()));
                }
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {
        String productId = template.match(session.getUri().getPath()).get("productId");
        List<WebSocketSession> sessions = chatRooms.get(productId);
        if (sessions != null) {
            sessions.remove(session);
            System.out.println("채팅방 [" + productId + "]에서 연결이 종료되었습니다. 현재 접속중인 유저 수: " + sessions.size());
        }
    }
}
