package com.tixthefox.DAO;

import com.tixthefox.DTO.ShipFiltersRequestDTO;
import com.tixthefox.DTO.ShipFiltersWithPaginationDTO;
import com.tixthefox.exceptions.InvalidParametersException;
import com.tixthefox.exceptions.ParameterConversionException;
import com.tixthefox.entity.Ship;
import com.tixthefox.entity.ShipType;
import com.tixthefox.utils.LocalDateToLongConverter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
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
  public List<Ship> findAll(ShipFiltersWithPaginationDTO shipFilters) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Ship> query = cb.createQuery(Ship.class);
    Root<Ship> root = query.from(Ship.class);

    List<Predicate> predicates = formPredicatesFromFilters(shipFilters, cb, root);


    ShipOrder order;
    try {
      order = ShipOrder.valueOf(shipFilters.getOrder().toUpperCase());
    } catch (IllegalArgumentException | NullPointerException e) {
      order = ShipOrder.ID;
    }
    query.orderBy(cb.asc(root.get(order.getFieldName())));


    if (shipFilters.getPageSize() <= 0) {
      throw new InvalidParametersException("Invalid page size: " + shipFilters.getPageSize());
    }
    if (shipFilters.getPageNumber() < 0) {
      throw new InvalidParametersException("Invalid page number: " + shipFilters.getPageNumber());
    }

    int offset = shipFilters.getPageNumber() * shipFilters.getPageSize();
    int limit = shipFilters.getPageSize();

    query.select(root).where(cb.and(predicates.toArray(new Predicate[0])));

    return entityManager.createQuery(query)
            .setFirstResult(offset)
            .setMaxResults(limit)
            .getResultList();
  }

  @Override
  public Long count(ShipFiltersRequestDTO shipFilters) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Long> query = cb.createQuery(Long.class);
    Root<Ship> root = query.from(Ship.class);

    List<Predicate> predicates = formPredicatesFromFilters(shipFilters, cb, root);
    query.where(cb.and(predicates.toArray(new Predicate[0])));

    query.select(cb.count(root));

    return entityManager.createQuery(query).getSingleResult();
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

  // использует только фильтры, не включает пагинацию
  private List<Predicate> formPredicatesFromFilters(ShipFiltersRequestDTO shipFilters, CriteriaBuilder cb, Root<Ship> root) {

    List<Predicate> predicates = new ArrayList<>();


    // NAME
    if (shipFilters.getName() != null) {
      Predicate p = cb.like(root.get("name"), "%" + shipFilters.getName() + "%");
      predicates.add(p);
    }

    // PLANET
    if (shipFilters.getPlanet() != null) {
      Predicate p = cb.like(root.get("planet"), "%" + shipFilters.getPlanet() + "%");
      predicates.add(p);
    }

    // SHIP TYPE
    if (shipFilters.getShipType() != null) {
      ShipType shipType;
      try {
        shipType = ShipType.valueOf(shipFilters.getShipType().toUpperCase());
      } catch (IllegalArgumentException e) {
        throw new ParameterConversionException("Cannot convert shipType: " + shipFilters.getShipType());
      }
      Predicate p = cb.equal(root.get("shipType"), shipType);
      predicates.add(p);
    }

    // PROD DATE
    if (shipFilters.getAfter() != null){
      LocalDate afterLocalDate = LocalDateToLongConverter.toLocalDate(shipFilters.getAfter());

      Predicate p = cb.greaterThanOrEqualTo(root.get("prodDate"), afterLocalDate);
      predicates.add(p);
    }

    if (shipFilters.getBefore() != null){
      LocalDate beforeLocalDate = LocalDateToLongConverter.toLocalDate(shipFilters.getBefore());

      Predicate p = cb.lessThanOrEqualTo(root.get("prodDate"), beforeLocalDate);
      predicates.add(p);
    }

    // IS USED
    if (shipFilters.getIsUsed() != null){
      Predicate p = cb.equal(root.get("isUsed"), shipFilters.getIsUsed());
      predicates.add(p);
    }

    // SPEED
    if (shipFilters.getMinSpeed() != null){
      Predicate p = cb.greaterThanOrEqualTo(root.get("speed"), shipFilters.getMinSpeed());
      predicates.add(p);
    }

    if (shipFilters.getMaxSpeed() != null){
      Predicate p = cb.lessThanOrEqualTo(root.get("speed"), shipFilters.getMaxSpeed());
      predicates.add(p);
    }

    // CREW SIZE
    if (shipFilters.getMinCrewSize() != null){
      Predicate p = cb.greaterThanOrEqualTo(root.get("crewSize"), shipFilters.getMinCrewSize());
      predicates.add(p);
    }

    if (shipFilters.getMaxCrewSize() != null){
      Predicate p = cb.lessThanOrEqualTo(root.get("crewSize"), shipFilters.getMaxCrewSize());
      predicates.add(p);
    }


    // RATING
    if (shipFilters.getMinRating() != null){
      Predicate p = cb.greaterThanOrEqualTo(root.get("rating"), shipFilters.getMinRating());
      predicates.add(p);
    }

    if (shipFilters.getMaxRating() != null){
      Predicate p = cb.lessThanOrEqualTo(root.get("rating"), shipFilters.getMaxRating());
      predicates.add(p);
    }

    return predicates;
  }
}
