package com.jzel.m321universalcouplermodule.communication.impl;

import com.jzel.m321universalcouplermodule.adapter.model.MessageDto;
import com.jzel.m321universalcouplermodule.communication.CommModule;
import java.util.List;

public class AzuraCommModule implements CommModule {

  @Override
  public void send(final MessageDto message) {
    // TODO implement this
  }

  @Override
  public List<MessageDto> receive() {
    return List.of(); // TODO implement this
  }
}
