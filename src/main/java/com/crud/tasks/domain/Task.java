package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor //Dodaje settery i konstruktor

public class Task {
    private Long id;
    private String title;
    private String content;
}
