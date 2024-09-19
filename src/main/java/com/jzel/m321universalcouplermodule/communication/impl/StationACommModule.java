package com.jzel.m321universalcouplermodule.communication.impl;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service
public class StationACommModule extends CustomTCPCommModule {

    public StationACommModule(Gson gson) {
        super(gson, 2034);
    }
}
