package pl.training.shop.orders;

import lombok.Data;
import org.javamoney.moneta.FastMoney;

@Data
public class Product {

    private Long id;
    private String name;
    private FastMoney price;

}
