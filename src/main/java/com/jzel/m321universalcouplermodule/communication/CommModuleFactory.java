package com.jzel.m321universalcouplermodule.communication;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.valueOf;

import lombok.NoArgsConstructor;
import org.springframework.web.server.ResponseStatusException;

@NoArgsConstructor(access = PRIVATE)
public class CommModuleFactory {

  public static CommModule create(final String stationName) {
    return switch (stationName) {
      case "Zurro Station" -> new ZurroCommModule();
      case "Azura Station" -> new AzuraCommModule();
      default -> throw new ResponseStatusException(
          valueOf(404), "No communication module found for station name '%s'".formatted(stationName)
      );
    };
  }
}
