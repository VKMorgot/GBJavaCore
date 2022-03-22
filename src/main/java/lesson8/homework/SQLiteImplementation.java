package lesson8.homework;

import lesson8.homework.weather_data.Forecast;
import lesson8.homework.weather_data.Weather;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class SQLiteImplementation implements DatabaseRepository {

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private final String CONDITIONS = "./src/main/resources/lesson8.homework/conditions.properties";
    private final PropertiesProvider conditions = new PropertiesProvider(CONDITIONS);
    private final String fileName;

    private final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS Weather " +
            "(\n" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "city TEXT NOT NULL,\n" +
            "day_date TEXT NOT NULL,\n" +
            "temperature REAL NOT NULL,\n" +
            "feels_like REAL NOT NULL,\n" +
            "weather_text TEXT NOT NULL\n" +
            ");";
    private final String INSERT_WEATHER_QUERY =
            "INSERT INTO Weather (city, day_date, temperature, feels_like, weather_text) VALUES (?,?,?,?,?)";
    private final String GET_ALL_WEATHER_QUERY = "SELECT * FROM Weather";


    public SQLiteImplementation() throws IOException {
        fileName = ApplicationGlobalState.getInstance().getDB_FILE_NAME();

        createTableIfNotExists();
    }

    @Override
    public void saveWeatherData(Weather weather) {
        // todo предусмотреть вариант, если сохранять нечего
        try (PreparedStatement preparedStatement =
                     getConnection().prepareStatement(INSERT_WEATHER_QUERY)) {
            for (Forecast forecast : weather.getForecasts()) {
                preparedStatement.setString(1, weather.getGeoObject().getLocalityObject().getName());
                preparedStatement.setString(2, forecast.getDate());
                preparedStatement.setDouble(3, forecast.getPartsObject().getDayShortObject().getTemp());
                preparedStatement.setDouble(4, forecast.getPartsObject().getDayShortObject().getFeelsLike());
                preparedStatement.setString(5, forecast.getPartsObject().getDayShortObject().getCondition());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ArrayList<Weather> getAllSavedData() throws MyObjectSaveException {
        try (Connection connection = getConnection()) {
            ResultSet resultSet = connection.createStatement().executeQuery(GET_ALL_WEATHER_QUERY);

            ArrayList<Weather> weatherArrayList = new ArrayList<>();
            while (resultSet.next()) {
                Weather weather = new Weather();
                weather.getGeoObject().getLocalityObject().setName(resultSet.getString("city"));
                weather.getForecasts().get(0).setDate(resultSet.getString("day_date"));
                weather.getForecasts().get(0).getPartsObject().getDayShortObject().setTemp(resultSet.getFloat("temperature"));
                weather.getForecasts().get(0).getPartsObject().getDayShortObject().setFeelsLike(resultSet.getFloat("feels_like"));
                weather.getForecasts().get(0).getPartsObject().getDayShortObject().setCondition(resultSet.getString("weather_text"));

                weatherArrayList.add(weather);
            }

            return weatherArrayList;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        throw new MyObjectSaveException("Ошибка в получении объекта из базы данных");
    }

    @Override
    public ArrayList<Weather> getCustomSavedData() {
        return null;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + fileName);
    }

    /**
     * Создаем таблицу, если она еще не существует
     */
    private void createTableIfNotExists() {
        try (Connection connection = getConnection()) {
            connection.createStatement().execute(CREATE_TABLE_QUERY);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
