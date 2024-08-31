package com.jzel.m321universalcouplermodule.config;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XmlSerialisationConfig {

  @Bean
  public Serializer serializer() {
    return new Persister();
  }
}
