package pl.training.shop.payments.persistence;

import lombok.Setter;
import org.javamoney.moneta.FastMoney;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static pl.training.shop.payments.persistence.PaymentEntity.SELECT_WITH_VALUE_GREATER_THEN;

class JpaPaymentsRepositoryImpl implements JpaPaymentsRepositoryExtension {

    @Setter
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<PaymentEntity> getWithValueGreaterThen(FastMoney value) {
        return entityManager.createNamedQuery(SELECT_WITH_VALUE_GREATER_THEN, PaymentEntity.class)
                .setParameter("value", value)
                .getResultList();
    }

}
