package com.jzel.m321universalcouplermodule.adapter.rest;


import static com.jzel.m321universalcouplermodule.adapter.model.CouplerResponseDto.SUCCESS_CONSUMPTION;

import com.jzel.m321universalcouplermodule.adapter.model.CouplerResponseDto;
import com.jzel.m321universalcouplermodule.adapter.model.MessageDto;
import com.jzel.m321universalcouplermodule.communication.CommModuleFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CouplerController {

  @PostMapping("/{stationName}/receive")
  public ResponseEntity<CouplerResponseDto> receive(@PathVariable final String stationName) {
    return ResponseEntity.ok(new CouplerResponseDto(CommModuleFactory.create(stationName).receive()));
  }

  @PostMapping("/{stationName}/send")
  public ResponseEntity<CouplerResponseDto> send(
      @PathVariable final String stationName, @RequestBody final MessageDto message
  ) {
    CommModuleFactory.create(stationName).send(message);
    return ResponseEntity.ok(SUCCESS_CONSUMPTION);
  }
}
