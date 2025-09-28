package com.tixthefox;

import com.jayway.jsonpath.JsonPath;
import com.tixthefox.DAO.ShipDAO;
import com.tixthefox.entity.Ship;
import com.tixthefox.utils.TestHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CreateTests {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ShipDAO shipDAO;

  @Test
  public void CreateTest_validBody() throws Exception {
    ResultActions resultActions = mockMvc.perform(post("/api/ships")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestHelper.NORMAL_JSON));

    resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", notNullValue()))
            .andExpect(jsonPath("$.name", equalTo("123456789")))
            .andExpect(jsonPath("$.planet", equalTo("Earth")))
            .andExpect(jsonPath("$.shipType", equalTo("MILITARY")))
//          не сравниваю prodDate, т.к. в БД хранится точность до дня, а не до мс
//          .andExpect(jsonPath("$.prodDate", equalTo(32998274577071L)))
            .andExpect(jsonPath("$.isUsed", equalTo(false)))
            .andExpect(jsonPath("$.speed", equalTo(0.8)))
            .andExpect(jsonPath("$.crewSize", equalTo(14)))
            .andExpect(jsonPath("$.rating", equalTo(12.8)));

    String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
    int newId = JsonPath.parse(contentAsString).read("$.id");

    Ship createdShip = shipDAO.findById(newId);
    assertNotNull(createdShip);
    assertEquals(createdShip.getName(), "123456789");
  }

  @Test
  public void CreateTest_validBody_withId() throws Exception {
    ResultActions resultActions = mockMvc.perform(post("/api/ships")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestHelper.NORMAL_JSON_WITH_ID));

    resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", notNullValue()))
            .andExpect(jsonPath("$.name", equalTo("123456789")))
            .andExpect(jsonPath("$.planet", equalTo("Earth")))
            .andExpect(jsonPath("$.shipType", equalTo("MILITARY")))
//          не сравниваю prodDate, т.к. в БД хранится точность до дня, а не до мс
//          .andExpect(jsonPath("$.prodDate", equalTo(32998274577071L)))
            .andExpect(jsonPath("$.isUsed", equalTo(false)))
            .andExpect(jsonPath("$.speed", equalTo(0.8)))
            .andExpect(jsonPath("$.crewSize", equalTo(14)))
            .andExpect(jsonPath("$.rating", equalTo(12.8)));

    String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
    int newId = JsonPath.parse(contentAsString).read("$.id");

    Ship createdShip = shipDAO.findById(newId);
    assertNotNull(createdShip);
    assertEquals(createdShip.getName(), "123456789");
  }

  @Test
  public void CreateTest_validBody_withRating() throws Exception {
    ResultActions resultActions = mockMvc.perform(post("/api/ships")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestHelper.NORMAL_JSON_WITH_RATING));

    resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", notNullValue()))
            .andExpect(jsonPath("$.name", equalTo("123456789")))
            .andExpect(jsonPath("$.planet", equalTo("Earth")))
            .andExpect(jsonPath("$.shipType", equalTo("MILITARY")))
//          не сравниваю prodDate, т.к. в БД хранится точность до дня, а не до мс
//          .andExpect(jsonPath("$.prodDate", equalTo(32998274577071L)))
            .andExpect(jsonPath("$.isUsed", equalTo(false)))
            .andExpect(jsonPath("$.speed", equalTo(0.8)))
            .andExpect(jsonPath("$.crewSize", equalTo(14)))
            .andExpect(jsonPath("$.rating", equalTo(12.8)));

    String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
    int newId = JsonPath.parse(contentAsString).read("$.id");

    Ship createdShip = shipDAO.findById(newId);
    assertNotNull(createdShip);
    assertEquals(createdShip.getName(), "123456789");
  }

  @Test
  public void CreateTest_noIsUsed() throws Exception {
    ResultActions resultActions = mockMvc.perform(post("/api/ships")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestHelper.NO_IS_USED));

    resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", notNullValue()))
            .andExpect(jsonPath("$.name", equalTo("123456789")))
            .andExpect(jsonPath("$.planet", equalTo("Earth")))
            .andExpect(jsonPath("$.shipType", equalTo("MILITARY")))
