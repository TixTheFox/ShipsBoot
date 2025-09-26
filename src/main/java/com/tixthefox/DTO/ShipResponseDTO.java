package com.tixthefox.DTO;

import com.tixthefox.entity.ShipType;

public class ShipResponseDTO {
  private Long id;
  private String name;
  private String planet;
  private ShipType shipType;
  private Long prodDate;
  private Boolean isUsed;
  private Double speed;
  private Integer crewSize;
  private Double rating;

  public ShipResponseDTO() {
  }

  public ShipResponseDTO(Long id, String name, String planet, ShipType shipType, Long prodDate, Boolean isUsed, Double speed, Integer crewSize, Double rating) {
    this.id = id;
    this.name = name;
    this.planet = planet;
    this.shipType = shipType;
    this.prodDate = prodDate;
    this.isUsed = isUsed;
    this.speed = speed;
    this.crewSize = crewSize;
    this.rating = rating;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public ShipType getShipType() {
    return shipType;
  }

  public void setShipType(ShipType shipType) {
    this.shipType = shipType;
  }

  public Long getProdDate() {
    return prodDate;
  }

  public void setProdDate(Long prodDate) {
    this.prodDate = prodDate;
  }

  public Boolean getIsUsed() {
    return isUsed;
  }

  public void setIsUsed(Boolean used) {
    isUsed = used;
  }

  public Double getSpeed() {
    return speed;
  }

  public void setSpeed(Double speed) {
    this.speed = speed;
  }

  public Integer getCrewSize() {
    return crewSize;
  }

  public void setCrewSize(Integer crewSize) {
    this.crewSize = crewSize;
  }

  public Double getRating() {
    return rating;
  }

  public void setRating(Double rating) {
    this.rating = rating;
  }
}
