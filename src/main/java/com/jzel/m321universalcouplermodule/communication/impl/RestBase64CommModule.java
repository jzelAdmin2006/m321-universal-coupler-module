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
import okhttp3.Request.Builder;
import okhttp3.Response;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public abstract class RestBase64CommModule implements CommModule {

  private final OkHttpClient client;
  private final Gson gson;
  private final CommunicationMapperService mapper;

  abstract String getUrl();


  @Override
  public void send(final MessageDto message) {
    // TODO implement this
  }

  @Override
  public List<MessageDto> receive() throws IOException {
    try (final Response response = client.newCall(new Builder()
        .url(getUrl())
        .post(EMPTY_REQUEST)
        .build()).execute()) {
      return gson.fromJson(requireNonNull(response.body()).string(), Base64ReceiveResponseDto.class)
          .receivedMessages()
          .stream()
          .map(mapper::toMessageDto)
          .toList();
    }
  }
}
