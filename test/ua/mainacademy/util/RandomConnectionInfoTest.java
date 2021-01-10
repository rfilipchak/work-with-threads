package ua.mainacademy.util;

import org.junit.jupiter.api.Test;
import ua.mainacademy.model.ConnectionInfo;

import static ua.mainacademy.util.RandomConnectionInfo.getRandomConnectionInfo;

class RandomConnectionInfoTest {

    @Test
    void getRandomConnectionInfoTest() {
        assert (getRandomConnectionInfo().getClass().equals(ConnectionInfo.class));
    }

}