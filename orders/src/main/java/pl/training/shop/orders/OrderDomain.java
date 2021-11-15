package pl.training.shop.orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.javamoney.moneta.FastMoney;
import pl.training.shop.commons.LocalMoney;

import java.util.List;

@AllArgsConstructor
@Data
class OrderDomain {

    private Long id;
    private List<OderEntryDomain> entries;

    FastMoney getTotalValue() {
        return entries.stream()
                .map(entry -> entry.getPrice().multiply(entry.getQuantity()))
                .reduce(LocalMoney.zero(), FastMoney::add);
    }

}
