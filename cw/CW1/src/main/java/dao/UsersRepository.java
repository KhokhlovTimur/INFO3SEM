package dao;

import models.Food;
import models.User;
import providerss.MyDriverManager;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UsersRepository {
    //language=SQL
    private static final String SQL_ADD_USER = "insert into mac_user (login, password)" +
            "values (?, ?)";

//
    //language=SQL
    private static final String SQL_FIND_BY = "select * from mac_user where login = ? and password = ?";

    //language=SQL
    private static final String SQL_FIND_BY_ID = "select * from mac_user where id=?";

    public void save(User user) {
        if (user.getLogin().length() > 1 && user.getPassword().length() > 1) {
            try (Connection connection = MyDriverManager.getConnection();
                 PreparedStatement statement = connection.prepareStatement(SQL_ADD_USER, Statement.RETURN_GENERATED_KEYS)) {

                statement.setString(1, user.getLogin());
                statement.setString(2, user.getPassword());

                int affectedRows = statement.executeUpdate();

                if (affectedRows != 1) {
                    throw new SQLException("Can't save user");
                }

                ResultSet keys = statement.getGeneratedKeys();
                if (keys.next()) {
                    user.setId(keys.getLong("id"));
                } else {
                    throw new SQLException();
                }
            } catch (SQLException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }


    public Optional<User> findEntity(String login, String password) {
        try (Connection connection = MyDriverManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY)) {

            statement.setString(1, login);
            statement.setString(2, password);
            statement.execute();

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(User.builder().id(resultSet.getLong("id"))
                            .login(resultSet.getString("login"))
                            .password(resultSet.getString("password")).build());
                } else {
                    return Optional.empty();
                }
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Optional<User> findEntityById(Long id) {
        try (Connection connection = MyDriverManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)) {

            statement.setLong(1, id);
            statement.execute();

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(User.builder().id(resultSet.getLong("id"))
                            .login(resultSet.getString("login"))
                            .password(resultSet.getString("password")).build());
                } else {
                    return Optional.empty();
                }
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }


}
