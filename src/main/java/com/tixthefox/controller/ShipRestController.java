package com.tixthefox.controller;

import com.tixthefox.DTO.ShipRequestDTO;
import com.tixthefox.DTO.ShipResponseDTO;
import com.tixthefox.entity.Ship;
import com.tixthefox.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ShipRestController {
  ShipService shipService;

  @Autowired
  public ShipRestController(ShipService shipService) {
    this.shipService = shipService;
  }

  @GetMapping("/ships")
  public List<ShipResponseDTO> getAllShips(@RequestParam Map<String, String> requestParams) {
    return shipService.findAll(requestParams);
  }

  @GetMapping("/ships/count")
  public Integer getShipsCount(@RequestParam Map<String, String> requestParams) {
    return shipService.count(requestParams);
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
