package com.jzel.m321universalcouplermodule.communication.impl;

import static java.util.Collections.synchronizedList;

import com.google.gson.Gson;
import com.jzel.m321universalcouplermodule.communication.CommModule;
import com.jzel.m321universalcouplermodule.communication.adapter.model.MessageDto;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Service
@RequiredArgsConstructor
public class ElyseCommModule implements CommModule {

  private final Gson gson;
  private final List<String> payloadBuffer = synchronizedList(new ArrayList<>());

  @Override
  public void send(String message) {
    // TODO implement this
  }

  @Override
  public List<MessageDto> receive() {
    final List<MessageDto> receivedMessages = payloadBuffer.stream()
        .map(payload -> gson.fromJson(payload, MessageDto.class)).toList();
    payloadBuffer.clear();
    return receivedMessages;
  }

  @PostConstruct
  void initWebSocketConnection() {
    new WebSocketConnectionManager(
        new StandardWebSocketClient(),
        new TextWebSocketHandler() {
          @Override
          public void handleTextMessage(@NotNull WebSocketSession session, @NotNull TextMessage message) {
            final String payload = message.getPayload();
            if (payloadBuffer.isEmpty()) {
              payloadBuffer.add(payload);
            }
          }
        },
        "ws://192.168.100.11:2026/api"
    ).start();
  }
}
