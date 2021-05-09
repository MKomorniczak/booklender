package se.lexicon.marek.booklender.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import se.lexicon.marek.booklender.dto.LibraryUserDto;


import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class LibraryUserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() throws Exception {
        objectMapper = new ObjectMapper();
        LibraryUserDto libraryUserDto = new LibraryUserDto();
        libraryUserDto.setName("Marek");
        libraryUserDto.setEmail("abc123@gmail.com");
        String jsonForLibraryUser = objectMapper.writeValueAsString(libraryUserDto);

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/libraryUser/")
                .content(jsonForLibraryUser)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();

        int actualStatus = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(201, actualStatus);

        String libraryUserJsonResponse = mvcResult.getResponse().getContentAsString();
        LibraryUserDto libraryUserDto1 = objectMapper.readValue(libraryUserJsonResponse, new TypeReference<LibraryUserDto>() {
        });
        Assertions.assertEquals("Marek", libraryUserDto1.getName());
    }


    //todo: ---------------------------
    @DisplayName("Save libraryUser")
    @Test
    public void test_save_json() throws Exception {
        mockMvc.perform(
                post("/api/v1/libraryUser/")
                        .content("{\n" +
                                "    \"name\" : \"Dominika\"\n" +
                                "    \"email\" : \"asd12@gmail.com\"\n" +
                                "}")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("Dominika")))
                .andExpect(content().string(containsString("asd12@gmail.com")))
                .andDo(print())
                .andReturn();

    }

    @DisplayName("Find libraryUser by Id 1")
    @Test
    public void test_find_by_id_1() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                get("/api/v1/libraryUser/1")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("Marek")))
                .andReturn();


        String content = mvcResult.getResponse().getContentAsString();
        LibraryUserDto actual = objectMapper.readValue(content, new TypeReference<LibraryUserDto>() {
        });
        Assertions.assertEquals("Marek", actual.getName());
    }

    @Test
    @DisplayName("get all ")
    public void test_find_all_size_1() throws Exception {
        mockMvc.perform(get("/api/v1/libraryUser/")
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Marek")))
                .andReturn();
    }
    @Test
    @DisplayName("delete by id, size 0")
    public void test_delete_by_id_check_size_0() throws Exception {
        mockMvc.perform(delete("/api/v1/libraryUser/1"))
                .andExpect(status().isOk())
                .andReturn();

        mockMvc.perform(get("/api/v1/libraryUser/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)))
                .andReturn();
    }
}


