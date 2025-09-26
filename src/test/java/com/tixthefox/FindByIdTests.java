package com.tixthefox;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class FindByIdTests {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void FindByIdTest() throws Exception {
    mockMvc.perform(get("/api/ships/1"))
            .andExpect(jsonPath("$.id", equalTo(1)))
            .andExpect(jsonPath("$.name", is("Orion III")));
  }

  @Test
  public void FindByIdTest_idEqualsZero() throws Exception {
    mockMvc.perform(get("/api/ships/0"))
            .andExpect(status().isBadRequest());
  }

  @Test
  public void FindByIdTest_idNotNumber() throws Exception {
    mockMvc.perform(get("/api/ships/as"))
            .andExpect(status().isBadRequest());
  }

  @Test
  public void FindByIdTest_idNotExist() throws Exception {
    mockMvc.perform(get("/api/ships/419"))
            .andExpect(status().isNotFound());
  }
}
