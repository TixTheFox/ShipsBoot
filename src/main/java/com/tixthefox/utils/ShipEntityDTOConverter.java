package com.tixthefox.utils;

import com.tixthefox.DTO.ShipRequestDTO;
import com.tixthefox.DTO.ShipResponseDTO;
import com.tixthefox.entity.Ship;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class ShipEntityDTOConverter {
  public static Ship toEntity(ShipRequestDTO shipRequestDTO) {
    Ship entity = new Ship();
    entity.setName(shipRequestDTO.getName());
    entity.setPlanet(shipRequestDTO.getPlanet());
    entity.setShipType(shipRequestDTO.getShipType());

    LocalDate ProdDate = Instant.ofEpochMilli(shipRequestDTO.getProdDate()).atZone(ZoneId.systemDefault()).toLocalDate();
    entity.setProdDate(ProdDate);

    entity.setIsUsed(shipRequestDTO.getIsUsed());
    entity.setSpeed(shipRequestDTO.getSpeed());
    entity.setCrewSize(shipRequestDTO.getCrewSize());
    return entity;
  }

  public static Ship toEntity(ShipResponseDTO shipResponseDTO) {
    Ship entity = new Ship();
    entity.setId(shipResponseDTO.getId());
    entity.setName(shipResponseDTO.getName());
    entity.setPlanet(shipResponseDTO.getPlanet());
    entity.setShipType(shipResponseDTO.getShipType());

    LocalDate ProdDate = Instant.ofEpochMilli(shipResponseDTO.getProdDate()).atZone(ZoneId.systemDefault()).toLocalDate();
    entity.setProdDate(ProdDate);

    entity.setIsUsed(shipResponseDTO.getIsUsed());
    entity.setSpeed(shipResponseDTO.getSpeed());
    entity.setCrewSize(shipResponseDTO.getCrewSize());
    entity.setRating(shipResponseDTO.getRating());
    return entity;
  }

  public static ShipRequestDTO toRequestDTO(Ship ship) {
    ShipRequestDTO requestDTO = new ShipRequestDTO();
    requestDTO.setName(ship.getName());
    requestDTO.setPlanet(ship.getPlanet());
    requestDTO.setShipType(ship.getShipType());

    long prodDate = ship.getProdDate().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    requestDTO.setProdDate(prodDate);

    requestDTO.setIsUsed(ship.getIsUsed());
    requestDTO.setSpeed(ship.getSpeed());
    requestDTO.setCrewSize(ship.getCrewSize());
    return requestDTO;
  }

  public static ShipResponseDTO toResponseDTO(Ship ship) {
    ShipResponseDTO responseDTO = new ShipResponseDTO();
    responseDTO.setId(ship.getId());
    responseDTO.setName(ship.getName());
    responseDTO.setPlanet(ship.getPlanet());
    responseDTO.setShipType(ship.getShipType());

    long prodDate = ship.getProdDate().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    responseDTO.setProdDate(prodDate);

    responseDTO.setIsUsed(ship.getIsUsed());
    responseDTO.setSpeed(ship.getSpeed());
    responseDTO.setCrewSize(ship.getCrewSize());
    responseDTO.setRating(ship.getRating());
    return responseDTO;
  }
}
