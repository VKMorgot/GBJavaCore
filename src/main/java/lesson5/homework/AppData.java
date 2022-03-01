package lesson5.homework;

import java.util.Arrays;

/**
 * Класс для хранения и печати данных
 */
public class AppData {

    private static final String SEPARATOR = ";";

    private final String[] header;
    private final int[][] data;

    public AppData(String[] header, int[][] data) {
        this.header = header;
        this.data = data;
    }

    /**
     * Метод для подготовки объекта к печати в файл или консоль
     * @return строка для вывода
     */
    public String toPrintData() {
        StringBuilder printData = new StringBuilder();
        for (String headValue : header) {
            printData.append(headValue).append(SEPARATOR);
        }
        printData.append(System.lineSeparator());
        for (int[] itemI : data) {
            for (int itemJ : itemI) {
                printData.append(itemJ).append(SEPARATOR);
            }
            printData.append(System.lineSeparator());
        }
        return new String(printData);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppData appData = (AppData) o;
        return Arrays.equals(header, appData.header) && Arrays.deepEquals(data, appData.data);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(header);
        result = 31 * result + Arrays.deepHashCode(data);
        return result;
    }
}
