package pl.training.shop.orders.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.training.shop.commons.streotype.RestAdapter;
import pl.training.shop.orders.PlaceOrderUseCase;

import static lombok.AccessLevel.PACKAGE;

@RequestMapping("orders")
@RestAdapter
@RequiredArgsConstructor(access = PACKAGE)
class PlaceOrderUseCaseRestAdapter {

    private final PlaceOrderUseCase placeOrderUseCase;
    private final OrdersRestMapper mapper;

    @PostMapping
    public ResponseEntity<Void> place(@RequestBody OrderDto orderDto) {
        var order = mapper.toModel(orderDto);
        placeOrderUseCase.place(order);
        return ResponseEntity.accepted().build();
    }

}
