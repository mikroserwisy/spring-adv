package pl.training.shop.payments.persistence;

import org.javamoney.moneta.FastMoney;

import java.util.List;

interface JpaPaymentsRepositoryExtension {

    List<PaymentEntity> getWithValueGreaterThen(FastMoney value);

}
