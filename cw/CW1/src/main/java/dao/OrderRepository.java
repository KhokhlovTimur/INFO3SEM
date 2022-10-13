package dao;

import holder.RepositoriesHolder;
import models.Order;
import providerss.MyDriverManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    //language=SQL
    private static final String SQL_INSERT_order = "insert into mac_order ( food_id, food_name)" +
            "values ( ?, ?)";

    //language=SQL
    private static final String SQL_DELETE_PARTICIPATION = "delete from participation where actor_id = ? and film_id = ?";

    //language=SQL
    private static final String SQL_PRINT = "select mac_order.id as id, mac_order.food_id as food_id, f.name as name from mac_order join food f on mac_order.food_id = f.id";

    public void saveOrder(Long foodId) {
        try (Connection connection = MyDriverManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_order, Statement.RETURN_GENERATED_KEYS)) {
            if (RepositoriesHolder.foodRep.findEntity(foodId).isPresent()) {
//                statement.setLong(1, id);
                statement.setLong(1, foodId);
                statement.setString(2, RepositoriesHolder.foodRep.findEntity(foodId).get().getName());
                int affectedRows = statement.executeUpdate();

                if (affectedRows != 1) {
                    throw new SQLException("Can't save");
                }

            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }


    public List<Order> findAll() {
        List<Order> models = new ArrayList<>();
        try (Connection connection = MyDriverManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_PRINT)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                models.add(Order.builder()
                        .id(resultSet.getLong("id"))
                        .foodId(resultSet.getLong("food_id"))
                        .food_name(resultSet.getString("name"))
                        .build());
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        return models;
    }

//    public void delete(Long actorID, Long filmID) {
//        try (Connection connection = MyDriverManager.getConnection();
//             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_PARTICIPATION)) {
//            statement.setLong(1, actorID);
//            statement.setLong(2, filmID);
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            throw new IllegalArgumentException(e);
//        }
//    }
}
