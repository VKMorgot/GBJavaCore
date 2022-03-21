package lesson8.homework;

import java.io.IOException;
import java.util.Scanner;


public class UserInterface {

    private final String EXIT = "0";
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Проверяем корректность ввода периода для прогноза погоды
     *
     * @param inputStr введенный период
     * @return если введенный период соответсвует предустановленным в Period
     */
    private boolean isInputValid(String inputStr) {
        for (Period period : Period.values()) {
            if (period.getInputNumber().equals(inputStr)) return true;
        }
        return false;
    }

    /**
     * Выбираем период для прогноза погоды
     *
     * @return код выбранного действия
     */
    private Period getPeriod() {
        String periodInput;
        // todo дополнить меню получением прогноза погода по указанной дате
        // todo дополнить меню выводом всех имеющихся данных
        // todo можно попробовать сделать вывод и для города, и для всех данных в таблице
        do {
            System.out.printf("Выберите дальнейшее действие: \n" +
                            "%s - Получить текущую погоду \n" +
                            "%s - Получить погоду на следующие 5 дней\n",
                    Period.NOW.getInputNumber(),
                    Period.FIVE_DAYS.getInputNumber());
            periodInput = scanner.nextLine();
        } while (!isInputValid(periodInput));

        for (Period period : Period.values()) {
            if (period.getInputNumber().equals(periodInput)) return period;
        }

        return null;
    }

    /**
     * Определяем город для поиска погоды
     *
     * @return название города
     */
    private String getCityName() {
        System.out.println("Введите название города на русском языке");
        return scanner.nextLine();
    }

    /**
     * Проверяем, если пользователь хочет продолжить работу с программой
     */
    private void isContinue() {
        System.out.println("Для выхода нажмите '0'");
        System.out.println("Для продолжения нажмите клавишу ввода");
        if (scanner.nextLine().equals(EXIT)) System.exit(0);
    }

    public void runApplication() throws IOException {
        System.out.println("Добро пожаловать в программу по определению погоды");
        while (true) {

            City city = new City(getCityName());
            if (city.isFound()) {

                WeatherProvider weatherProvider = new YandexWeatherProvider();
                weatherProvider.getWeather(city, getPeriod());

            } else {
                System.out.println("Попробуйте ввести город еще раз");
            }
            isContinue();
        }
    }
}
