package com.jzel.m321universalcouplermodule.adapter.rest;


import static com.jzel.m321universalcouplermodule.adapter.model.CouplerResponseDto.SUCCESS_CONSUMPTION;
import static org.springframework.http.HttpStatus.valueOf;

import com.google.gson.JsonSyntaxException;
import com.jzel.m321universalcouplermodule.adapter.model.CouplerResponseDto;
import com.jzel.m321universalcouplermodule.communication.CommModuleFactory;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class CouplerController {

  private final CommModuleFactory commModuleFactory;

  @PostMapping("/{stationName}/receive")
  public ResponseEntity<CouplerResponseDto> receive(@PathVariable final String stationName) throws IOException {
    return ResponseEntity.ok(new CouplerResponseDto(commModuleFactory.create(stationName).receive()));
  }

  @PostMapping(path = "/{stationName}/send", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
  public ResponseEntity<CouplerResponseDto> send(
      @PathVariable final String stationName,
      @RequestBody final String messageContent
  ) throws IOException {
    try {
      commModuleFactory.create(stationName).send(messageContent);
      return ResponseEntity.ok(SUCCESS_CONSUMPTION);
    } catch (JsonSyntaxException e) {
      throw new ResponseStatusException(valueOf(400), "invalid JSON format");
    }
  }
}
