package com.jzel.m321universalcouplermodule.communication;

import static org.springframework.http.HttpStatus.valueOf;

import com.jzel.m321universalcouplermodule.communication.impl.AzuraCommModule;
import com.jzel.m321universalcouplermodule.communication.impl.ZurroCommModule;
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
    return switch (stationName) {
      case "Zurro Station" -> ZurroCommModule.class;
      case "Azura Station" -> AzuraCommModule.class;
      default -> throw new ResponseStatusException(
          valueOf(404), "No communication module found for station name '%s'".formatted(stationName)
      );
    };
  }
}
