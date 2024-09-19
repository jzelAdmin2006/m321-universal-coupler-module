package com.jzel.m321universalcouplermodule.communication.impl;

import com.google.gson.Gson;
import com.jzel.m321universalcouplermodule.communication.adapter.service.CommunicationMapperService;
import com.jzel.m321universalcouplermodule.config.StationConfig;
import okhttp3.OkHttpClient;
import org.springframework.stereotype.Service;

@Service
public class ZurroCommModule extends RestBase64CommModule {

  public ZurroCommModule(final OkHttpClient client, final Gson gson, final CommunicationMapperService mapper) {
    super(client, gson, mapper);
  }

  @Override
  String getUrl() {
    return "http://192.168.100."+ StationConfig.STATION_NUMBER +":2029";
  }
}
