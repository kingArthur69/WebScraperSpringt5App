package com.example.springt5app.dao;

import com.example.springt5app.entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VideoDao extends JpaRepository<Video, Long> {

    List<Video> findAllBySearchTextEquals(String searchText);

    boolean existsBySearchTextEquals(String searchText);

    boolean existsBySearchTextEqualsAndCreationDateAfter(String searchText, LocalDateTime localDateTime);

    List<Video> findAllBySearchTextContains(String searchTextPart);

}
