package com.MoreiraJunior.desafio.AppLogger;

import com.MoreiraJunior.desafio.exceptions.LogConfigException;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AppLogger {
    private static Logger LOGGER;
    private static final String logFileDirectoryPath = System.getProperty("user.home") + "/data/log/";

    public AppLogger (){

    }

    public static Logger getAppLogger (){
        if(LOGGER == null){
            LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
            try {
                FileHandler fileHandler = new FileHandler(logFileDirectoryPath+"logs.log");
                LOGGER.addHandler(fileHandler);
                SimpleFormatter formatter = new SimpleFormatter();
                fileHandler.setFormatter(formatter);
            } catch (IOException e) {
                LOGGER.severe("Could not config the logger!");
                throw new LogConfigException("Could not config the logger! " + e);
            }
        }
        return LOGGER;
    }
}
