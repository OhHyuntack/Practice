package com.example.demo.common;

import com.example.demo.user.domain.entity.Board;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class BoardSpecification {

  public static Specification<Board> searchBoard(Map<String, String> filter) {
    return (root, query, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList();
      String key = "";
      String value = "";

      for (Map.Entry<String, String> entry : filter.entrySet()) {
        key = entry.getKey();
        value = entry.getValue();
      }
      String likeValue = "%" + value + "%";
      /*Predicate pred = cb.and(cb.like(cb.lower(root.<String>get("tenant")), containsLikePatternForTenant),
          cb.equal(cb.lower(root.<String>get("billingCode")), whoOwnsIt.getBillingCode()),
          cb.greaterThanOrEqualTo(root.<Date>get("scheduleDate"), formattedFromDate),
          cb.lessThanOrEqualTo(root.<Date>get("scheduleDate"), formattedToDate));*/

      switch (key) {
        case "title":
          predicates.add(criteriaBuilder.like(root.get(key).as(String.class), likeValue));
          break;
        case "content":
          predicates.add(criteriaBuilder.like(root.get(key).as(String.class), likeValue));
          break;
        case "all":
          predicates.add(criteriaBuilder.or(criteriaBuilder.like(root.get("content").as(String.class), likeValue),
              criteriaBuilder.like(root.get("title").as(String.class), likeValue)));
          break;
      }
      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    };
  }

}
