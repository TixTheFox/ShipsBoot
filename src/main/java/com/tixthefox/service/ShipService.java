package com.tixthefox.service;

import com.tixthefox.DTO.ShipRequestDTO;
import com.tixthefox.DTO.ShipResponseDTO;
import com.tixthefox.entity.Ship;

import java.util.List;
import java.util.Map;

public interface ShipService {
  List<ShipResponseDTO> findAll(Map<String, String> requestParams);
  Integer count(Map<String, String> requestParams);
  ShipResponseDTO findById(int id);
  ShipResponseDTO save(ShipRequestDTO shipRequestDTO);
  ShipResponseDTO update(int id, ShipRequestDTO shipRequestDTO);
  void remove(int id);
}
