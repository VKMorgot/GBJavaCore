package lesson8.homework;

/**
 * Перечисление для возможных периодов показа прогноза погоды
 */
public enum Period {
    NOW("1", "1"),
    FIVE_DAYS("2", "5"),
    CUSTOM_CITY_DATE("3","0"),
    CUSTOM_DATE("4","0"),
    CUSTOM_ALL("5","0");

    private final String inputNumber;
    private final String days;

    public String getInputNumber() {
        return inputNumber;
    }

    public String getDays() {
        return days;
    }

    Period(String inputNumber, String daysPeriod) {
        this.inputNumber = inputNumber;
        this.days = daysPeriod;
    }
}
