package com.tixthefox.service;

import com.tixthefox.DAO.ShipDAO;
import com.tixthefox.DTO.ShipFiltersRequestDTO;
import com.tixthefox.DTO.ShipFiltersWithPaginationDTO;
import com.tixthefox.DTO.ShipRequestDTO;
import com.tixthefox.DTO.ShipResponseDTO;
import com.tixthefox.exceptions.InvalidIdException;
import com.tixthefox.exceptions.InvalidParametersException;
import com.tixthefox.entity.Ship;
import com.tixthefox.exceptions.ShipNotFoundException;
import com.tixthefox.utils.DataBaseConstraints;
import com.tixthefox.utils.LocalDateToLongConverter;
import com.tixthefox.utils.ShipEntityDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

@Service
public class ShipServiceImpl implements ShipService {
  private ShipDAO shipDAO;

  @Autowired
  public ShipServiceImpl(ShipDAO shipDAO) {
    this.shipDAO = shipDAO;
  }

  @Override
  public List<ShipResponseDTO> findAll(ShipFiltersWithPaginationDTO shipFilters) {
    List<Ship> shipsAsEntities = shipDAO.findAll(shipFilters);
    return shipsAsEntities.stream().map(ShipEntityDTOConverter::toResponseDTO).toList();
  }

  @Override
  public Long count(ShipFiltersRequestDTO shipFilters) {
    return shipDAO.count(shipFilters);
  }

  @Override
  public ShipResponseDTO findById(int id) {
    if (id <= 0) {
      throw new InvalidIdException("Invalid id: " + id);
    }

    Ship foundShip = shipDAO.findById(id);
    if (foundShip == null) {
      throw new ShipNotFoundException("Ship with id " + id + " not found", HttpStatus.NOT_FOUND);
    }

    return ShipEntityDTOConverter.toResponseDTO(foundShip);
  }

  @Override
  public ShipResponseDTO save(ShipRequestDTO shipRequestDTO) {
    ShipRequestValidator.validateShipForSave(shipRequestDTO);

    if (shipRequestDTO.getIsUsed() == null) {
      shipRequestDTO.setIsUsed(false);
    }

    Ship newShip = ShipEntityDTOConverter.toEntity(shipRequestDTO);
    newShip.setRating(countRating(newShip));
    shipDAO.save(newShip);

    return ShipEntityDTOConverter.toResponseDTO(newShip);
  }

  @Override
  public ShipResponseDTO update(int id, ShipRequestDTO shipRequestDTO) {
    if (id <= 0) {
      throw new InvalidIdException("Invalid id: " + id);
    }

    Ship foundShip = shipDAO.findById(id);
    if (foundShip == null) {
      throw new ShipNotFoundException("Ship with id " + id + " not found", HttpStatus.NOT_FOUND);
    }

    ShipRequestValidator.validateShipForUpdate(shipRequestDTO);

    if (shipRequestDTO.getName() != null) {
      foundShip.setName(shipRequestDTO.getName());
    }

    if (shipRequestDTO.getPlanet() != null) {
      foundShip.setPlanet(shipRequestDTO.getPlanet());
    }

    if (shipRequestDTO.getShipType() != null) {
      foundShip.setShipType(shipRequestDTO.getShipType());
    }

    if (shipRequestDTO.getIsUsed() != null) {
      foundShip.setIsUsed(shipRequestDTO.getIsUsed());
    }

    if (shipRequestDTO.getProdDate() != null) {
      foundShip.setProdDate(LocalDateToLongConverter.toLocalDate(shipRequestDTO.getProdDate()));
    }

    if (shipRequestDTO.getSpeed() != null) {
      foundShip.setSpeed(shipRequestDTO.getSpeed());
    }

    if (shipRequestDTO.getCrewSize() != null) {
      foundShip.setCrewSize(shipRequestDTO.getCrewSize());
    }

    foundShip.setRating(countRating(foundShip));

    return ShipEntityDTOConverter.toResponseDTO(shipDAO.update(foundShip));
  }

  @Override
  public void remove(int id) {
    if (id <= 0) {
      throw new InvalidParametersException("Invalid id: " + id);
    }

    Ship shipToDelete = shipDAO.findById(id);
    if (shipToDelete == null) {
      throw new ShipNotFoundException("Ship with id " + id + " not found", HttpStatus.NOT_FOUND);
    }
    shipDAO.remove(shipToDelete);
  }



  private double countRating(Ship ship) {
    double usedCoefficient = ship.getIsUsed() ? 0.5 : 1;
    double rating = (usedCoefficient * ship.getSpeed() * 80) / (3019 - ship.getProdDate().getYear() + 1);
    rating = Math.round(rating * 100) / 100.0;
    return rating;
  }
}
