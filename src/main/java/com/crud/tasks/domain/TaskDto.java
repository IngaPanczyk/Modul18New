package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor //Dodaje settery i konstruktor
@NoArgsConstructor

public class TaskDto {
    private Long id;
    private String title;
    private String content;

}
