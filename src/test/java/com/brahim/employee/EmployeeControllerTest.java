package com.brahim.employee;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.brahim.employee.repository.EmployeeRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testPostEmployeeWithAdminRole() throws Exception {
        // Perform a POST request with an 'ADMIN' user role
        mockMvc.perform(MockMvcRequestBuilders
                .post("http://localhost:8080/api/employees/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstname\": \"Test Firstname\",\n" +
                        "  \"lastname\": \"Test Lastname\",\n" +
                        "  \"age\": 30,\n" +
                        "  \"birthDate\": \"1982-11-10\",\n" +
                        "  \"address\": \"Test Address\",\n" +
                        "  \"function\": \"Test Function\",\n" +
                        "  \"engagedDate\": \"2012-12-12\",\n" +
                        "  \"familySituation\": \"SINGLE\",\n" +
                        "  \"administrativeSituation\": \"CONTRACTED\",\n" +
                        "  \"department\": {\n" +
                        "    \"id\": 4,\n" +
                        "    \"name\": \"Services\",\n" +
                        "    \"address\": \"684 Lindbergh Way\"\n" +
                        "  }}"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testPostEmployeeWithUserRole() throws Exception {
        // Perform a POST request with a 'USER' user role
        mockMvc.perform(MockMvcRequestBuilders
                .post("http://localhost:8080/api/employees/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")) // You can provide a valid JSON representation of an employee here
                .andExpect(MockMvcResultMatchers.status().isForbidden()); // Expect a 403 Forbidden response
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testGetEmployeesWithAdminRole() throws Exception {
        // Perform a POST request with an 'ADMIN' user role
        mockMvc.perform(MockMvcRequestBuilders
                .get("http://localhost:8080/api/employees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testGetEmployeesWithUserRole() throws Exception {
        // Perform a POST request with an 'ADMIN' user role
        mockMvc.perform(MockMvcRequestBuilders
                .get("http://localhost:8080/api/employees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test // Still doesn't work
    @WithMockUser(roles = "ADMIN")
    public void testDeleteEmployeeWithAdminRole() throws Exception {
        Integer id = employeeRepository.findByFirstname("Test Firstname").get(0).getId();
        // Perform a DELETE request with an 'ADMIN' user role
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/employees/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test // Still doesn't work
    @WithMockUser(roles = "USER")
    public void testDeleteEmployeeWithUserRole() throws Exception {
        Integer id = employeeRepository.findByFirstname("Test Firstname").get(0).getId();
        // Perform a DELETE request with a 'USER' user role
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/employees/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isForbidden()); // Expect a 403 Forbidden response
    }
}
