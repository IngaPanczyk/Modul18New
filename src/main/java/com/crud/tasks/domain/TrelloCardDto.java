package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Component
public class TrelloCardDto {
    private String name;
    private String description;
    private  String pos;
    private String listId;

}
