package com.tixthefox;

import com.tixthefox.DAO.ShipDAO;
import com.tixthefox.entity.Ship;
import com.tixthefox.utils.TestHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;


import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UpdateTests {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ShipDAO shipDAO;

  @Test
  public void UpdateTest_zeroId() throws Exception {
    mockMvc.perform(post("/api/ships/0")
                    .content(TestHelper.NORMAL_JSON)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
  }

  @Test
  public void UpdateTest_notExistingShip() throws Exception {
    mockMvc.perform(post("/api/ships/9278")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestHelper.NORMAL_JSON))
            .andExpect(status().isNotFound());
  }

  @Test
  public void UpdateTest_idIsNotANumber() throws Exception {
    mockMvc.perform(post("/api/ships/12sa")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
  }

  @Test
  public void UpdateTest_emptyName() throws Exception {
    mockMvc.perform(post("/api/ships/1")
                    .content(TestHelper.EMPTY_NAME)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
  }

  @Test
  public void UpdateTest_negativeProdDate() throws Exception {
    mockMvc.perform(post("/api/ships/1")
                    .content(TestHelper.NEGATIVE_PROD_DATE)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
  }

  @Test
  public void UpdateTest_tooBigProdDate() throws Exception {
    mockMvc.perform(post("/api/ships/1")
                    .content(TestHelper.TOO_BIG_PROD_DATE)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
  }

  @Test
  public void UpdateTest_negativeCrewSize() throws Exception {
    mockMvc.perform(post("/api/ships/1")
                    .content(TestHelper.NEGATIVE_CREW_SIZE)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
  }

  @Test
  public void UpdateTest_validBody() throws Exception {
    int id = 4;
    ResultActions resultActions = mockMvc.perform(post("/api/ships/" + id)
            .content(TestHelper.NORMAL_JSON)
            .contentType(MediaType.APPLICATION_JSON));

    resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", notNullValue()))
            .andExpect(jsonPath("$.name", equalTo("123456789")))
            .andExpect(jsonPath("$.planet", equalTo("Earth")))
            .andExpect(jsonPath("$.shipType", equalTo("MILITARY")))
            .andExpect(jsonPath("$.isUsed", equalTo(false)))
            .andExpect(jsonPath("$.speed", equalTo(0.8)))
            .andExpect(jsonPath("$.crewSize", equalTo(14)))
            .andExpect(jsonPath("$.rating", equalTo(12.8)));

    Ship createdShip = shipDAO.findById(id);
    assertNotNull(createdShip);
    assertEquals(createdShip.getName(), "123456789");
    assertEquals(createdShip.getPlanet(), "Earth");
    assertEquals(createdShip.getShipType().name(), "MILITARY");
    assertFalse(createdShip.getIsUsed());
    assertEquals(createdShip.getSpeed(), 0.8);
    assertEquals(createdShip.getCrewSize(), 14);
    assertEquals(createdShip.getRating(), 12.8);
  }

  @Test
  public void UpdateTest_validBody_WithId() throws Exception {
    int id = 3;
    ResultActions resultActions = mockMvc.perform(post("/api/ships/" + id)
            .content(TestHelper.NORMAL_JSON_WITH_ID)
            .contentType(MediaType.APPLICATION_JSON));

    resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", notNullValue()))
            .andExpect(jsonPath("$.name", equalTo("123456789")))
            .andExpect(jsonPath("$.planet", equalTo("Earth")))
            .andExpect(jsonPath("$.shipType", equalTo("MILITARY")))
            .andExpect(jsonPath("$.isUsed", equalTo(false)))
            .andExpect(jsonPath("$.speed", equalTo(0.8)))
            .andExpect(jsonPath("$.crewSize", equalTo(14)))
            .andExpect(jsonPath("$.rating", equalTo(12.8)));

    Ship createdShip = shipDAO.findById(id);
    assertNotNull(createdShip);
    assertEquals(createdShip.getName(), "123456789");
    assertEquals(createdShip.getPlanet(), "Earth");
    assertEquals(createdShip.getShipType().name(), "MILITARY");
    assertFalse(createdShip.getIsUsed());
    assertEquals(createdShip.getSpeed(), 0.8);
    assertEquals(createdShip.getCrewSize(), 14);
    assertEquals(createdShip.getRating(), 12.8);
  }

  @Test
  public void UpdateTest_validBody_WithRating() throws Exception {
    int id = 2;
    ResultActions resultActions = mockMvc.perform(post("/api/ships/" + id)
            .content(TestHelper.NORMAL_JSON_WITH_RATING)
            .contentType(MediaType.APPLICATION_JSON));

    resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", notNullValue()))
            .andExpect(jsonPath("$.name", equalTo("123456789")))
            .andExpect(jsonPath("$.planet", equalTo("Earth")))
            .andExpect(jsonPath("$.shipType", equalTo("MILITARY")))
            .andExpect(jsonPath("$.isUsed", equalTo(false)))
            .andExpect(jsonPath("$.speed", equalTo(0.8)))
            .andExpect(jsonPath("$.crewSize", equalTo(14)))
            .andExpect(jsonPath("$.rating", equalTo(12.8)));

    Ship createdShip = shipDAO.findById(id);
    assertNotNull(createdShip);
    assertEquals(createdShip.getName(), "123456789");
    assertEquals(createdShip.getPlanet(), "Earth");
    assertEquals(createdShip.getShipType().name(), "MILITARY");
    assertFalse(createdShip.getIsUsed());
    assertEquals(createdShip.getSpeed(), 0.8);
    assertEquals(createdShip.getCrewSize(), 14);
    assertEquals(createdShip.getRating(), 12.8);
  }


  @Test
  public void UpdateTest_emptyBody() throws Exception {
    ResultActions resultActions = mockMvc.perform(post("/api/ships/1")
            .content("{}")
            .contentType(MediaType.APPLICATION_JSON));

    resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", notNullValue()))
            .andExpect(jsonPath("$.name", equalTo("Orion III")))
            .andExpect(jsonPath("$.planet", equalTo("Mars")))
            .andExpect(jsonPath("$.shipType", equalTo("MERCHANT")))
            .andExpect(jsonPath("$.isUsed", equalTo(true)))
            .andExpect(jsonPath("$.speed", equalTo(0.82)))
            .andExpect(jsonPath("$.crewSize", equalTo(617)))
            .andExpect(jsonPath("$.rating", equalTo(1.31)));

    Ship createdShip = shipDAO.findById(1);
    assertNotNull(createdShip);
    assertEquals(createdShip.getName(), "Orion III");
    assertEquals(createdShip.getPlanet(), "Mars");
    assertEquals(createdShip.getShipType().name(), "MERCHANT");
    assertTrue(createdShip.getIsUsed());
    assertEquals(createdShip.getSpeed(), 0.82);
    assertEquals(createdShip.getCrewSize(), 617);
    assertEquals(createdShip.getRating(), 1.31);
  }
}
