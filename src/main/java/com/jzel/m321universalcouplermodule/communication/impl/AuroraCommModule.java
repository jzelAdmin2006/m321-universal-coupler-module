package com.jzel.m321universalcouplermodule.communication.impl;

import static com.google.common.primitives.Bytes.asList;
import static java.nio.charset.StandardCharsets.UTF_8;

import com.jzel.m321universalcouplermodule.communication.CommModule;
import com.jzel.m321universalcouplermodule.communication.adapter.model.MessageDto;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class AuroraCommModule implements CommModule {

  @Override
  public void send(String message) {
    // TODO implement this
  }

  public List<MessageDto> receive() throws IOException {
    List<MessageDto> messages = new ArrayList<>();
    Set<String> receivedMessages = new HashSet<>();

    try (Socket socket = new Socket("192.168.100.11", 2031);
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
