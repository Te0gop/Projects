package com.project.company.controller;

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
class DirectorateControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testFindDirectorateById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/directorates/1"))
                .andExpect(status().isOk()).andDo(print());
    }
    @Test
    void testFindDirectorateByInvalidId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/directorates/-1"))
                .andExpect(status().isInternalServerError()).andDo(print());
    }

    @Test
    void testFindAllDirectorates() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/directorates"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].name").value("Logistic Directorate"))
                .andExpect(jsonPath("$.[0].description").value("Logistics Headquarters"))
                .andDo(print());
    }

    @Test
    void testAddDirectorateIsValidRequestStatusCreated() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/directorates")
                        .content("{\"name\": \"Engineering Directorate\"," +
                                " \"description\": \"Technicians Headquarters\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andDo(print());
    }

    @Test
    void testDeleteDirectorateById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/directorates/2"))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    void testDeleteDirectorateByInvalidId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/directorates/-1"))
                .andExpect(status().isInternalServerError()).andDo(print());
    }

    @Test
    void testIfUpdateDirectorateWorksCorrectly() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/directorates/3")
                        .content("{\"firstName\": \"John\"," +
                                " \"lastName\": \"Smith\"," +
                                " \"personalId\": \"33435656\"," +
                                " \"age\": \"32\"," +
                                " \"position\": \"Employee\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    void testUpdateDirectorateWithInvalidId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/directorates/-1")
                        .content("{\"name\": \"Engineering Directorate\"," +
                                " \"description\": \"Technicians Headquarters\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andDo(print());
    }

}