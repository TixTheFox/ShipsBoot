package com.tixthefox.DAO;

import com.tixthefox.entity.Ship;

import java.util.List;
import java.util.Map;

public interface ShipDAO {
  List<Ship> findAll(Map<String, String> requestParams);
  Integer count(Map<String, String> requestParams);
  Ship findById(int id);
  void save(Ship ship);
  Ship update(Ship ship);
  void remove(Ship ship);
}
