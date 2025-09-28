package com.tixthefox.DAO;

import com.tixthefox.DTO.ShipFiltersRequestDTO;
import com.tixthefox.DTO.ShipFiltersWithPaginationDTO;
import com.tixthefox.entity.Ship;

import java.util.List;
import java.util.Map;

public interface ShipDAO {
  List<Ship> findAll(ShipFiltersWithPaginationDTO shipFilters);
  Long count(ShipFiltersRequestDTO shipFilters);
  Ship findById(int id);
  void save(Ship ship);
  Ship update(Ship ship);
  void remove(Ship ship);
}
