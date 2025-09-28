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
    if (!isShipValidForSave(shipRequestDTO)) {
      throw new InvalidParametersException("Invalid ship for save");
    }

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

    if (shipRequestDTO.getName() != null) {
      if (shipRequestDTO.getName().isEmpty()) {
        throw new InvalidParametersException("Name cannot be empty");
      }
      foundShip.setName(shipRequestDTO.getName());
    }

    if (shipRequestDTO.getPlanet() != null) {
      if (shipRequestDTO.getPlanet().isEmpty()) {
        throw new InvalidParametersException("Planet cannot be empty");
      }
      foundShip.setPlanet(shipRequestDTO.getPlanet());
    }

    if (shipRequestDTO.getShipType() != null) {
      foundShip.setShipType(shipRequestDTO.getShipType());
    }

    if (shipRequestDTO.getIsUsed() != null) {
      foundShip.setIsUsed(shipRequestDTO.getIsUsed());
    }

    if (shipRequestDTO.getProdDate() != null) {
      LocalDate shipDate =
          Instant.ofEpochMilli(shipRequestDTO.getProdDate())
              .atZone(ZoneId.systemDefault())
              .toLocalDate();
      if (!(shipDate.isAfter(DataBaseConstraints.DATE_MIN)
          && shipDate.isBefore(DataBaseConstraints.DATE_MAX))) {
        throw new InvalidParametersException(
            String.format(
                "Invalid prod date: %s. Must be in [%s, %s]",
                shipRequestDTO.getProdDate(),
                DataBaseConstraints.DATE_MIN,
                DataBaseConstraints.DATE_MAX));
      }

      foundShip.setProdDate(shipDate);
    }

    if (shipRequestDTO.getSpeed() != null) {
      if (shipRequestDTO.getSpeed() > DataBaseConstraints.SPEED_MAX
          || shipRequestDTO.getSpeed() < DataBaseConstraints.SPEED_MIN) {
        throw new InvalidParametersException(
            String.format(
                "Invalid speed: %.2f. Must be in [%.2f, %.2f]",
                shipRequestDTO.getSpeed(),
                DataBaseConstraints.SPEED_MIN,
                DataBaseConstraints.SPEED_MAX));
      }
      foundShip.setSpeed(shipRequestDTO.getSpeed());
    }

    if (shipRequestDTO.getCrewSize() != null) {
      if (shipRequestDTO.getCrewSize() > DataBaseConstraints.CREW_SIZE_MAX
          || shipRequestDTO.getCrewSize() < DataBaseConstraints.CREW_SIZE_MIN) {
        throw new InvalidParametersException(
            String.format(
                "Invalid crew size: %d. Must be in [%d, %d]",
                shipRequestDTO.getCrewSize(),
                DataBaseConstraints.CREW_SIZE_MIN,
                DataBaseConstraints.CREW_SIZE_MAX));
      }
      foundShip.setCrewSize(shipRequestDTO.getCrewSize());
    }

    foundShip.setRating(countRating(foundShip));

    return ShipEntityDTOConverter.toResponseDTO(shipDAO.update(foundShip));
  }

  private boolean isShipValidForSave(ShipRequestDTO shipRequestDTO) {
    if ((shipRequestDTO.getName() == null)
        || (shipRequestDTO.getPlanet() == null)
        || (shipRequestDTO.getShipType() == null)
        || (shipRequestDTO.getProdDate() == null)
        || (shipRequestDTO.getSpeed() == null)
        || (shipRequestDTO.getCrewSize() == null)) {
      return false;
    }

    if (shipRequestDTO.getName().isEmpty()
        || shipRequestDTO.getName().length() > DataBaseConstraints.NAME_LENGTH_MAX) {
      return false;
    }

    if (shipRequestDTO.getPlanet().isEmpty()
        || shipRequestDTO.getPlanet().length() > DataBaseConstraints.PLANET_LENGTH_MAX) {
      return false;
    }

    LocalDate shipDate =
        Instant.ofEpochMilli(shipRequestDTO.getProdDate())
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
    if (!(shipDate.isAfter(DataBaseConstraints.DATE_MIN)
        && shipDate.isBefore(DataBaseConstraints.DATE_MAX))) {
      return false;
    }

    if (shipRequestDTO.getSpeed() > DataBaseConstraints.SPEED_MAX
        || shipRequestDTO.getSpeed() < DataBaseConstraints.SPEED_MIN) {
      return false;
    }

    if (shipRequestDTO.getCrewSize() > DataBaseConstraints.CREW_SIZE_MAX
        || shipRequestDTO.getCrewSize() < DataBaseConstraints.CREW_SIZE_MIN) {
      return false;
    }

    return true;
  }

  private double countRating(Ship ship) {
    double usedCoefficient = ship.getIsUsed() ? 0.5 : 1;
    double rating = (usedCoefficient * ship.getSpeed() * 80) / (3019 - ship.getProdDate().getYear() + 1);
    rating = Math.round(rating * 100) / 100.0;
    return rating;
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
}
