package lesson4.homework.task1;

import java.util.*;

/**
 * t1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
 * Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
 * Посчитать, сколько раз встречается каждое слово.
 */
public class UniqWordsApp {

    private static final String WORD = "Word";
    private static final int SIZE = 20;

    /**
     * Создаем массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
     *
     * @return массив из 20 повторяющихся слов
     */
    private static String[] createWordsArr() {
        Random rnd = new Random();
        String[] strArr = new String[SIZE];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = WORD + rnd.nextInt(SIZE/2);
        }
        return strArr;
    }

    public static void main(String[] args) {

        String[] wordsArr = createWordsArr();
        System.out.println("Первоначальный массив:");
        System.out.println(Arrays.toString(wordsArr));

        // определяем уникальные слова и подсчитываем их количество
        HashMap<String, Integer> wordsMap = new HashMap<>();
        for (String word : wordsArr) {
            wordsMap.put(word, wordsMap.getOrDefault(word, 0) + 1);
        }

        // выводим список уникальных слов
        System.out.println("Уникальные слова:");
        System.out.println(wordsMap.keySet());

        // выводим, сколько раз встречается каждое слово
        System.out.println("Сколько раз встречаются слова:");
        for (String word : wordsMap.keySet()) {
            System.out.print(word + ": " + wordsMap.get(word) + "; ");
        }

    }
}
