package lesson7.homework;

/**
 * Перечисление для возможных периодов показа прогноза погоды
 */
public enum Period {
    NOW("1", "1"),
    FIVE_DAYS("2", "5");

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
