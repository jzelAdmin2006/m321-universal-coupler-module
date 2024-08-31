package com.jzel.m321universalcouplermodule.communication.impl;

import static java.util.Objects.requireNonNull;

import com.jzel.m321universalcouplermodule.adapter.model.MessageDto;
import com.jzel.m321universalcouplermodule.communication.CommModule;
import com.jzel.m321universalcouplermodule.communication.adapter.model.ArtemisReceiveMessagesDto;
import com.jzel.m321universalcouplermodule.communication.adapter.service.CommunicationMapperService;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.simpleframework.xml.Serializer;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArtemisCommModule implements CommModule {

  public static final String RECEIVE_RPC = "<?xml version=\"1.0\"?>"
      + "<methodCall>"
      + "<methodName>receive</methodName>"
      + "<params></params>"
      + "</methodCall>";
  private final OkHttpClient client;
  private final Serializer serializer;
  private final CommunicationMapperService mapper;

  @Override
  public void send(MessageDto message) throws IOException {
    // TODO implement this
  }

  @Override
  public List<MessageDto> receive() throws IOException {
    try (final Response response = client.newCall(
        new Request.Builder()
            .url("http://192.168.100.11:2024/RPC2")
            .post(RequestBody.create(RECEIVE_RPC, MediaType.parse("text/xml; charset=utf-8")))
            .build()
    ).execute()
    ) {
      if (!response.isSuccessful()) {
        throw new IOException("Unexpected code " + response);
      }
      try {
        return mapper.toMessageDto(serializer.read(ArtemisReceiveMessagesDto.class,
            requireNonNull(response.body()).string()));
      } catch (Exception e) {
        throw new IOException("Failed to parse response", e);
      }
    }
  }
}
