package com.jzel.m321universalcouplermodule.communication;

import static org.springframework.http.HttpStatus.valueOf;

import com.jzel.m321universalcouplermodule.communication.impl.*;
import com.jzel.m321universalcouplermodule.config.StationConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CommModuleFactory {

  private final ApplicationContext context;

  public CommModule create(final String stationName) {
    return context.getBean(determineCommModule(stationName));
  }

  private Class<? extends CommModule> determineCommModule(String stationName) {
    if (stationName.equals("Station "+ StationConfig.STATION_NUMBER+"-A")) {
      return StationACommModule.class;
    }
    return switch (stationName) {
      case "Zurro Station" -> ZurroCommModule.class;
      case "Azura Station" -> AzuraCommModule.class;
      case "Core Station" -> CoreCommModule.class;
      case "Artemis Station" -> ArtemisCommModule.class;
      case "Elyse Terminal" -> ElyseCommModule.class;
      case "Aurora Station" -> AuroraCommModule.class;
      default -> throw new ResponseStatusException(
          valueOf(404), "No communication module found for station name '%s'".formatted(stationName)
      );
    };
  }
}
