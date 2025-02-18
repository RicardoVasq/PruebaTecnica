package Readers;

import Models.variable;
import Utilities.Logs;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonReader {
    private final static String variablePath = "src/test/java/Utilities/variablesParametros.json";

    public static variable variableReader(){
        final var objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(variablePath), variable.class);
        }catch (IOException ioException){
            Logs.error("IOException: %s", ioException.getLocalizedMessage());
            throw new RuntimeException();
        }
    }
}
