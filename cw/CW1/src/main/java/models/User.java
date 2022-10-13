package models;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class User {
    private Long id;
    private String login;
    private String password;
    private boolean status;
    private List<Food> foods = new ArrayList<>();

    public List<Food> getFoods(User user) {
        return user.getFoods();
    }

    public void addFood(Food food) {
        if (food != null) {
            foods.add(food);
        }
    }
}
