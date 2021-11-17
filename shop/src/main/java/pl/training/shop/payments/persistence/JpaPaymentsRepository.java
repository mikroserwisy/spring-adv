package pl.training.shop.payments.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

interface JpaPaymentsRepository extends JpaRepository<PaymentEntity, String>, JpaPaymentsRepositoryExtension, JpaSpecificationExecutor<PaymentEntity> {

    Page<PaymentEntity> getByStatus(String status, Pageable pageable);

    @Query("select p from PaymentEntity p where p.status = 'STARTED' and p.value = :value")
    Page<PaymentEntity> getStartedWithValue(@Param("value") String value, Pageable pageable);

    @Query("select new pl.training.shop.payments.persistence.PaymentEntityView(p.id, p.status) from PaymentEntity p where p.id = :id")
    Optional<PaymentEntityView> getViewById(String id);

    @Query("select p.id as id, p.status as status from PaymentEntity p where p.id = :id")
    Optional<PaymentDescription> getDescriptionById(String id);

    @EntityGraph(attributePaths = { "properties" })
    //@EntityGraph(value = PaymentEntity.SELECT_WITH_PROPERTIES, type = EntityGraph.EntityGraphType.FETCH)
    @Query("select p from PaymentEntity p where p.id = :id")
    Optional<PaymentEntity> getCutomById(String id);

}
