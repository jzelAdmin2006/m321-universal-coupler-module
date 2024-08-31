package com.jzel.m321universalcouplermodule.common;

import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;
import okhttp3.MediaType;

@NoArgsConstructor(access = PRIVATE)
public class MediaTypeCommons {

  public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
}
