package com.jzel.m321universalcouplermodule.adapter.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public record MessageDto(
    @SerializedName(value = DESTINATION, alternate = SOURCE)
    @JsonProperty(DESTINATION) @JsonAlias(SOURCE) String identifier,
    @SerializedName(value = "data", alternate = "msg") List<Byte> data
) {

  private static final String DESTINATION = "destination";
  private static final String SOURCE = "source";
}