//          не сравниваю prodDate, т.к. в БД хранится точность до дня, а не до мс
//          .andExpect(jsonPath("$.prodDate", equalTo(32998274577071L)))
            .andExpect(jsonPath("$.isUsed", equalTo(false)))
            .andExpect(jsonPath("$.speed", equalTo(0.8)))
            .andExpect(jsonPath("$.crewSize", equalTo(14)))
            .andExpect(jsonPath("$.rating", equalTo(12.8)));

    String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
    int newId = JsonPath.parse(contentAsString).read("$.id");

    Ship createdShip = shipDAO.findById(newId);
    assertNotNull(createdShip);
    assertEquals(createdShip.getName(), "123456789");
  }

  @Test
  public void CreateTest_IsUsedTrue() throws Exception {
    ResultActions resultActions = mockMvc.perform(post("/api/ships")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestHelper.IS_USED_TRUE));

    resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", notNullValue()))
            .andExpect(jsonPath("$.name", equalTo("123456789")))
            .andExpect(jsonPath("$.planet", equalTo("Earth")))
            .andExpect(jsonPath("$.shipType", equalTo("MILITARY")))
//          не сравниваю prodDate, т.к. в БД хранится точность до дня, а не до мс
//          .andExpect(jsonPath("$.prodDate", equalTo(32998274577071L)))
            .andExpect(jsonPath("$.isUsed", equalTo(true)))
            .andExpect(jsonPath("$.speed", equalTo(0.8)))
            .andExpect(jsonPath("$.crewSize", equalTo(14)))
            .andExpect(jsonPath("$.rating", equalTo(6.4)));

    String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
    int newId = JsonPath.parse(contentAsString).read("$.id");

    Ship createdShip = shipDAO.findById(newId);
    assertNotNull(createdShip);
    assertEquals(createdShip.getName(), "123456789");
  }

  @Test
  public void CreateTest_emptyBody() throws Exception {
    ResultActions resultActions = mockMvc.perform(post("/api/ships")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{}"));

    resultActions.andExpect(status().isBadRequest());
  }

  @Test
  public void CreateTest_noSpeedBody() throws Exception {
    ResultActions resultActions = mockMvc.perform(post("/api/ships")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestHelper.NO_SPEED));

    resultActions.andExpect(status().isBadRequest());
  }

  @Test
  public void CreateTest_emptyNameBody() throws Exception {
    ResultActions resultActions = mockMvc.perform(post("/api/ships")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestHelper.EMPTY_NAME));

    resultActions.andExpect(status().isBadRequest());
  }

  @Test
  public void CreateTest_negativeProdDateBody() throws Exception {
    ResultActions resultActions = mockMvc.perform(post("/api/ships")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestHelper.NEGATIVE_PROD_DATE));

    resultActions.andExpect(status().isBadRequest());
  }

  @Test
  public void CreateTest_tooBigProdDateBody() throws Exception {
    ResultActions resultActions = mockMvc.perform(post("/api/ships")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestHelper.TOO_BIG_PROD_DATE));

    resultActions.andExpect(status().isBadRequest());
  }

  @Test
  public void CreateTest_tooBigCrewSize() throws Exception {
    ResultActions resultActions = mockMvc.perform(post("/api/ships")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestHelper.TOO_BIG_CREW_SIZE));

    resultActions.andExpect(status().isBadRequest());
  }

  @Test
  public void CreateTest_negativeCrewSize() throws Exception {
    ResultActions resultActions = mockMvc.perform(post("/api/ships")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestHelper.NEGATIVE_CREW_SIZE));

    resultActions.andExpect(status().isBadRequest());
  }

  @Test
  public void CreateTest_tooBigPlanetName() throws Exception {
    ResultActions resultActions = mockMvc.perform(post("/api/ships")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestHelper.TOO_BIG_PLANET_NAME));

    resultActions.andExpect(status().isBadRequest());
  }
}
