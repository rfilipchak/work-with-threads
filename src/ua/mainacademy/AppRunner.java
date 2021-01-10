package ua.mainacademy;

import ua.mainacademy.service.ThreadsService;

public class AppRunner {
    private static final String TEST_DIR = "/Users/roman/IdeaProjects/work-with-threads/test/resources";
    private static String newInfo = "newtInfo.txt";

    public static void main(String[] args) {

        for (int i = 0 ; i<100;i++){
            Thread thread = new ThreadsService(TEST_DIR, newInfo);
            thread.start();
        }
    }
}
