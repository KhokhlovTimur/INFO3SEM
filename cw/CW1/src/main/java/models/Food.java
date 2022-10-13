package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Food {
    private Long id;
    private String name;
    private int price;
    private int quantity;
}
