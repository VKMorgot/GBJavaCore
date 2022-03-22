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
            "INSERT INTO Weather (city, day_date, temperature, feels_like, weather_text) VALUES (?,?,?,?,?);";
    private final String GET_ALL_WEATHER_QUERY = "SELECT * FROM Weather;";
    private final String GET_CITY_WEATHER_FOR_DATE_QUERY = "SELECT * FROM Weather WHERE city = '?' AND day_date = '?'";
    private final String GET_WEATHER_FOR_DATE_QUERY = "SELECT * FROM Weather WHERE day_date = '?';";


    public SQLiteImplementation() throws IOException {
        fileName = ApplicationGlobalState.getInstance().getDB_FILE_NAME();

        createTableIfNotExists();
    }

    @Override
    public void saveWeatherData(Weather weather) {
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
            return getWeatherDataFromResultSet(resultSet);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        throw new MyObjectSaveException("Ошибка в получении объекта из базы данных");
    }

    @Override
    public ArrayList<Weather> getCitySavedDataForDate(String cityName, String date) throws MyObjectSaveException {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(GET_CITY_WEATHER_FOR_DATE_QUERY)) {
            preparedStatement.setString(1, cityName);
            preparedStatement.setString(2, date);
            preparedStatement.addBatch();
            ResultSet resultSet = preparedStatement.executeQuery();
            return getWeatherDataFromResultSet(resultSet);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        throw new MyObjectSaveException("Ошибка в получении объекта из базы данных");
    }

    @Override
    public ArrayList<Weather> getSavedDataForDate(String date) throws MyObjectSaveException{
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(GET_WEATHER_FOR_DATE_QUERY)) {
            preparedStatement.setString(1, date);
            preparedStatement.addBatch();
            ResultSet resultSet = preparedStatement.executeQuery();
            return getWeatherDataFromResultSet(resultSet);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        throw new MyObjectSaveException("Ошибка в получении объекта из базы данных");
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

    /**
     * Сохраняем данные из результатов запроса к базе данных в объекты класса
     * @param resultSet результат запроса к БД
     * @return массив погоды из полученных строк
     * @throws SQLException исключение
     */
    private ArrayList<Weather> getWeatherDataFromResultSet(ResultSet resultSet) throws SQLException {
        ArrayList<Weather> weatherArrayList = new ArrayList<>();
        while (resultSet.next()) {
            Weather weather = new Weather();
            weather.setGeoObject().setLocalityObject().setName("city");
            weather.setForecasts().get(0).setDate(resultSet.getString("day_date"));
            weather.setForecasts().get(0).setPartsObject().setDayShortObject().setTemp(resultSet.getFloat("temperature"));
            weather.setForecasts().get(0).setPartsObject().setDayShortObject().setFeelsLike(resultSet.getFloat("feels_like"));
            weather.setForecasts().get(0).setPartsObject().setDayShortObject().setCondition(resultSet.getString("weather_text"));
            weatherArrayList.add(weather);
        }
        return weatherArrayList;
    }
}
