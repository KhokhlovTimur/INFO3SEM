package holder;

import dao.FoodRep;
import dao.OrderRepository;

import java.util.HashMap;
import java.util.Map;

public class RepositoriesHolder {
    public static Map<String, String> passwords = new HashMap<>();
    public static FoodRep foodRep = new FoodRep();
    public static OrderRepository orderRepository = new OrderRepository();
}
