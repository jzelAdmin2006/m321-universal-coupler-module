package com.jzel.m321universalcouplermodule.communication;

import com.jzel.m321universalcouplermodule.adapter.model.MessageDto;
import java.io.IOException;
import java.util.List;

public interface CommModule {

  void send(final MessageDto message) throws IOException;

  List<MessageDto> receive() throws IOException;
}
