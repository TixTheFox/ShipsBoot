package com.tixthefox.controller;

import com.tixthefox.DTO.ShipFiltersRequestDTO;
import com.tixthefox.DTO.ShipFiltersWithPaginationDTO;
import com.tixthefox.DTO.ShipRequestDTO;
import com.tixthefox.DTO.ShipResponseDTO;
import com.tixthefox.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ShipRestController {
  ShipService shipService;

  @Autowired
  public ShipRestController(ShipService shipService) {
    this.shipService = shipService;
  }

  @GetMapping("/ships")
  public List<ShipResponseDTO> getAllShips(ShipFiltersWithPaginationDTO shipFilters) {
    return shipService.findAll(shipFilters);
  }

  @GetMapping("/ships/count")
  public Long getShipsCount(ShipFiltersRequestDTO shipFilters) {
    return shipService.count(shipFilters);
  }

  @GetMapping("/ships/{id}")
  public ShipResponseDTO getShipById(@PathVariable int id) {
    return shipService.findById(id);
  }

  @PostMapping("/ships")
  public ShipResponseDTO addShip(@RequestBody ShipRequestDTO shipRequestDTO) {
    return shipService.save(shipRequestDTO);
  }

  @PostMapping("/ships/{id}")
  public ShipResponseDTO updateShip(@PathVariable int id, @RequestBody ShipRequestDTO shipRequestDTO) {
    return shipService.update(id, shipRequestDTO);
  }

  @DeleteMapping("/ships/{id}")
  public void deleteShip(@PathVariable int id) {
    shipService.remove(id);
  }
}
