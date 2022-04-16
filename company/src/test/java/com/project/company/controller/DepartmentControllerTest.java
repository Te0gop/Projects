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
class DepartmentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testFindDepartmentById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/departments/1"))
                .andExpect(status().isOk()).andDo(print());
    }
    @Test
    void testFindDepartmentByInvalidId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/departments/-1"))
                .andExpect(status().isInternalServerError()).andDo(print());
    }

    @Test
    void testFindAllDepartments() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/departments"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].name").value("Logistics"))
                .andExpect(jsonPath("$.[0].description").value("Logistics"))
                .andDo(print());
    }

    @Test
    void testAddDepartmentIsValidRequestStatusCreated() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/departments")
                        .content("{\"name\": \"Engineering\"," +
                                " \"description\": \"Technicians\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andDo(print());
    }

    @Test
    void testDeleteDepartmentById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/departments/2"))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    void testDeleteDepartmentsByInvalidId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/departments/-1"))
                .andExpect(status().isInternalServerError()).andDo(print());
    }

    @Test
    void testIfUpdateDepartmentWorksCorrectly() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/departments/2")
                        .content("{\"name\": \"Engineering\"," +
                                " \"description\": \"Engineers\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    void testUpdateDepartmentsWithInvalidId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/departments/-1")
                        .content("{\"name\": \"Engineering\"," +
                                " \"description\": \"Engineers\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andDo(print());
    }

}