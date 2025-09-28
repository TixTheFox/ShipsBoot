package com.tixthefox;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class CountTests {

  @Autowired
  private MockMvc mockMvc;

  // Valid tests

  @Test
  public void GetShipsCountTest_noParam() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships/count"));

    resultActions
            .andExpect(status().isOk())
            .andExpect(content().string("5"));
  }

  @Test
  public void GetShipsCountTest_nameParam() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships/count")
                    .param("name", "edalus"));

    resultActions
            .andExpect(status().isOk())
            .andExpect(content().string("2"));
  }

  @Test
  public void GetShipsCountTest_planetParam() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships/count")
                    .param("planet", "u"));

    resultActions
            .andExpect(status().isOk())
            .andExpect(content().string("3"));
  }

  @Test
  public void GetShipsCountTest_ShipTypeParam() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships/count")
                    .param("shipType", "MERCHANT"));

    resultActions
            .andExpect(status().isOk())
            .andExpect(content().string("2"));
  }

  @Test
  public void GetShipsCountTest_ShipTypeParam_LowerCase() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships/count")
                    .param("shipType", "merchant"));

    resultActions
            .andExpect(status().isOk())
            .andExpect(content().string("2"));
  }

  @Test
  public void GetShipsCountTest_AfterParam() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships/count")
                    .param("after", "32377449600000"));

    resultActions
            .andExpect(status().isOk())
            .andExpect(content().string("3"));
  }

  @Test
  public void GetShipsCountTest_BeforeParam() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships/count")
                    .param("before", "32377449600000"));

    resultActions
            .andExpect(status().isOk())
            .andExpect(content().string("2"));
  }

  @Test
  public void GetShipsCountTest_isUsedParam() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships/count")
                    .param("isUsed", "true"));

    resultActions
            .andExpect(status().isOk())
            .andExpect(content().string("3"));
  }

  @Test
  public void GetShipsCountTest_minSpeedParam() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships/count")
                    .param("minSpeed", "0.82"));

    resultActions
            .andExpect(status().isOk())
            .andExpect(content().string("2"));
  }

  @Test
  public void GetShipsCountTest_maxSpeedParam() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships/count")
                    .param("maxSpeed", "0.82"));

    resultActions
            .andExpect(status().isOk())
            .andExpect(content().string("4"));
  }

  @Test
  public void GetShipsCountTest_minCrewParam() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships/count")
                    .param("minCrewSize", "617"));

    resultActions
            .andExpect(status().isOk())
            .andExpect(content().string("4"));
  }

  @Test
  public void GetShipsCountTest_maxCrewParam() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships/count")
                    .param("maxCrewSize", "617"));

    resultActions
            .andExpect(status().isOk())
            .andExpect(content().string("2"));
  }

  @Test
  public void GetShipsCountTest_minRatingParam() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships/count")
                    .param("minRating", "1.31"));

    resultActions
            .andExpect(status().isOk())
            .andExpect(content().string("4"));
  }

  @Test
  public void GetShipsCountTest_maxRatingParam() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships/count")
                    .param("maxRating", "1.31"));

    resultActions
            .andExpect(status().isOk())
            .andExpect(content().string("2"));
  }


  @Test
  public void GetShipsCountTest_complexParams_after_minCrewSize() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships/count")
                    .param("after", "32377449600000")
                    .param("minCrewSize", "617")
            );

    resultActions
            .andExpect(status().isOk())
            .andExpect(content().string("2"));
  }

  @Test
  public void GetShipsCountTest_complexParams_minSpeed_maxSpeed_shipType() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships/count")
                    .param("minSpeed", "0.6")
                    .param("maxSpeed", "0.85")
                    .param("shipType", "MERCHANT")
            );

    resultActions
            .andExpect(status().isOk())
            .andExpect(content().string("1"));
  }

  //
  // Invalid inputs
  //

  @Test
  public void GetShipsCountTest_doubleConversionError_minRating() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships/count")
                    .param("minRating", "12lsa"));

    resultActions
            .andExpect(status().isBadRequest());
  }

  @Test
  public void GetShipsCountTest_doubleConversionError_maxRating() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships/count")
                    .param("maxRating", "12lsa"));

    resultActions
            .andExpect(status().isBadRequest());
  }

  @Test
  public void GetShipsCountTest_doubleConversionError_minSpeed() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships/count")
                    .param("minSpeed", "12lsa"));

    resultActions
            .andExpect(status().isBadRequest());
  }
  @Test
  public void GetShipsCountTest_doubleConversionError_maxSpeed() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships/count")
                    .param("maxSpeed", "12lsa"));

    resultActions
            .andExpect(status().isBadRequest());
  }

  @Test
  public void GetShipsCountTest_intConversionError_minCrewSize() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships/count")
                    .param("minCrewSize", "12lsa"));

    resultActions
            .andExpect(status().isBadRequest());
  }
  @Test
  public void GetShipsCountTest_intConversionError_maxCrewSize() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships/count")
                    .param("maxCrewSize", "12lsa"));

    resultActions
            .andExpect(status().isBadRequest());
  }

  @Test
  public void GetShipsCountTest_longConversionError_after() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships/count")
                    .param("before", "12lsa"));

    resultActions
            .andExpect(status().isBadRequest());
  }

  @Test
  public void GetShipsCountTest_longConversionError_before() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships/count")
                    .param("after", "12lsa"));

    resultActions
            .andExpect(status().isBadRequest());
  }

  @Test
  public void GetShipsCountTest_validPagination_mustIgnore() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships/count")
                    .param("pageSize", "2")
                    .param("pageNumber", "1"));

    resultActions
            .andExpect(status().isOk())
            .andExpect(content().string("5"));
  }

  @Test
  public void GetShipsCountTest_invalidPagination_mustIgnore() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships/count")
                    .param("pageSize", "-2")
                    .param("pageNumber", "as"));

    resultActions
            .andExpect(status().isOk())
            .andExpect(content().string("5"));
  }

}
