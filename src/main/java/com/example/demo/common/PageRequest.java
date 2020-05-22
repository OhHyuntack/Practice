package com.example.demo.common;

public class PageRequest {
  protected Long page = 1l;// 1 is the first page
  protected Integer limit = 10;

  public PageRequest(Long page, Integer limit) {
    this.limit = limit;
    this.page = page;
  }

  public PageRequest() {
  }

  public Long getPage() {
    return page;
  }

  public Integer getLimit() {
    return limit;
  }

  public Long getOffset() {
    return (page - 1l) * limit;
  }
}