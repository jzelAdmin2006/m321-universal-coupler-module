package com.jzel.m321universalcouplermodule;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
public class CouplerModuleController {

    @PostMapping(value = "/**", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> receive() {
        byte[] byteArray = Base64.getDecoder().decode("ewogIm1lc3NhZ2UiOiAie1xuIFwic291cmNlXCI6IFwiWnVycm8gU3RhdGlvblwiLFxuIFwiZGVzdGluYXRpb25cIjogXCJBenVyYSBTdGF0aW9uXCIsXG4gXCJkYXRhXCI6IFwiRm9yc2NodW5nc2RhdGVuIChHcnVwcGUgMTkyLjE2OC4xMDAuMTEpXCIsXG4gXCJ0c1wiOiBcIjc0MzA4OVwiXG59XG4iLAogInNpZ25hdHVyZSI6ICI1NGNjOTFjOTZiMzEzMmExOWQ0ZTI0YTk5N2NiYTQ4YzJlNjVlMzMzOGZiODU1MjM0YzBiZWIwZWNhOWExIgp9Cg==");

        // StringBuilder zur Formatierung des Byte-Arrays als String
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (int i = 0; i < byteArray.length; i++) {
            result.append(byteArray[i]);
            if (i < byteArray.length - 1) {
                result.append(", ");
            }
        }
        result.append("]");
        String jsonResponse = "{\"kind\": \"success\", \"messages\": [{\"destination\": \"Azura Station\", \"data\": " + result.toString() + "}]}";
        return ResponseEntity.ok(jsonResponse);
    }
}
