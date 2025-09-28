package com.tixthefox.service;

import com.tixthefox.DTO.ShipFiltersRequestDTO;
import com.tixthefox.DTO.ShipFiltersWithPaginationDTO;
import com.tixthefox.DTO.ShipRequestDTO;
import com.tixthefox.DTO.ShipResponseDTO;
import com.tixthefox.entity.Ship;

import java.util.List;
import java.util.Map;

public interface ShipService {
  List<ShipResponseDTO> findAll(ShipFiltersWithPaginationDTO shipFilters);
  Long count(ShipFiltersRequestDTO shipFilters);
  ShipResponseDTO findById(int id);
  ShipResponseDTO save(ShipRequestDTO shipRequestDTO);
  ShipResponseDTO update(int id, ShipRequestDTO shipRequestDTO);
  void remove(int id);
}
