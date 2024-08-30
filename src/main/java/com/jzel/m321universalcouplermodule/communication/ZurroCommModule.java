package com.jzel.m321universalcouplermodule.communication;

import com.jzel.m321universalcouplermodule.adapter.model.MessageDto;
import java.util.List;

public class ZurroCommModule implements CommModule {

  @Override
  public void send(final MessageDto message) {
    // TODO implement this
  }

  @Override
  public List<MessageDto> receive() {
    return List.of(); // TODO implement this
  }
}
