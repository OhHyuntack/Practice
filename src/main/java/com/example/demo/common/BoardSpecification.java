package com.example.demo.common;

import com.example.demo.user.domain.entity.Board;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class BoardSpecification {

  public static Specification<Board> searchBoard(Map<String, String> filter){
    return (root, query, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList();
      String key = "";
      String value = "";

      for(Map.Entry<String,String> entry : filter.entrySet()){
        key = entry.getKey();
        value = entry.getValue();
      }
      String likeValue = "%" + value + "%";

      switch (key){
        case "title":
           predicates.add(criteriaBuilder.like(root.get(key).as(String.class), likeValue));
           break;
        case "content":
          predicates.add(criteriaBuilder.like(root.get(key).as(String.class), likeValue));
          break;
        case "all":

          break;
      }
      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    };
  }

}
