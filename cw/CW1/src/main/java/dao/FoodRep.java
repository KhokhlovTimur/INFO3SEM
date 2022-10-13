package dao;

import models.Food;
import models.User;
import providerss.MyDriverManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FoodRep {

    //    //language=SQL
//    private static final String SQL_UPDATE_FILM = "update films set title=?, " +
//            "genre=?, budget=? where id=?";
//
    //language=SQL
    private static final String SQL_FIND_BY_ID = "select * from food where id = ?";

    //language=SQL
    private static final String SQL_FIND_ALL= "select * from food";
//
//    //language=SQL
//    private static final String SQL_DELETE_FILM_BY_ID = "delete from films where id = ?";
//
//    //language=SQL
//    private static String SQL_SORT_FILMS_BY = "select * from films order by ";

    public List<Food> findAll() {
        List<Food> foods = new ArrayList<>();
        try (Connection connection = MyDriverManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                foods.add(Food.builder().id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .price(resultSet.getInt("price")).build());
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        return foods;
    }

    public Optional<Food> findEntity(Long id) {
        try (Connection connection = MyDriverManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)) {

            statement.setLong(1, id);
            statement.execute();

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(Food.builder().id(resultSet.getLong("id"))
                            .name(resultSet.getString("name"))
                            .price(resultSet.getInt("price")).build());
                } else {
                    return Optional.empty();
                }
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
