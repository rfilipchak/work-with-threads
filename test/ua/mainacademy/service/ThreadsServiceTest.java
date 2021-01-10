package ua.mainacademy.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ua.mainacademy.model.ConnectionInfo;

import java.io.File;

class ThreadsServiceTest {
    private static final String TEST_DIR = "/Users/roman/IdeaProjects/work-with-threads/test/resources";
    private static final String newInfo = "newInfo.txt";
    private static final String newInfoPath = TEST_DIR + "/" + newInfo;

    @BeforeAll
    public static void setUp() {
        new File("/Users/roman/IdeaProjects/work-with-threads/test/resources/newInfo.txt").deleteOnExit();
    }

    @AfterAll
    public static void tearDown() {
        new File("/Users/roman/IdeaProjects/work-with-threads/test/resources/newInfo.txt").deleteOnExit();
    }

    @Test
    void runTest() {
        new ThreadsService(TEST_DIR, newInfo).run();
        String actualResult = new File(newInfoPath).toString();

        actualResult.contains("192.192.192.1");
    }

    @Test
    void runThreadsAddingTest() {
        for (int i = 0; i < 3; i++) {
            Thread thread = new ThreadsService(TEST_DIR, newInfo);
            thread.start();
        }

        String actualResult = new File(newInfoPath).toString();
        actualResult.contains("192.192.192.1");
        actualResult.contains("192.192.192.2");
        actualResult.contains("192.192.192.3");
    }

    @Test
    void runCustomConnectionInfoConstructorTest() {
        ConnectionInfo connectionInfo = new ConnectionInfo(123, 1234567L, "192.193.194.0");

        new ThreadsService(connectionInfo, TEST_DIR, newInfo).run();
        String actualResult = new File(newInfoPath).toString();

        actualResult.contains("123 1234567 192.193.194.0");
    }
}