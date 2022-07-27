package com.MoreiraJunior.desafio.processors;

import com.MoreiraJunior.desafio.AppLogger.AppLogger;
import com.MoreiraJunior.desafio.exceptions.InvalidDataException;

import java.io.*;
import java.util.logging.Logger;

public class InputProcessor {
    private final ObjectConverter objectConvertor = new ObjectConverter();
    private static final Logger LOGGER = AppLogger.getAppLogger();
    public boolean treatFile(String PATH_IN) throws IOException {
        File file = new File (PATH_IN);
        String fileName = file.getName();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String st;
        StringBuilder dataBuilder = new StringBuilder();
        while ((st = bufferedReader.readLine()) != null) {
            if (!st.isBlank()) {
                dataBuilder.append(st).append("\n");
            }
        }
        String data = dataBuilder.toString();
        try{
            objectConvertor.convertToObjects(data, fileName);
            return true;
        } catch (InvalidDataException invalidDataException){
            LOGGER.severe("Data in this file is compromised!");
            return false;
        } catch (NumberFormatException numberFormatException) {
            LOGGER.severe("Error during formatting");
            return false;
        }
    }
}
