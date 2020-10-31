package com.example.springt5app.scanners;

//import com.example.springt5app.dao.ScrappedObjectDao;

import com.example.springt5app.entities.Video;
import com.example.springt5app.scrapers.IWebScraper;
import com.example.springt5app.services.VideoService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class YouTubeScanner implements IScanner {

    public static final String YOUTUBE_HOMEPAGE = "https://www.youtube.com/";
    public static final String SEARCH_FORM_CSS_SELECTOR = "input#search";
    public static final String VIDEO_CSS_SELECTOR = "div a#video-title";
    public static final String VIDEO_ATTRIBUTE = "href";

    private IWebScraper webScraper;

    private VideoService videoService;

    public YouTubeScanner(IWebScraper webScraper, VideoService videoService) {
        this.webScraper = webScraper;
        this.videoService = videoService;
    }

    @Override
    public void scan(String text) {
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1L);
        if (!videoService.videoExistsBySearchTextAndCreationDateAfter(text, yesterday)) {
            webScraper.init();
            webScraper.get(YOUTUBE_HOMEPAGE);

            webScraper.wait(SEARCH_FORM_CSS_SELECTOR, 10L);
            webScraper.submitText(SEARCH_FORM_CSS_SELECTOR, text);

            webScraper.wait(VIDEO_CSS_SELECTOR, 10L);

            List<String> attributesText = webScraper.getAllTextFromAttributesPath(VIDEO_CSS_SELECTOR, VIDEO_ATTRIBUTE);

            webScraper.close();

            List<Video> videos = attributesText.stream().map(attribute -> new Video(attribute, text)).collect(Collectors.toList());

            videoService.saveAll(videos);
        }
    }
}
