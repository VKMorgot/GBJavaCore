package lesson5.homework;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class SaveCSVApp {

    static final String FILE_NAME = "./src/main/java/lesson5/homework/data.csv";
    static final String SEPARATOR = ";";
    static final String HEADER_VALUE = "Value";
    // число столбцов в файле, а так же размер
    static int size;

    /**
     * Метод для создания заголовка
     *
     * @return заголовок
     */
    private static String[] createHeader() {
        Random rnd = new Random();
        size = rnd.nextInt(10) + 1;
        String[] dataHeader = new String[size];
        for (int i = 0; i < dataHeader.length; i++) {
            dataHeader[i] = HEADER_VALUE + " " + (i + 1);
        }
        return dataHeader;
    }

    /**
     * Метод для создания данных в таблице
     *
     * @return таблица данных
     */
    private static int[][] createDataTable() {
        Random rnd = new Random();
        int[][] dataInt = new int[rnd.nextInt(100) + 1][size];
        for (int i = 0; i < dataInt.length; i++) {
            for (int j = 0; j < dataInt[i].length; j++) {
                dataInt[i][j] = rnd.nextInt(900) + 100;
            }
        }
        return dataInt;
    }

    /**
     * Метод для создания объекта
     *
     * @return объект
     */
    private static AppData createDataObj() {
        return new AppData(createHeader(), createDataTable());
    }

    /**
     * Записываем данные в файл
     *
     * @param data     объект для записи
     * @param fileName путь к файлу для записи
     * @throws IOException если нет указанного файла
     */
    private static void writeData(AppData data, String fileName) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(data.toPrintData());
        }
    }

    /**
     * Метод для чтения данных из файла
     *
     * @param fileName путь к файлу для чтения
     * @return список строковых массивов
     * @throws IOException если нет указанного файла
     */
    private static ArrayList<String[]> readData(String fileName) throws IOException {
        ArrayList<String[]> strArr = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                strArr.add(str.split(SEPARATOR));
            }
        }
        return strArr;
    }

    /**
     * Переводим данные таблицы из файла в массив чисел
     *
     * @param dataFromFile данные, прочитанные из файла
     * @return массив чисел
     */
    private static int[][] saveData(ArrayList<String[]> dataFromFile) {
        int[][] dataTable = new int[dataFromFile.size() - 1][];
        for (int i = 0; i < dataFromFile.size() - 1; i++) {
            dataTable[i] = new int[dataFromFile.get(i + 1).length];
            for (int j = 0; j < dataFromFile.get(i + 1).length; j++) {
                dataTable[i][j] = Integer.parseInt(dataFromFile.get(i + 1)[j]);
            }
        }
        return dataTable;
    }

    /**
     * Сохраняем заголовок таблицы из файла
     *
     * @param dataFromFile данные, прочитанные из файла
     * @return заголовок таблицы
     */
    private static String[] saveHeader(ArrayList<String[]> dataFromFile) {
        return dataFromFile.get(0);
    }

    /**
     * Сохраняем данные, полученные из файла, в объект
     *
     * @param dataFromFile данные, прочитанные из файла
     * @return объект
     */
    private static AppData saveDataObj(ArrayList<String[]> dataFromFile) {
        return new AppData(saveHeader(dataFromFile), saveData(dataFromFile));
    }

    public static void main(String[] args) throws IOException {

        // создаем объект для записи данных
        AppData dataObjOut = createDataObj();

        // записываем объект в файл
        writeData(dataObjOut, FILE_NAME);

        // создаем объект из прочитанных данных
        AppData dataObjIn = saveDataObj(readData(FILE_NAME));

        // выводим новый объект в консоль
        System.out.println(dataObjIn.toPrintData());

        // проверяем, что созданный из файла и созданный в коде объекты одинаковы
        if (dataObjOut.equals(dataObjIn)) {
            System.out.println("Созданный из файла и созданный кодом объекты одинаковы");
        } else {
            System.out.println("Объекты не одинаковы");
        }

    }
}
