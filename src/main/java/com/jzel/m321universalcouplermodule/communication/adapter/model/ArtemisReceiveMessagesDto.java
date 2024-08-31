package com.jzel.m321universalcouplermodule.communication.adapter.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "methodResponse")
@Data
public class ArtemisReceiveMessagesDto {

  @Element(name = "params")
  private Params params;

  @Data
  public static class Params {

    @Element(name = "param")
    private Param param;
  }

  @Data
  public static class Param {

    @Element(name = "value")
    private OuterValue value;
  }

  @Data
  public static class OuterValue {

    @Element(name = "array")
    private OuterArray array;
  }

  @Data
  public static class OuterArray {

    @Element(name = "data")
    private OuterData data;
  }

  @Data
  public static class OuterData {

    @ElementList(entry = "value", inline = true)
    private List<InnerValue> values;
  }

  @Data
  public static class InnerValue {

    @Element(name = "array", required = false)
    private InnerArray array;
  }

  @Data
  public static class InnerArray {

    @Element(name = "data")
    private InnerData data;
  }

  @Data
  public static class InnerData {

    @ElementList(entry = "value", inline = true)
    private List<ValueElement> valueElements;
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ValueElement {

    @Element(name = "string", required = false)
    private String stringValue;

    @Element(name = "base64", required = false)
    private String base64Value;
  }
}
