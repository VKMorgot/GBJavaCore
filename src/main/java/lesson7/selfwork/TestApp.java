package lesson7.selfwork;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class TestApp {

    private static final String TESTJSON = "./src/main/java/lesson7/test/test.json";

    public static void main(String[] args) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        TestJson testJson = objectMapper.readValue(new File(TESTJSON), TestJson.class);

        System.out.println(testJson.getInfo().getTzinfo().getName());

    }
}
