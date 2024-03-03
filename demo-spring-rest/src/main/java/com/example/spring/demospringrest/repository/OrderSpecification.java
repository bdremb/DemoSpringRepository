package com.example.spring.demospringrest.repository;

import com.example.spring.demospringrest.model.Client;
import com.example.spring.demospringrest.model.Order;
import com.example.spring.demospringrest.web.model.OrderFilter;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.Instant;

public interface OrderSpecification {

    static Specification<Order> withFilter(OrderFilter orderFilter) {
        return Specification.where(byProductName(orderFilter.getProductName()))
                .and(byCostRange(orderFilter.getMinCost(), orderFilter.getMaxCost()))
                .and(byClientId(orderFilter.getClientId()))
                .and(byCreatedAtBefore(orderFilter.getCreateBefore()))
                .and(byUpdatedAtBefore(orderFilter.getUpdateBefore()));
    }

    static Specification<Order> byUpdatedAtBefore(Instant updateBefore) {
        return (root, query, criteriaBuilder) -> {
            if (updateBefore == null) {
                return null;
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get(Order.Fields.updatedAt), updateBefore);
        };
    }

    static Specification<Order> byCreatedAtBefore(Instant createBefore) {
        return (root, query, criteriaBuilder) -> {
            if (createBefore == null) {
                return null;
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get(Order.Fields.createdAt), createBefore);
        };
    }


    static Specification<Order> byClientId(Long clientId) {
        return (root, query, criteriaBuilder) -> {
            if (clientId == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get(Order.Fields.client).get(Client.Fields.id), clientId);
        };
    }

    static Specification<Order> byCostRange(BigDecimal minCost, BigDecimal maxCost) {
        return (root, query, criteriaBuilder) -> {
            if (minCost == null && maxCost == null) {
                return null;
            }
            if (minCost == null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get(Order.Fields.cost), maxCost);
            }
            if (maxCost == null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get(Order.Fields.cost), minCost);
            }
            return criteriaBuilder.between(root.get(Order.Fields.cost), minCost, maxCost);
        };
    }

    static Specification<Order> byProductName(String productName) {
        return (root, query, criteriaBuilder) -> {
            if (productName == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get(Order.Fields.product), productName);
        };
    }

}
