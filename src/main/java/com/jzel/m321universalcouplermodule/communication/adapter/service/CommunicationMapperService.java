package com.jzel.m321universalcouplermodule.communication.adapter.service;

import static java.util.Arrays.asList;
import static org.apache.commons.lang3.ArrayUtils.toObject;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import com.jzel.m321universalcouplermodule.communication.adapter.model.ArtemisReceiveMessagesDto;
import com.jzel.m321universalcouplermodule.communication.adapter.model.ArtemisReceiveMessagesDto.ValueElement;
import com.jzel.m321universalcouplermodule.communication.adapter.model.Base64ReceiveResponseDto.Base64MessageDto;
import com.jzel.m321universalcouplermodule.communication.adapter.model.MessageDto;
import java.util.Base64;
import java.util.List;
import java.util.stream.IntStream;
import org.springframework.stereotype.Service;

@Service
public class CommunicationMapperService {

  public MessageDto toMessageDto(final Base64MessageDto base64MessageDto) {
    return new MessageDto(
        base64MessageDto.target(),
        asList(toObject(Base64.getDecoder().decode(base64MessageDto.message())))
    );
  }

  public List<MessageDto> toMessageDto(final ArtemisReceiveMessagesDto artemisMsgDto) {
    return pairValueElements(
        artemisMsgDto.getParams().getParam().getValue().getArray().getData().getValues()
            .getFirst()
            .getArray().getData().getValueElements()
    ).stream()
        .map(e -> new MessageDto(
            e.getStringValue(),
            asList(toObject(Base64.getDecoder().decode(e.getBase64Value().replace("\n", EMPTY))))
        ))
        .toList();
  }

  private List<ValueElement> pairValueElements(final List<ValueElement> elements) {
    return IntStream.range(0, elements.size())
        .filter(i -> i % 2 == 0 && i + 1 < elements.size())
        .mapToObj(i -> new ValueElement(elements.get(i).getStringValue(), elements.get(i + 1).getBase64Value()))
        .toList();
  }
}
