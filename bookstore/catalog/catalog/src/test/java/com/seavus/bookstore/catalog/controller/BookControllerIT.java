package com.seavus.bookstore.catalog.controller;

import com.seavus.bookstore.catalog.CatalogSpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@CatalogSpringBootTest
class BookControllerIT {

  @Autowired
  private MockMvc mvc;

  @Test
  void findBooks() throws Exception {
    mvc.perform(get("/api/books"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(6)))
        .andExpect(jsonPath("$[0].uuid").isNotEmpty())
        .andExpect(jsonPath("$[0].title").isNotEmpty())
        .andExpect(jsonPath("$[0].author").isNotEmpty())
        .andExpect(jsonPath("$[0].genre").isNotEmpty());
  }

  @Test
  void findBook() throws Exception {
    mvc.perform(get("/api/books/{uuid}", "it"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(APPLICATION_JSON))
        .andExpect(jsonPath("$.uuid").isNotEmpty())
        .andExpect(jsonPath("$.title").isNotEmpty())
        .andExpect(jsonPath("$.author").isNotEmpty())
        .andExpect(jsonPath("$.genre").isNotEmpty());
  }

  @Test
  void findBook_bookDoesNotExist() throws Exception {
    mvc.perform(get("/api/books/{uuid}", "invalid"))
        .andExpect(status().isNotFound());
  }
}
