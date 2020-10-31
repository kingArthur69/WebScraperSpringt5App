package com.example.springt5app.controllers;

import com.example.springt5app.entities.Video;
import com.example.springt5app.services.VideoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WebController {

    private VideoService videoService;

    public WebController(VideoService videoService) {
        this.videoService = videoService;
    }

    @RequestMapping(value = {"/index", "/", "index.html"})
    public String index() {
        return "index";
    }

    @RequestMapping(value = {"/scan", "/scan.html"})
    public String scan() {
        return "scan";
    }

    @GetMapping("/videos")
    public String getAllVideosByPages(
            Model model,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        Page<Video> videos = videoService.findAllVideosByPages(pageNo, pageSize, sortBy);

        if (videos.hasContent()) {
            changeUrlsToEmbedded(videos.getContent());
        }

        model.addAttribute("videos", videos);

        return "index";

    }

//    @GetMapping("/filteredVideos")
//    public String showFilteredVideos(@RequestParam("filterBy") String filterText, Model model) {
//        List<Video> videos = videoService.findAllBySearchTextContains(filterText);
//
//        changeUrlsToEmbedded(videos);
//
//        model.addAttribute("videos", videos);
//
//        return "index";
//    }

    private void changeUrlsToEmbedded(List<Video> videos) {
        videos.forEach(video -> video.setUrl(StringUtils.replace(video.getUrl(), "watch?v=", "embed/")));
    }
}
