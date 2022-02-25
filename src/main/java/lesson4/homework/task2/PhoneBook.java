package lesson4.homework.task2;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * t2. Написать простой класс «Телефонный Справочник», который хранит в себе список фамилий и телефонных номеров.
 * В этот телефонный справочник с помощью метода add() можно добавлять записи,
 * а с помощью метода get() искать номер телефона по фамилии.
 * Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев),
 * тогда при запросе такой фамилии должны выводиться все телефоны.
 * <p>
 * <i>Желательно не добавлять лишний функционал (дополнительные поля (имя, отчество, адрес),
 * взаимодействие с пользователем через консоль и т.д).
 * Консоль использовать только для вывода результатов проверки телефонного справочника.</i>
 */
public class PhoneBook {

    // телефонный справочник
    private final HashMap<String, String> phoneBook = new HashMap<>();

    /**
     * Добавляем запись в телефонный справочник
     * @param lastName фамилия
     * @param phoneNumber телефон
     */
    public void add(String lastName, String phoneNumber) {
        this.phoneBook.put(phoneNumber, lastName);
    }

    /**
     * Ищем номер телефона по фамилии
     * @param lastName фамилия
     * @return список номеров телефона, относящихся к фамилии
     */
    public ArrayList<String> get(String lastName) {
        ArrayList<String> numList = new ArrayList<>();
        for (String keyNumber : phoneBook.keySet()) {
            if (phoneBook.get(keyNumber).equals(lastName)) {
                numList.add(keyNumber);
            }
        }
        if (numList.size() == 0) numList.add("номер телефона не найден");
        return numList;
    }
}
