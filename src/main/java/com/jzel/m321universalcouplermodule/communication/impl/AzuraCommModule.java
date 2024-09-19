package com.jzel.m321universalcouplermodule.communication.impl;

import static com.google.common.primitives.Bytes.toArray;
import static com.jzel.m321universalcouplermodule.common.MediaTypeCommons.JSON;
import static okhttp3.RequestBody.create;

import com.google.gson.Gson;
import com.jzel.m321universalcouplermodule.communication.CommModule;
import com.jzel.m321universalcouplermodule.communication.adapter.model.AzuraSendMessageDto;
import com.jzel.m321universalcouplermodule.communication.adapter.model.MessageDto;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import com.jzel.m321universalcouplermodule.config.StationConfig;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AzuraCommModule implements CommModule {

  private final OkHttpClient client;
  private final Gson gson;

  @Override
  public void send(final String messageJson) throws IOException {
    MessageDto message = gson.fromJson(messageJson, MessageDto.class);
    try (final Response response = client.newCall(new Builder()
        .url("http://192.168.100."+ StationConfig.STATION_NUMBER +":2030/put_message")
        .post(create(
            gson.toJson(new AzuraSendMessageDto(message.identifier(),
                Base64.getEncoder().encodeToString(toArray(message.data())))),
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
  public List<MessageDto> receive() {
    return List.of(); // TODO implement this
  }
}
