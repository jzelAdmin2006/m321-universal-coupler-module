package com.jzel.m321universalcouplermodule.communication.adapter.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public record Base64ReceiveResponseDto(
    String kind, @SerializedName("received_messages") List<Base64MessageDto> receivedMessages
) {

  public record Base64MessageDto(
      String dest,
      @SerializedName(value = "msg", alternate = {"base64data"}) String message
  ) {

  }
}
