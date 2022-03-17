package com.spring.shopapp.controller;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class DrinkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testFindDrinkById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/drinks/1"))
                .andExpect(status().isOk()).andDo(print());
    }
    @Test
    void testFindDrinkByInvalidId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/drinks/-1"))
                .andExpect(status().isInternalServerError()).andDo(print());
    }

    @Test
    void testFindAllDrinks() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/drinks"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].name").value("Whiskey"))
                .andExpect(jsonPath("$.[0].price").value(45.00))
                .andExpect(jsonPath("$.[0].manufacturerName").value("Jack Daniels"))
                .andExpect(jsonPath("$.[0].alcoholContent").value(42))
                .andDo(print());
    }

    @Test
    void testAddDrinkIsValidRequestStatusCreated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/drinks")
                        .content("{\"name\": \"Vodka\"," +
                                " \"price\": 20," +
                                " \"manufacturerName\": \"Smirnoff\"," +
                                " \"alcoholContent\": 50}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andDo(print());
    }

    @Test
    void testDeleteDrinkById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/drinks/8"))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    void testDeleteDrinkByInvalidId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/drinks/-1"))
                .andExpect(status().isInternalServerError()).andDo(print());
    }

    @Test
    void testIfUpdateDrinkWorkCorrectly() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/drinks/10")
                        .content("{\"name\": \"Vodka\"," +
                                " \"price\": 20," +
                                " \"manufacturerName\": \"Savoy\"," +
                                " \"alcoholContent\": 44}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    void testUpdateDrinkWithInvalidId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/drinks/-1")
                        .content("{\"name\": \"Bourbon\"," +
                                " \"price\": 40," +
                                " \"manufacturerName\": \"Jim Beam\"," +
                                " \"alcoholContent\": 43}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andDo(print());
    }
}