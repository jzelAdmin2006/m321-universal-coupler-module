package com.jzel.m321universalcouplermodule.adapter.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record MessageDto(@JsonProperty("destination") @JsonAlias("source") String identifier, List<Byte> data) {

}
