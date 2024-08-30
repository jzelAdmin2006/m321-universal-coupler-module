package com.jzel.m321universalcouplermodule.communication.adapter.model;

import com.google.gson.annotations.SerializedName;

public record AzuraSendMessageDto(@SerializedName("sending_station") String source, String base64data) {

}
