package com.tixthefox.DTO;

public class ShipFiltersWithPaginationDTO extends ShipFiltersRequestDTO {
  private Integer pageNumber = 0;
  private Integer pageSize = 5;

  public Integer getPageNumber() {
    return pageNumber;
  }

  public void setPageNumber(Integer pageNumber) {
    this.pageNumber = pageNumber;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }
}
