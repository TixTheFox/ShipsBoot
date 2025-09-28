package com.tixthefox;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class GetAllTests {

  @Autowired
  private MockMvc mockMvc;

  // Valid tests

  @Test
  void GetShipsListTest_noParam() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships"));

    resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(5)));
  }

  @Test
  void GetShipsListTest_nameParam() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships")
            .param("name", "edalus"));

    resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[*].name", hasItem("Daedalus")))
            .andExpect(jsonPath("$[*].name", hasItem("Proedalus")));
  }

  @Test
  void GetShipsListTest_planetParam() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships")
            .param("planet", "u"));

    resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$[*].planet", hasItem("Jupiter")))
            .andExpect(jsonPath("$[*].planet", hasItem("Neptune")))
            .andExpect(jsonPath("$[*].planet", hasItem("Mercury")));
  }

  @Test
  void GetShipsListTest_ShipTypeParam() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships")
            .param("shipType", "MERCHANT"));

    resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)));
  }

  @Test
  void GetShipsListTest_ShipTypeParam_LowerCase() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships")
                    .param("shipType", "merchant"));

    resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)));
  }

  @Test
  void GetShipsListTest_unknownShipTypeParam() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships")
                    .param("shipType", "goofy"));

    resultActions
            .andExpect(status().isBadRequest());
  }

  @Test
  void GetShipsListTest_AfterParam() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships")
            .param("after", "32377449600000"));

    resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)));
  }

  @Test
  void GetShipsListTest_BeforeParam() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships")
            .param("before", "32377449600000"));

    resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)));
  }

  @Test
  void GetShipsListTest_isUsedParam() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships")
            .param("isUsed", "true"));

    resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)));
  }

  @Test
  void GetShipsListTest_minSpeedParam() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships")
            .param("minSpeed", "0.82"));

    resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)));
  }

  @Test
  void GetShipsListTest_maxSpeedParam() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships")
            .param("maxSpeed", "0.82"));

    resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(4)));
  }

  @Test
  void GetShipsListTest_minCrewParam() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships")
            .param("minCrewSize", "617"));

    resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(4)));
  }

  @Test
  void GetShipsListTest_maxCrewParam() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships")
            .param("maxCrewSize", "617"));

    resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)));
  }

  @Test
  void GetShipsListTest_minRatingParam() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships")
                    .param("minRating", "1.31"));

    resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(4)));
  }

  @Test
  void GetShipsListTest_maxRatingParam() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships")
                    .param("maxRating", "1.31"));

    resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)));
  }

  @Test
  void GetShipsListTest_orderParam() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships")
                    .param("order", "SPEED"));

    resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(5)))
            .andExpect(jsonPath("$[0].id", is(4)))
            .andExpect(jsonPath("$[1].id", is(5)))
            .andExpect(jsonPath("$[2].id", is(3)))
            .andExpect(jsonPath("$[3].id", is(1)))
            .andExpect(jsonPath("$[4].id", is(2)));
  }

  @Test
  void GetShipsListTest_orderParam_lowerCase() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships")
                    .param("order", "speed"));

    resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(5)))
            .andExpect(jsonPath("$[0].id", is(4)))
            .andExpect(jsonPath("$[1].id", is(5)))
            .andExpect(jsonPath("$[2].id", is(3)))
            .andExpect(jsonPath("$[3].id", is(1)))
            .andExpect(jsonPath("$[4].id", is(2)));
  }

  @Test
  void GetShipsListTest_unknownOrderParam_returnSortedById() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships")
                    .param("order", "speeds"));

    resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(5)))
            .andExpect(jsonPath("$[0].id", is(1)))
            .andExpect(jsonPath("$[1].id", is(2)))
            .andExpect(jsonPath("$[2].id", is(3)))
            .andExpect(jsonPath("$[3].id", is(4)))
            .andExpect(jsonPath("$[4].id", is(5)));
  }

  @Test
  void GetShipsListTest_complexParams_after_minCrewSize_order() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships")
                    .param("after", "32377449600000")
                    .param("minCrewSize", "617")
                    .param("order", "SPEED")
            );

    resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].id", is(4)))
            .andExpect(jsonPath("$[1].id", is(2)));
  }

  @Test
  void GetShipsListTest_complexParams_minSpeed_maxSpeed_shipType() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships")
                    .param("minSpeed", "0.6")
                    .param("maxSpeed", "0.85")
                    .param("shipType", "MERCHANT")
            );

    resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].id", is(1)));
  }

  //
  // Invalid inputs
  //

  @Test
  void GetShipsListTest_doubleConversionError_minRating() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships")
                    .param("minRating", "12lsa"));

    resultActions
            .andExpect(status().isBadRequest());
  }

  @Test
  void GetShipsListTest_doubleConversionError_maxRating() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships")
                    .param("maxRating", "12lsa"));

    resultActions
            .andExpect(status().isBadRequest());
  }

  @Test
  void GetShipsListTest_doubleConversionError_minSpeed() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships")
                    .param("minSpeed", "12lsa"));

    resultActions
            .andExpect(status().isBadRequest());
  }
  @Test
  void GetShipsListTest_doubleConversionError_maxSpeed() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships")
                    .param("maxSpeed", "12lsa"));

    resultActions
            .andExpect(status().isBadRequest());
  }

  @Test
  void GetShipsListTest_intConversionError_minCrewSize() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships")
                    .param("minCrewSize", "12lsa"));

    resultActions
            .andExpect(status().isBadRequest());
  }
  @Test
  void GetShipsListTest_intConversionError_maxCrewSize() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships")
                    .param("maxCrewSize", "12lsa"));

    resultActions
            .andExpect(status().isBadRequest());
  }

  @Test
  void GetShipsListTest_longConversionError_after() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships")
                    .param("before", "12lsa"));

    resultActions
            .andExpect(status().isBadRequest());
  }

  @Test
  void GetShipsListTest_longConversionError_before() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships")
                    .param("after", "12lsa"));

    resultActions
            .andExpect(status().isBadRequest());
  }

  //
  // PAGINATION
  //

  @Test
  void GetShipsListTest_Pagination_defaultParams() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships"));

    resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(5)));
  }

  @Test
  void GetShipsListTest_Pagination_ValidInputs() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships")
                    .param("pageSize", "3")
                    .param("pageNumber", "0"));

    resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)));

    resultActions =
            mockMvc.perform(get("/api/ships")
                    .param("pageSize", "3")
                    .param("pageNumber", "1"));

    resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)));
  }

  @Test
  void GetShipsListTest_Pagination_defaultPageNumber() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships")
                    .param("pageSize", "3"));

    resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)));
  }

  @Test
  void GetShipsListTest_Pagination_pageSizeBiggerThanDataSize() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships")
                    .param("pageSize", "25"));

    resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(5)));
  }

  @Test
  void GetShipsListTest_Pagination_pageNumberBiggerThanDataSize() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships")
                    .param("pageNumber", "25"));

    resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(0)));
  }

  @Test
  void GetShipsListTest_Pagination_invalidPageNumber() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships")
                    .param("pageNumber", "-21"));

    resultActions
            .andExpect(status().isBadRequest());

    resultActions =
            mockMvc.perform(get("/api/ships")
                    .param("pageNumber", "2as"));

    resultActions
            .andExpect(status().isBadRequest());
  }

  @Test
  void GetShipsListTest_Pagination_invalidPageSize() throws Exception {
    ResultActions resultActions =
            mockMvc.perform(get("/api/ships")
                    .param("pageSize", "-21"));

    resultActions
            .andExpect(status().isBadRequest());

    resultActions =
            mockMvc.perform(get("/api/ships")
                    .param("pageSize", "2as"));

    resultActions
            .andExpect(status().isBadRequest());
  }
}
