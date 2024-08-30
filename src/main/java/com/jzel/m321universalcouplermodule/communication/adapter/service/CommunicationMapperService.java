package com.jzel.m321universalcouplermodule.communication.adapter.service;

import static java.util.Arrays.asList;
import static org.apache.commons.lang3.ArrayUtils.toObject;

import com.jzel.m321universalcouplermodule.adapter.model.MessageDto;
import com.jzel.m321universalcouplermodule.communication.adapter.model.Base64ReceiveResponseDto.Base64MessageDto;
import java.util.Base64;
import org.springframework.stereotype.Service;

@Service
public class CommunicationMapperService {

  public MessageDto toMessageDto(final Base64MessageDto base64MessageDto) {
    return new MessageDto(
        base64MessageDto.dest(),
        asList(toObject(Base64.getDecoder().decode(base64MessageDto.message())))
    );
  }
}
