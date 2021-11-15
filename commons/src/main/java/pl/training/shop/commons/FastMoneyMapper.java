package pl.training.shop.commons;

import org.javamoney.moneta.FastMoney;
import org.mapstruct.Mapper;

@Mapper
public class FastMoneyMapper {

    public FastMoney toMoney(String text) {
        return text != null ? FastMoney.parse(text) : LocalMoney.zero();
    }

    public String toText(FastMoney money) {
        return money != null ? money.toString() : LocalMoney.zero().toString();
    }

}
