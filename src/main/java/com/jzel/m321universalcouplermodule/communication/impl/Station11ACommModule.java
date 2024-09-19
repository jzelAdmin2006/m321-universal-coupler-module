package com.jzel.m321universalcouplermodule.communication.impl;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service
public class Station11ACommModule extends CustomTCPCommModule {

    public Station11ACommModule(Gson gson) {
        super(gson, 2034);
    }
}
