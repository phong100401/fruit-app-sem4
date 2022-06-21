package com.example.restapiprojectsem4.specification;

import com.example.restapiprojectsem4.dto.ProductDTO;
import com.example.restapiprojectsem4.entity.Product;
import com.example.restapiprojectsem4.util.SQLConstant;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductSpecification implements Specification<Product> {
    private SearchCriteria criteria;

    public ProductSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criteria.getOperation().equalsIgnoreCase(SQLConstant.GREATER_THAN_OR_EQUAL_TO)) {
            return builder.greaterThanOrEqualTo(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase(SQLConstant.LESS_THAN_OR_EQUAL_TO)) {
            return builder.lessThanOrEqualTo(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase(SQLConstant.LIKE)) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                        root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }else if (criteria.getOperation().equalsIgnoreCase(SQLConstant.EQUAL)){
            return builder.equal(
                    root.<String>get(criteria.getKey()), criteria.getValue() );
        }
        return null;
    }

    public Specification<Product> filter(final ProductDTO criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(StringUtils.hasLength(criteria.getName())) {
                predicates.add(cb.like(cb.upper(root.get("name")), "%" + criteria.getName().trim().toUpperCase()+ "%"));
            }

            return cb.and(predicates.stream().toArray(Predicate[]::new));
        };

    }
}
