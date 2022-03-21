package lesson8.homework;

public class ApplicationGlobalState {
    /**
     * Класс для задания внутренних данных для работы приложения
     */
    //todo добавить сюда пути ко всем файлам настроек, что встречаются в приложении
    private static ApplicationGlobalState INSTANCE;
    private final String GEO_API_KEY = "15b3532e-7ae4-4246-9430-73b756e6dc4d";
    private final String X_YANDEX_API_KEY = "7cd4e8fb-f652-4276-b372-9dd9e199775d";
    private final String DB_FILE_NAME = "lesson8.homework.db";

    public static ApplicationGlobalState getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ApplicationGlobalState();
        }
        return INSTANCE;
    }

    public String getGEO_API_KEY() {
        return GEO_API_KEY;
    }

    public String getX_YANDEX_API_KEY() {
        return X_YANDEX_API_KEY;
    }

    public String getDB_FILE_NAME() {
        return DB_FILE_NAME;
    }
}
