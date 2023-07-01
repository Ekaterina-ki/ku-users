import java.sql.*;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class UserRespository {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    private static final String FIND_BY_ID_QUERY = """
            SELECT * FROM users WHERE id = ?
                """;

    private static final String SAVE = """
            INSERT INTO users(name, surname, age, username, password, inserted_date_at_utc)\s
            values (?, ?, ?, ?, ?, ?);""";

    private static final String DELETE_BY_ID = """
            DELETE FROM users WHERE id = ?
            """;

    private static final String UPDATE_BY_ID = """
            UPDATE users SET name = ? WHERE id = ?
            """;

    private static final String FIND_BY_ALL = """
            SELECT * FROM users u
            """;



    public List <User> findByAll () throws SQLException {
        List<User> users = new ArrayList<>();
        try (
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ALL)
        ) {
               try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setUserName(resultSet.getString("username"));
                    user.setSurname(resultSet.getString("surname"));
                    user.setPassword(resultSet.getString("password"));
                    user.setAge(resultSet.getInt("age"));
                    user.setUserName(resultSet.getString("name"));
                    users.add(user);


                }
                return users;
            }
        }
    }





    public void UpdateById (Long id, String name) throws SQLException {
        try (
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID)

        ) {
            preparedStatement.setLong(2,id);
            preparedStatement.setString(1, name);
            preparedStatement.execute();
        }
    }







    public void DeleteById(Long id) throws SQLException {
        try (
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)
        ) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        }

    }

    public void save(User user) throws SQLException {
        try (
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(SAVE)
        ) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setLong(3, user.getAge());
            preparedStatement.setString(4, user.getUserName());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setTimestamp(6, new Timestamp(user.getInsertedAtUtc().toInstant(ZoneOffset.UTC).toEpochMilli()));
            preparedStatement.execute();
        }
    }




    public User findById (Long id) throws SQLException {

        try (
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY)
        ) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                User user = new User();
                while (resultSet.next()) {
                    user.setId(resultSet.getLong("id"));
                    user.setUserName(resultSet.getString("username"));
                }
                return user;
            }
        }
    }
}
        //Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);


