package com.jzel.m321universalcouplermodule.communication.impl;

import static com.google.common.primitives.Bytes.toArray;
import static okhttp3.RequestBody.create;

import com.google.gson.Gson;
import com.jzel.m321universalcouplermodule.adapter.model.MessageDto;
import com.jzel.m321universalcouplermodule.communication.CommModule;
import com.jzel.m321universalcouplermodule.communication.adapter.model.AzuraSendMessageDto;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import lombok.RequiredArgsConstructor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AzuraCommModule implements CommModule {

  private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
  private final OkHttpClient client;
  private final Gson gson;

  @Override
  public void send(final MessageDto message) throws IOException {
    try (final Response response = client.newCall(new Builder()
        .url("http://192.168.100.11:2030/put_message")
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
