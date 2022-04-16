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
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testFindEmployeeById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/1"))
                .andExpect(status().isOk()).andDo(print());
    }
    @Test
    void testFindEmployeeByInvalidId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/-1"))
                .andExpect(status().isInternalServerError()).andDo(print());
    }

    @Test
    void testFindAllEmployees() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].firstName").value("Mike"))
                .andExpect(jsonPath("$.[0].lastName").value("Johnson"))
                .andExpect(jsonPath("$.[0].personalId").value("23534623"))
                .andExpect(jsonPath("$.[0].position").value("DIRECTOR"))
                .andDo(print());
    }

    @Test
    void testAddEmployeeIsValidRequestStatusCreated() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                        .content("{\"firstName\": \"Dan\"," +
                                " \"lastName\": \"Smith\"," +
                                " \"personalId\": \"54657633236\"," +
                                " \"age\": \"38\"," +
                                " \"position\": \"Employee\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andDo(print());
    }

    @Test
    void testDeleteEmployeeById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/employees/2"))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    void testDeleteEmployeeByInvalidId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/-1"))
                .andExpect(status().isInternalServerError()).andDo(print());
    }

    @Test
    void testIfUpdateEmployeeWorksCorrectly() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/employees/3")
                        .content("{\"firstName\": \"John\"," +
                                " \"lastName\": \"Smith\"," +
                                " \"personalId\": \"33435656\"," +
                                " \"age\": \"32\"," +
                                " \"position\": \"Employee\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    void testUpdateEmployeeWithInvalidId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/employees/-1")
                        .content("{\"firstName\": \"John\"," +
                                " \"lastName\": \"Smith\"," +
                                " \"personalId\": \"33435656\"," +
                                " \"age\": \"32\"," +
                                " \"position\": \"Employee\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andDo(print());
    }
}