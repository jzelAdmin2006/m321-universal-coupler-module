package com.jzel.m321universalcouplermodule.communication;

import com.jzel.m321universalcouplermodule.adapter.model.MessageDto;
import java.util.List;

public interface CommModule {

  void send(final MessageDto message);

  List<MessageDto> receive();
}
