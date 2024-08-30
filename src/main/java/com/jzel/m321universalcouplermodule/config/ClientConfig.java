package com.jzel.m321universalcouplermodule.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {
  @Bean
  public OkHttpClient client() {
    return new OkHttpClient();
  }
}
