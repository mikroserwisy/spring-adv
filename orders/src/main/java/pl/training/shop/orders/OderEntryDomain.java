package pl.training.shop.orders;

import lombok.Builder;
import lombok.Data;
import org.javamoney.moneta.FastMoney;

@Builder
@Data
class OderEntryDomain {

    private Long id;
    private FastMoney price;
    private int quantity;

}
