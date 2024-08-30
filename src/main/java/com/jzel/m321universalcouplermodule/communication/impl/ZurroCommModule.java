package com.jzel.m321universalcouplermodule.communication.impl;

import static java.util.Objects.requireNonNull;
import static okhttp3.internal.Util.EMPTY_REQUEST;

import com.google.gson.Gson;
import com.jzel.m321universalcouplermodule.adapter.model.MessageDto;
import com.jzel.m321universalcouplermodule.communication.CommModule;
import com.jzel.m321universalcouplermodule.communication.adapter.model.Base64ReceiveResponseDto;
import com.jzel.m321universalcouplermodule.communication.adapter.service.CommunicationMapperService;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ZurroCommModule implements CommModule {

  private static final Request RECEIVE_REQUEST = new Builder()
      .url("http://192.168.100.11:2029/receive")
      .post(EMPTY_REQUEST)
      .build();

  private final OkHttpClient client;
  private final Gson gson;
  private final CommunicationMapperService mapper;

  @Override
  public void send(final MessageDto message) {
    // TODO implement this
  }

  @Override
  public List<MessageDto> receive() throws IOException {
    try (final Response response = client.newCall(RECEIVE_REQUEST).execute()) {
      return gson.fromJson(requireNonNull(response.body()).string(), Base64ReceiveResponseDto.class)
          .receivedMessages()
          .stream()
          .map(mapper::toMessageDto)
          .toList();
    }
  }
}
