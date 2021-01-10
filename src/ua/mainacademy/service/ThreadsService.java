package ua.mainacademy.service;

import ua.mainacademy.model.ConnectionInfo;

import java.io.File;
import java.io.FileWriter;
import java.util.logging.Logger;

import static ua.mainacademy.util.RandomConnectionInfo.getRandomConnectionInfo;


public class ThreadsService extends Thread {
    private static final Logger LOGGER = Logger.getLogger(ThreadsService.class.getName());
    private static final String SEPARATOR = System.getProperty("file.separator");

    private final String filePath;
    private final String fileName;
    private final ConnectionInfo connectionInfo;

    public ThreadsService(String filePath, String fileName) {
        this.filePath = filePath;
        this.fileName = fileName;
        connectionInfo = getRandomConnectionInfo();
    }

    public ThreadsService(ConnectionInfo connectionInfo, String filePath, String fileName) {
        this.connectionInfo = connectionInfo;
        this.filePath = filePath;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        writeConnectionInfoToFileToSeparateLine(connectionInfo, filePath, fileName);
        LOGGER.info(String.format("New info added: %s", connectionInfo));
    }

    private void writeConnectionInfoToFileToSeparateLine(ConnectionInfo connectionInfo, String filePath, String fileName) {
        String file = filePath + SEPARATOR + fileName;
        checkFilesDir(filePath);

        try (FileWriter writer = new FileWriter(file, true)) {
            writer.append(connectionInfo.toString()).append("\n");
            writer.flush();
        } catch (Exception e) {
            LOGGER.warning("New note not added because of an error");
            e.printStackTrace();
        }
    }

    private void checkFilesDir(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) file.mkdir();
    }
}
