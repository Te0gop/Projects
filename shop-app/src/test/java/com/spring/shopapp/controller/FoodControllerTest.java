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
class FoodControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testFindFoodById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/foods/4"))
                .andExpect(status().isOk()).andDo(print());
    }
    @Test
    void testFindFoodByInvalidId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/foods/-1"))
                .andExpect(status().isInternalServerError()).andDo(print());
    }

    @Test
    void testFindAllFoods() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/foods"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.[0].id").value(4))
                .andExpect(jsonPath("$.[0].name").value("Banichka"))
                .andExpect(jsonPath("$.[0].expiryDate").value("20-May"))
                .andExpect(jsonPath("$.[0].manufacturerName").value("Bakery"))
                .andExpect(jsonPath("$.[0].price").value(1.60))
                .andDo(print());
    }

    @Test
    void testAddFoodIsValidRequestStatusCreated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/foods")
                        .content("{\"name\": \"Croissant\"," +
                                " \"expiryDate\": \"20-May-22\"," +
                                " \"price\": 2," +
                                " \"manufacturerName\": \"Bakery\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andDo(print());
    }

    @Test
    void testDeleteFoodById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/foods/16"))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    void testDeleteFoodByInvalidId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/foods/-1"))
                .andExpect(status().isInternalServerError()).andDo(print());
    }

    @Test
    void testIfUpdateFoodWorkCorrectly() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/foods/11")
                        .content("{\"name\": \"Croissant with chocolate\"," +
                                " \"expiryDate\": \"20-June-22\"," +
                                " \"price\": 3," +
                                " \"manufacturerName\": \"Bakery\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    void testUpdateFoodWithInvalidId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/drinks/-1")
                        .content("{\"name\": \"Bread\"," +
                                " \"expiryDate\": \"20-June-22\"," +
                                " \"price\": 3," +
                                " \"manufacturerName\": \"Bakery\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andDo(print());
    }
}