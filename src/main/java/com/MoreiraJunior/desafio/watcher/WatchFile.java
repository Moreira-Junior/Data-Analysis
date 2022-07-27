package com.MoreiraJunior.desafio.watcher;

import com.MoreiraJunior.desafio.AppLogger.AppLogger;
import com.MoreiraJunior.desafio.processors.InputProcessor;
import com.MoreiraJunior.desafio.processors.OutputProcessor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class WatchFile {
    private static final Logger LOGGER = AppLogger.getAppLogger();
    private static final String inputFileDirectoryPath = System.getProperty("user.home") + "/data/in/";
    private static final String invalidFileDirectoryPath = System.getProperty("user.home") + "/data/invalid/";
    private static final String processedFileDirectoryPath = System.getProperty("user.home") + "/data/proc/";
    private static final String outputFileDirectoryPath = System.getProperty("user.home") + "/data/out/";
    private static final String logFileDirectoryPath = System.getProperty("user.home") + "/data/log/";
    private static final InputProcessor inputProcessor = new InputProcessor();
    private static final OutputProcessor outputProcessor = new OutputProcessor();

    public static void watch() {
        try (WatchService service = FileSystems.getDefault().newWatchService()){
            LOGGER.info("Program started!");
            Map <WatchKey, Path> keyMap = new HashMap<>();
            Path path = Paths.get(inputFileDirectoryPath);
            keyMap.put(path.register(service,
                    StandardWatchEventKinds.ENTRY_CREATE),
                    path);
            WatchKey watchKey;
            do {
                watchKey = service.take();
                Path eventDir = keyMap.get(watchKey);
                for (WatchEvent<?> event : watchKey.pollEvents()){
                    String eventPath = String.valueOf(event.context());
                    if(eventPath.contains(".dat")){
                        File file = new File(eventDir + "/" + eventPath);
                        if(inputProcessor.treatFile(eventDir + "/" + eventPath)){
                            LOGGER.info("Read file: " + eventPath);
                            file.renameTo(new File(processedFileDirectoryPath +eventPath));
                            FileWriter fileWriter = new FileWriter(outputFileDirectoryPath +"/"+file.getName().replace(".dat","")+".done.dat");
                            fileWriter.write(outputProcessor.generateOutput(eventPath));
                            LOGGER.info("Output created: "+ file.getName().replace(".dat","")+".done.dat");
                            fileWriter.close();
                        } else {
                            LOGGER.warning("Could not read file: " + eventPath);
                            file.renameTo(new File(invalidFileDirectoryPath +eventPath));
                        }
                    }
                }
            } while (watchKey.reset());
        } catch (IOException e) {
            LOGGER.severe("Error on IO!");
        } catch (InterruptedException e) {
            LOGGER.severe("Interruption error!");
        }
    }

    public static void start(){
        try {
            Files.createDirectories(Paths.get(inputFileDirectoryPath));
            Files.createDirectories(Paths.get(outputFileDirectoryPath));
            Files.createDirectories(Paths.get(processedFileDirectoryPath));
            Files.createDirectories(Paths.get(invalidFileDirectoryPath));
            Files.createDirectories(Paths.get(logFileDirectoryPath));
        } catch(IOException e) {
            LOGGER.severe("Could not create directories");
        }
    }
}
