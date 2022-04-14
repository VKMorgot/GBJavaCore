package lesson4.homework.task2;

import java.util.Random;

public class PhoneBookApp {

    // массив кодов номеров телефонов
    private static final String[] PHONES = {"923", "919", "910", "962", "982", "903", "965", "905", "925"};

    // массив фамилий
    private static final String[] SURNAMES = {"Иванов", "Петров", "Сидоров", "Жданов", "Лебедев"};

    private static final Random rnd = new Random();


    /**
     * Возвращает номер телефона сотрудника в формате: +79239581232
     * @param phones массив номеров
     * @return номер телефона
     */
    private static String getPhoneNumber(String[] phones){
        return "+7" + phones[rnd.nextInt(phones.length)] + (int)(rnd.nextFloat() * 10000000);
    }

    public static void main(String[] args) {
        // создаем телефонный справочник с повторяющимися фамилиями
        PhoneBook phoneBook = new PhoneBook();
        for (int i = 0; i < SURNAMES.length * 2; i++) {
            phoneBook.add(SURNAMES[rnd.nextInt(SURNAMES.length)], getPhoneNumber(PHONES));
        }

        // читаем справочник, проверяем все фамилии из массива фамилий
        // выводим фамилию и список номеров телефонов, если они есть
        // если номеров нет, выводим сообщение, что номер не найден
        System.out.println("Список телефонов:");
        for (String lastName : SURNAMES) {
            System.out.print(lastName + ": " + phoneBook.get(lastName) + "\n");
        }
    }
}
