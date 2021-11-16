package pl.training.shop.payments.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import pl.training.shop.commons.jpa.SearchCriteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Set;

@RequiredArgsConstructor
public class PaymentsSpecification implements Specification<PaymentEntity> {

    private final Set<SearchCriteria> searchCriteria;

    @Override
    public Predicate toPredicate(Root<PaymentEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        var predicates = searchCriteria.stream()
                .map(criteria -> map(criteria, root, criteriaBuilder))
                .toArray(Predicate[]::new);
        return criteriaBuilder.and(predicates);
    }

    private Predicate map(SearchCriteria searchCriteria, Root<PaymentEntity> root, CriteriaBuilder builder) {
        var property = searchCriteria.getProperty();
        var value = searchCriteria.getValue();
        switch (searchCriteria.getOperator()) {
            case GREATER_THAN:
                return builder.greaterThan(root.get(property), value.toString());
            case LESS_THAN:
                return builder.lessThan(root.get(property), value.toString());
            case EQUAL:
                return builder.equal(root.get(property), value);
            case NOT_EQUAL:
                return builder.notEqual(root.get(property), value);
            case MATCH:
                return builder.like(root.get(property), "%" + value + "%");
            case MATCH_START:
                return builder.like(root.get(property),  value + "%");
            case IN:
                return builder.in(root.get(property)).value(value);
            default:
                throw new IllegalArgumentException();
        }
    }

}
