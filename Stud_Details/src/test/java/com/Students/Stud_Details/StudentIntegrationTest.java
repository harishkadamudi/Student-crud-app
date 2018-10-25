package com.Students.Stud_Details;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.Students.Stud_Details.Model.Student;
import com.Students.Stud_Details.Repo.StudRepo;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = StudDetailsApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integration.properties"
)
public class StudentIntegrationTest {

    @LocalServerPort
    private Integer port;
    HttpHeaders headers = new HttpHeaders();

    @Autowired
    private MockMvc mvc;

    @Autowired
    private StudRepo studRepo;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void whenPostStudent_200Response() throws Exception {
        Student stud = new Student();
        stud.setFirstName("Subham");
        stud.setLastName("choudhary");
        stud.setEmail("choudharysubham47@gmail.com");

        mvc.perform(post("/Student")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(stud)))
                .andExpect(status().isOk());
    }


    @SuppressWarnings("unused")
    private String buildurl(String uri) {
        return "http://localhost:" + port + uri;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
