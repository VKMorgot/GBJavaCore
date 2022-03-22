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
     * Выбираем дальнейшие действия для работы с прогнозом погоды
     *
     * @return код выбранного действия
     */
    private Period getPeriod() {
        String periodInput;
        do {
            System.out.printf("Выберите дальнейшее действие: \n" +
                            "%s - Получить текущую погоду \n" +
                            "%s - Получить погоду на следующие 5 дней \n",
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

    /**
     * Выбор режима работы: поиск прогноза погоды или работа с базой данных
     *
     * @return режим работы
     */
    private String activateMode() {
        String mode;
        do {
            System.out.print("Выберите режим работы: \n" +
                    "1 - Поиск погоды \n" +
                    "2 - Работа с базой данных \n");
            mode = scanner.nextLine();
        } while (!mode.equals("1") && !mode.equals("2"));
        return mode;
    }

    private String activateModeSQL() {
        String mode;
        do {
            System.out.print("Выберите действие: \n" +
                    "1 - Прочитать данные из базы в городе по выбранной дате \n" +
                    "2 - Прочитать данные из базы по дате \n" +
                    "3 - Прочитать все данные из базы \n");
            mode = scanner.nextLine();
        } while (!mode.equals("1") && !mode.equals("2") && !mode.equals("3"));
        return mode;
    }

    public void runApplication() throws IOException, MyObjectSaveException {
        System.out.println("Добро пожаловать в программу по определению погоды");
        while (true) {
            switch (activateMode()) {
                // ищем погоду в интернете
                case "1":
                    City city = new City(getCityName());
                    if (city.isFound()) {
                        WeatherProvider weatherProvider = new YandexWeatherProvider();
                        weatherProvider.getWeather(city, getPeriod());
                    } else {
                        System.out.println("Попробуйте ввести город еще раз");
                    }
                    break;
                // работаем с базой данных
                case "2":
                    switch (activateModeSQL()) {
                        case "1":
                            // печать данных по указанному городу и дате
                            System.out.println("Введите название города:");
                            String cityName = scanner.nextLine();
                            System.out.println("Введите дату в формате yyyy-mm-dd:");
                            String weatherDay = scanner.nextLine();
                            cityName = cityName.substring(0, 1).toUpperCase() + cityName.substring(1);
                            new InterfaceForDatabase().printCityDateData(cityName, weatherDay);
                            break;
                        case "2":
                            // печать данных по указанной дате
                            System.out.println("Введите дату в формате yyyy-mm-dd:");
                            String day = scanner.nextLine();
                            new InterfaceForDatabase().printDateData(day);
                            break;
                        case "3":
                            // печать всех данных из базы
                            new InterfaceForDatabase().printAllData();
                            break;
                    }
                    break;
            }
            isContinue();
        }
    }
}
