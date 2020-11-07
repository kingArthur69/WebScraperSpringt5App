package com.example.springt5app.controllers;

import com.example.springt5app.scanners.IScanner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = ScanController.class)
class ScanControllerTest {

    private static final String SEARCH_TEXT = "cat";
    private static final String SEARCH_TEXT_PARAMETER = "searchText";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IScanner scanner;

    @Test
    void scanTest() throws Exception {
        doNothing().when(scanner).scan(SEARCH_TEXT);

        mockMvc.perform(get("/scanPage")
                .param(SEARCH_TEXT_PARAMETER, SEARCH_TEXT)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("scan"));
    }
}