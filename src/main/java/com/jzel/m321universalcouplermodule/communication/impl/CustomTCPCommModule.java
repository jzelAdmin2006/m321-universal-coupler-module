package com.jzel.m321universalcouplermodule.communication.impl;

import static com.google.common.primitives.Bytes.asList;
import static com.google.common.primitives.Bytes.toArray;
import static java.nio.charset.StandardCharsets.UTF_8;

import com.google.common.primitives.Bytes;
import com.google.gson.Gson;
import com.jzel.m321universalcouplermodule.communication.CommModule;
import com.jzel.m321universalcouplermodule.communication.adapter.model.MessageDto;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jzel.m321universalcouplermodule.config.StationConfig;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

public abstract class CustomTCPCommModule implements CommModule {
  private final Gson gson;
  private final int port;

  public CustomTCPCommModule(Gson gson, int port) {
    this.gson = gson;
    this.port = port;
  }

  @Override
  public void send(String messageJson) throws IOException {
    MessageDto message = gson.fromJson(messageJson, MessageDto.class);
    try (Socket socket = new Socket("192.168.100."+ StationConfig.STATION_NUMBER, port);
         DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {
      byte[] sourceBytes = message.identifier().getBytes("UTF-8");
      byte[] messageBytes = toArray(message.data());

      out.writeShort(messageBytes.length);
      out.writeByte(sourceBytes.length);
      out.write(sourceBytes);
      out.write(messageBytes);
    }
  }

  public List<MessageDto> receive() throws IOException {
    List<MessageDto> messages = new ArrayList<>();
    Set<String> receivedMessages = new HashSet<>();

    try (Socket socket = new Socket("192.168.100."+ StationConfig.STATION_NUMBER, port);
        DataInputStream in = new DataInputStream(socket.getInputStream())) {

      while (in.available() == 0) {
        try {
          Thread.sleep(1);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
          throw new IOException("Interrupted while waiting for data", e);
        }
      }

      while (in.available() > 0) {
        int sizeOfMessage = in.readUnsignedShort();
        int sizeOfSrcDst = in.readUnsignedByte();
        byte[] srcDstBytes = new byte[sizeOfSrcDst];
        in.readFully(srcDstBytes);
        String srcDst = new String(srcDstBytes, UTF_8);

        byte[] messageBytes = new byte[sizeOfMessage];
        in.readFully(messageBytes);
        String messageContent = new String(messageBytes, UTF_8);

        if (!receivedMessages.add(messageContent)) {
          break;
        }

        messages.add(new MessageDto(srcDst, asList(messageBytes)));
      }
    }
    return messages;
  }
}
