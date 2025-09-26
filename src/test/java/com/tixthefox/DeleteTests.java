package com.tixthefox;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class DeleteTests {
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void DeleteTest_zeroId() throws Exception {
    mockMvc.perform(delete("/api/ships/0"))
            .andExpect(status().isBadRequest());
  }

  @Test
  public void DeleteTest_notExistingShip() throws Exception {
    mockMvc.perform(delete("/api/ships/3772"))
            .andExpect(status().isNotFound());
  }

  @Test
  public void DeleteTest_idNotANumber() throws Exception {
    mockMvc.perform(delete("/api/ships/12sa"))
            .andExpect(status().isBadRequest());
  }

  @Test
  public void DeleteTest_validTest() throws Exception {
    mockMvc.perform(delete("/api/ships/1"))
            .andExpect(status().isOk());

  }
}
