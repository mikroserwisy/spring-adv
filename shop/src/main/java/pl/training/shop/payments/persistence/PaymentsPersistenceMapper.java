package pl.training.shop.payments.persistence;

import org.mapstruct.Mapper;
import pl.training.shop.payments.Payment;

@Mapper(componentModel = "spring")
public interface PaymentsPersistenceMapper {

    PaymentEntity toEntity(Payment payment);

    Payment toModel(PaymentEntity paymentEntity);

}
