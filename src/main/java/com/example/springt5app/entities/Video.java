package com.example.springt5app.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "VIDEOS")
@Data
public class Video implements ScrappedObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //    private String name;
    private String url;
    private LocalDateTime creationDate;
    private String searchText;

    public Video() {
    }

    public Video(String url, String searchText) {
        this.url = url;
        this.searchText = searchText;
        this.creationDate = LocalDateTime.now();
    }
}
