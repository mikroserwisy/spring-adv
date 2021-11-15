package pl.training.shop.orders;

import lombok.Data;

import java.util.Map;

@Data
public class Order {

    private Long id;
    private Map<Long, Integer> entries;

}
