package com.jzel.m321universalcouplermodule.communication.impl;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service
public class AuroraCommModule extends CustomTCPCommModule {


    public AuroraCommModule(Gson gson) {
        super(gson, 2031);
    }
}
