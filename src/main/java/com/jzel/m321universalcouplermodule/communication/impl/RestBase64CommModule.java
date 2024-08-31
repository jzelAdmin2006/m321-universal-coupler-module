package com.jzel.m321universalcouplermodule.communication.impl;

import static com.jzel.m321universalcouplermodule.common.MediaTypeCommons.JSON;
import static java.util.Objects.requireNonNull;
import static okhttp3.RequestBody.create;
import static okhttp3.internal.Util.EMPTY_REQUEST;

import com.google.gson.Gson;
import com.jzel.m321universalcouplermodule.communication.CommModule;
import com.jzel.m321universalcouplermodule.communication.adapter.model.Base64MessageDto;
import com.jzel.m321universalcouplermodule.communication.adapter.model.Base64ReceiveResponseDto;
import com.jzel.m321universalcouplermodule.communication.adapter.model.MessageDto;
import com.jzel.m321universalcouplermodule.communication.adapter.service.CommunicationMapperService;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public abstract class RestBase64CommModule implements CommModule {

  private final OkHttpClient client;
  private final Gson gson;
  private final CommunicationMapperService mapper;

  abstract String getUrl();


  @Override
  public void send(final String messageJson) throws IOException {
    try (final Response response = client.newCall(new Builder()
        .url(getUrl() + "/send")
        .post(create(
            gson.toJson(gson.fromJson(messageJson, Base64MessageDto.class)),
            JSON))
        .build()).execute()) {
      if (!response.isSuccessful()) {
        final ResponseBody body = response.body();
        throw new IOException(
            "Failed to send message: " + (body == null ? null : body.string()));
      }
    }
  }

  @Override
  public List<MessageDto> receive() throws IOException {
    try (final Response response = client.newCall(new Builder()
        .url(getUrl() + "/receive")
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
