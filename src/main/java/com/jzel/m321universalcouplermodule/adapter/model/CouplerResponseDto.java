package com.jzel.m321universalcouplermodule.adapter.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static lombok.AccessLevel.PRIVATE;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jzel.m321universalcouplermodule.communication.adapter.model.MessageDto;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(force = true, access = PRIVATE)
@JsonInclude(NON_NULL)
public class CouplerResponseDto {

  public static final CouplerResponseDto SUCCESS_CONSUMPTION = new CouplerResponseDto();

  @SuppressWarnings("java:S1170")
  private final String kind = "success";
  private final List<MessageDto> messages;
}
