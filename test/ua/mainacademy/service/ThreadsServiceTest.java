package ua.mainacademy.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.mainacademy.model.ConnectionInfo;

import java.io.File;

class ThreadsServiceTest {
    private static final String TEST_DIR = "/Users/roman/IdeaProjects/work-with-threads/test/resources";
    private static final String newInfo = "newInfo.txt";
    private static final String newInfoPath = TEST_DIR + "/" + newInfo;

    @AfterAll
    public static void tearDown() {
        new File("/Users/roman/IdeaProjects/work-with-threads/test/resources/newInfo.txt").deleteOnExit();
    }

    @Test
    void runTest() {
        File file = new File(newInfoPath);
        Assertions.assertFalse(file.exists());

        new ThreadsService(TEST_DIR, newInfo).run();
        String actualResult = file.toString();

        actualResult.contains("192.192.192.1");
    }

    @Test
    void runThreadsAddingTest() {
        File file = new File(newInfoPath);
        Assertions.assertFalse(file.exists());

        for (int i = 0 ; i<3;i++){
            Thread thread = new ThreadsService(TEST_DIR, newInfo);
            thread.start();
        }

        String actualResult = file.toString();
        actualResult.contains("192.192.192.1");
        actualResult.contains("192.192.192.2");
        actualResult.contains("192.192.192.3");
    }

    @Test
    void runCustomConnectionInfoConstructorTest() {
        File file = new File(newInfoPath);
        Assertions.assertFalse(file.exists());

        ConnectionInfo connectionInfo = new ConnectionInfo(123, 1234567L, "192.193.194.0");

        new ThreadsService(connectionInfo, TEST_DIR, newInfo).run();
        String actualResult = file.toString();

        actualResult.contains("123 1234567 192.193.194.0");
    }
}