package com.tixthefox.DAO;

import com.tixthefox.exceptions.ParameterConversionException;
import com.tixthefox.entity.Ship;
import com.tixthefox.entity.ShipType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ShipDAOImpl implements ShipDAO {

  private EntityManager entityManager;

  @Autowired
  public ShipDAOImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public List<Ship> findAll(Map<String, String> requestParams) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Ship> query = cb.createQuery(Ship.class);
    Root<Ship> root = query.from(Ship.class);

    List<Predicate> predicates = new ArrayList<>();

    if (requestParams.containsKey("name")) {
      Predicate p = cb.like(root.get("name"), "%" + requestParams.get("name") + "%");
      predicates.add(p);
    }

    if (requestParams.containsKey("planet")){
      Predicate p = cb.like(root.get("planet"), "%" + requestParams.get("planet") + "%");
      predicates.add(p);
    }

    if (requestParams.containsKey("shipType")){
      ShipType shipTypeParam = ShipType.valueOf(requestParams.get("shipType").toUpperCase());
      Predicate p = cb.equal(root.get("shipType"), shipTypeParam.name());
      predicates.add(p);
    }

    if (requestParams.containsKey("after")){
      long dateParam = 0;
      try{
        dateParam = Long.parseLong(requestParams.get("after"));
      } catch(NumberFormatException exc) {
        throw new ParameterConversionException("Cannot convert date (long) - " + requestParams.get("after"));
      }
      Predicate p =
              cb.greaterThanOrEqualTo(
                      root.get("prodDate"), Instant.ofEpochMilli(dateParam).atZone(ZoneId.systemDefault()).toLocalDate());
      predicates.add(p);
    }
    if (requestParams.containsKey("before")){
      long dateParam = 0;
      try{
        dateParam = Long.parseLong(requestParams.get("before"));
      } catch(NumberFormatException exc) {
        throw new ParameterConversionException("Cannot convert date (long) - " + requestParams.get("before"));
      }

      Predicate p =
          cb.lessThanOrEqualTo(
              root.get("prodDate"), Instant.ofEpochMilli(dateParam).atZone(ZoneId.systemDefault()).toLocalDate());
      predicates.add(p);
    }

    if (requestParams.containsKey("isUsed")){
      boolean isUsedParam = Boolean.parseBoolean(requestParams.get("isUsed"));
      Predicate p = cb.equal(root.get("isUsed"), isUsedParam);
      predicates.add(p);
    }

    if (requestParams.containsKey("minSpeed")){
      double speedParam;
      try{
        speedParam = Double.parseDouble(requestParams.get("minSpeed"));
      } catch(NumberFormatException exc) {
        throw new ParameterConversionException("Cannot convert speed (double) - " + requestParams.get("minSpeed"));
      }

      Predicate p = cb.greaterThanOrEqualTo(root.get("speed"), speedParam);
      predicates.add(p);
    }
    if (requestParams.containsKey("maxSpeed")){
      double speedParam;
      try{
        speedParam = Double.parseDouble(requestParams.get("maxSpeed"));
      } catch(NumberFormatException exc) {
        throw new ParameterConversionException("Cannot convert speed (double) - " + requestParams.get("maxSpeed"));
      }

      Predicate p = cb.lessThanOrEqualTo(root.get("speed"), speedParam);
      predicates.add(p);
    }

    if (requestParams.containsKey("minCrewSize")){
      double crewParam;
      try{
        crewParam = Integer.parseInt(requestParams.get("minCrewSize"));
      } catch(NumberFormatException exc) {
        throw new ParameterConversionException("Cannot convert crewSize (int) - " + requestParams.get("minCrewSize"));
      }

      Predicate p = cb.greaterThanOrEqualTo(root.get("crewSize"), crewParam);
      predicates.add(p);
    }
    if (requestParams.containsKey("maxCrewSize")){
      double crewParam;
      try{
        crewParam = Integer.parseInt(requestParams.get("maxCrewSize"));
      } catch(NumberFormatException exc) {
        throw new ParameterConversionException("Cannot convert crewSize (int) - " + requestParams.get("maxCrewSize"));
      }

      Predicate p = cb.lessThanOrEqualTo(root.get("crewSize"), crewParam);
      predicates.add(p);
    }

    if (requestParams.containsKey("minRating")){
      double ratingParam;
      try{
        ratingParam = Double.parseDouble(requestParams.get("minRating"));
      } catch(NumberFormatException exc) {
        throw new ParameterConversionException("Cannot convert rating (double) - " + requestParams.get("maxSpeed"));
      }
      Predicate p = cb.greaterThanOrEqualTo(root.get("rating"), ratingParam);
      predicates.add(p);
    }
    if (requestParams.containsKey("maxRating")){
      double ratingParam;
      try{
        ratingParam = Double.parseDouble(requestParams.get("maxRating"));
      } catch(NumberFormatException exc) {
        throw new ParameterConversionException("Cannot convert rating (double) - " + requestParams.get("maxSpeed"));
      }
      Predicate p = cb.lessThanOrEqualTo(root.get("rating"), ratingParam);
      predicates.add(p);
    }

    if (requestParams.containsKey("order")){
      ShipOrder order;
      try {
        order = ShipOrder.valueOf(requestParams.get("order").toUpperCase());
      } catch (IllegalArgumentException e) {
        order = ShipOrder.ID;
      }
      query.orderBy(cb.asc(root.get(order.getFieldName())));
    }

    query.select(root).where(cb.and(predicates.toArray(new Predicate[0])));

    return entityManager.createQuery(query).getResultList();
  }

  @Override
  public Integer count(Map<String, String> requestParams) {
    return findAll(requestParams).size();
  }

  @Override
  public Ship findById(int id) {
    return entityManager.find(Ship.class, id);
  }

  @Override
  @Transactional
  public void save(Ship ship) {
    entityManager.persist(ship);
  }

  @Override
  @Transactional
  public Ship update(Ship ship) {
    return entityManager.merge(ship);
  }

  @Override
  @Transactional
  public void remove(Ship ship) {
    entityManager.remove(ship);
  }
}
