package com.tixthefox.service;

import com.tixthefox.DTO.ShipRequestDTO;
import com.tixthefox.exceptions.InvalidParametersException;
import com.tixthefox.utils.DataBaseConstraints;
import com.tixthefox.utils.LocalDateToLongConverter;

import java.time.LocalDate;

public class ShipRequestValidator {
  public static void validateShipForUpdate(ShipRequestDTO shipRequestDTO) {
    if (shipRequestDTO.getName() != null) {
      if (!nameIsValid(shipRequestDTO.getName())) {
        throw new InvalidParametersException("Invalid name: " + shipRequestDTO.getName());
      }
    }

    if (shipRequestDTO.getPlanet() != null) {
      if (!planetIsValid(shipRequestDTO.getPlanet())) {
        throw new InvalidParametersException("Invalid planet: " + shipRequestDTO.getName());
      }
    }

    if (shipRequestDTO.getProdDate() != null) {
      if (!prodDateIsValid(shipRequestDTO.getProdDate())) {
        throw new InvalidParametersException(
            String.format(
                "Invalid prod date: %s. Must be in [%s, %s]",
                shipRequestDTO.getProdDate(),
                DataBaseConstraints.DATE_MIN,
                DataBaseConstraints.DATE_MAX));
      }
    }

    if (shipRequestDTO.getSpeed() != null) {
      if (!speedIsValid(shipRequestDTO.getSpeed())) {
        throw new InvalidParametersException(
            String.format(
                "Invalid speed: %.2f. Must be in [%.2f, %.2f]",
                shipRequestDTO.getSpeed(),
                DataBaseConstraints.SPEED_MIN,
                DataBaseConstraints.SPEED_MAX));
      }
    }

    if (shipRequestDTO.getCrewSize() != null)
      if (!crewSizeIsValid(shipRequestDTO.getCrewSize())) {
        throw new InvalidParametersException(String.format(
                "Invalid crew size: %d. Must be in [%d, %d]",
                shipRequestDTO.getCrewSize(),
                DataBaseConstraints.CREW_SIZE_MIN,
                DataBaseConstraints.CREW_SIZE_MAX));
      }
  }

  public static void validateShipForSave(ShipRequestDTO shipRequestDTO) {
    if (shipRequestDTO.getName() == null || !nameIsValid(shipRequestDTO.getName())) {
      throw new InvalidParametersException("Invalid name: " + shipRequestDTO.getName());
    }

    if (shipRequestDTO.getPlanet() == null || !planetIsValid(shipRequestDTO.getPlanet())) {
      throw new InvalidParametersException("Invalid planet: " + shipRequestDTO.getName());
    }

    if (shipRequestDTO.getProdDate() == null || !prodDateIsValid(shipRequestDTO.getProdDate())) {
      throw new InvalidParametersException(String.format(
              "Invalid prod date: %s. Must be in [%s, %s]",
              shipRequestDTO.getProdDate(),
              DataBaseConstraints.DATE_MIN,
              DataBaseConstraints.DATE_MAX));
    }

    if (shipRequestDTO.getSpeed() == null || !speedIsValid(shipRequestDTO.getSpeed())) {
      throw new InvalidParametersException(String.format(
              "Invalid speed: %.2f. Must be in [%.2f, %.2f]",
              shipRequestDTO.getSpeed(),
              DataBaseConstraints.SPEED_MIN,
              DataBaseConstraints.SPEED_MAX));
    }

    if (shipRequestDTO.getCrewSize() == null || !crewSizeIsValid(shipRequestDTO.getCrewSize())) {
      throw new InvalidParametersException(String.format(
              "Invalid crew size: %d. Must be in [%d, %d]",
              shipRequestDTO.getCrewSize(),
              DataBaseConstraints.CREW_SIZE_MIN,
              DataBaseConstraints.CREW_SIZE_MAX));
    }
  }


  private static boolean nameIsValid(String name) {
    return !(name.isEmpty() || name.length() > DataBaseConstraints.NAME_LENGTH_MAX);
  }

  private static boolean planetIsValid(String planet) {
    return !(planet.isEmpty() || planet.length() > DataBaseConstraints.PLANET_LENGTH_MAX);
  }

  private static boolean prodDateIsValid(Long prodDate) {
    LocalDate shipDate = LocalDateToLongConverter.toLocalDate(prodDate);
    return shipDate.isAfter(DataBaseConstraints.DATE_MIN) && shipDate.isBefore(DataBaseConstraints.DATE_MAX);
  }

  private static boolean speedIsValid(Double speed) {
    return speed <= DataBaseConstraints.SPEED_MAX
            && speed >= DataBaseConstraints.SPEED_MIN;
  }

  private static boolean crewSizeIsValid(Integer crewSize) {
    return crewSize <= DataBaseConstraints.CREW_SIZE_MAX
            && crewSize >= DataBaseConstraints.CREW_SIZE_MIN;
  }
}
