package pl.training.shop.orders.rest;

import lombok.Data;

import java.util.Map;

@Data
class OrderDto {

    private Long id;
    private Map<Long, Integer> entries;

}
