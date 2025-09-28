package com.tixthefox.DTO;

import com.tixthefox.entity.ShipType;

public class ShipFiltersRequestDTO {
  private String name;
  private String planet;
  private String shipType;
  private Long after;
  private Long before;
  private Boolean isUsed;
  private Double minSpeed;
  private Double maxSpeed;
  private Integer minCrewSize;
  private Integer maxCrewSize;
  private Double minRating;
  private Double maxRating;
  private String order;


  public ShipFiltersRequestDTO() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPlanet() {
    return planet;
  }

  public void setPlanet(String planet) {
    this.planet = planet;
  }

  public String getShipType() {
    return shipType;
  }

  public void setShipType(String shipType) {
    this.shipType = shipType;
  }

  public Long getAfter() {
    return after;
  }

  public void setAfter(long after) {
    this.after = after;
  }

  public Long getBefore() {
    return before;
  }

  public void setBefore(long before) {
    this.before = before;
  }

  public Boolean getIsUsed() {
    return isUsed;
  }

  public void setIsUsed(boolean used) {
    isUsed = used;
  }

  public Double getMinSpeed() {
    return minSpeed;
  }

  public void setMinSpeed(double minSpeed) {
    this.minSpeed = minSpeed;
  }

  public Double getMaxSpeed() {
    return maxSpeed;
  }

  public void setMaxSpeed(double maxSpeed) {
    this.maxSpeed = maxSpeed;
  }

  public Integer getMinCrewSize() {
    return minCrewSize;
  }

  public void setMinCrewSize(int minCrewSize) {
    this.minCrewSize = minCrewSize;
  }

  public Integer getMaxCrewSize() {
    return maxCrewSize;
  }

  public void setMaxCrewSize(int maxCrewSize) {
    this.maxCrewSize = maxCrewSize;
  }

  public Double getMinRating() {
    return minRating;
  }

  public void setMinRating(double minRating) {
    this.minRating = minRating;
  }

  public Double getMaxRating() {
    return maxRating;
  }

  public void setMaxRating(double maxRating) {
    this.maxRating = maxRating;
  }

  public String getOrder() {
    return order;
  }

  public void setOrder(String order) {
    this.order = order;
  }
}
