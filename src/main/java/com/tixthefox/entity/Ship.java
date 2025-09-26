package com.tixthefox.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="ship")
public class Ship {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  private long id;

  @Column(name="name")
  private String name;

  @Column(name="planet")
  private String planet;

  @Column(name="shipType")
  @Enumerated(EnumType.STRING)
  private ShipType shipType;

  @Column(name="prodDate")
  private LocalDate prodDate;

  @Column(name="isUsed")
  private boolean isUsed;

  @Column(name="speed")
  private double speed;

  @Column(name="crewSize")
  private int crewSize;

  @Column(name="rating")
  private double rating;

  public Ship() {

  }

  public Ship(String name, String planet, ShipType shipType, LocalDate prodDate, boolean isUsed, double speed, int crewSize, double rating) {
    this.name = name;
    this.planet = planet;
    this.shipType = shipType;
    this.prodDate = prodDate;
    this.isUsed = isUsed;
    this.speed = speed;
    this.crewSize = crewSize;
    this.rating = rating;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
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

  public LocalDate getProdDate() {
    return prodDate;
  }

  public void setProdDate(LocalDate prodDate) {
    this.prodDate = prodDate;
  }

  public boolean getIsUsed() {
    return isUsed;
  }

  public void setIsUsed(boolean used) {
    isUsed = used;
  }

  public double getSpeed() {
    return speed;
  }

  public void setSpeed(double speed) {
    this.speed = speed;
  }

  public int getCrewSize() {
    return crewSize;
  }

  public void setCrewSize(int crewSize) {
    this.crewSize = crewSize;
  }

  public double getRating() {
    return rating;
  }

  public void setRating(double rating) {
    this.rating = rating;
  }

  @Override
  public String toString() {
    return "Ship{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", planet='" + planet + '\'' +
            ", shipType='" + shipType + '\'' +
            ", prodDate=" + prodDate +
            ", isUsed=" + isUsed +
            ", speed=" + speed +
            ", crewSize=" + crewSize +
            ", rating=" + rating +
            '}';
  }
}