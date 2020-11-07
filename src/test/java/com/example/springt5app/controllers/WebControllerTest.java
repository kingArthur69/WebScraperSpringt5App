package com.example.springt5app.controllers;

import com.example.springt5app.entities.Video;
import com.example.springt5app.services.VideoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = WebController.class)
class WebControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VideoService videoService;

    @ParameterizedTest
    @CsvSource({"/index", "/", "/index.html"})
    void indexTest(String mapping) throws Exception {
        mockMvc.perform(get(mapping))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @ParameterizedTest
    @CsvSource({"/scan", "/scan.html"})
    void scanTest(String mapping) throws Exception {
        mockMvc.perform(get(mapping))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("scan"));
    }

    @Test
    void getAllVideosByPagesTest() throws Exception {
        Video video = new Video();
        video.setUrl("watch?v=");
        Page<Video> videos = new PageImpl<>(Collections.singletonList(video));
        given(videoService.findAllVideosByPages(0, 10, "id")).willReturn(videos);

        mockMvc.perform(get("/videos"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("videos", videos));
    }
}