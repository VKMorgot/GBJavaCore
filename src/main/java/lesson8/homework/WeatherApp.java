package lesson8.homework;

import java.io.IOException;

public class WeatherApp {

    public static void main(String[] args) throws IOException, MyObjectSaveException {
        UserInterface userInterface = new UserInterface();
        userInterface.runApplication();
    }

}
