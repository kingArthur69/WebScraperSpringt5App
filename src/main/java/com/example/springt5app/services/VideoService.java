package com.example.springt5app.services;

import com.example.springt5app.dao.VideoDao;
import com.example.springt5app.entities.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VideoService {

    private VideoDao videoDao;

    public VideoService(VideoDao videoDao) {
        this.videoDao = videoDao;
    }

    public List<Video> findAllVideos() {
        return videoDao.findAll();
    }

    public boolean videoExistsBySearchTextAndCreationDateAfter(String searchText, LocalDateTime localDateTime) {
        return videoDao.existsBySearchTextEqualsAndCreationDateAfter(searchText, localDateTime);
    }

    public void saveAll(List<Video> videos) {
        videoDao.saveAll(videos);
    }

    public List<Video> findAllBySearchTextContains(String searchTextPart) {
        return videoDao.findAllBySearchTextContains(searchTextPart);
    }

    public Page<Video> findAllVideosByPages(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return videoDao.findAll(paging);
    }
}
