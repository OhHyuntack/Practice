package com.example.demo.common;

import com.example.demo.user.domain.entity.Board;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class BoardSpecs {

  public static Specification<Board> titleLike(final String keyword) {
    return new Specification<Board>() {
      @Override
      public Predicate toPredicate(Root<Board> root,
          CriteriaQuery<?> query, CriteriaBuilder cb) {
        return cb.like(root.get("title"), "%" + keyword + "%");
      }
    };
  }

  public static Specification<Board> contentLike(final String keyword) {
    return new Specification<Board>() {
      @Override
      public Predicate toPredicate(Root<Board> root,
          CriteriaQuery<?> query, CriteriaBuilder cb) {
        return cb.like(root.get("content"), "%" + keyword + "%");
      }
    };
  }

  /*public static Specification<Board> category(final Category category) {
    return new Specification<Board>() {
      @Override
      public Predicate toPredicate(Root<Board> root,
          CriteriaQuery<?> query, CriteriaBuilder cb) {
        return cb.equal(root.get("content"), category);
      }
    };
  }*/
}
