package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class TrelloClient {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UrlCreator creator;


    public List<Optional<TrelloBoardDto>> getTrelloBoards() {
         TrelloBoardDto[] boardsResponse = restTemplate.getForObject(creator.createUrl(), TrelloBoardDto[].class);
         return Arrays.asList(boardsResponse);
      /*  if (boardsResponse != null) {
            return Arrays.asList(boardsResponse);
        }
        return new ArrayList<>();*/
    }
}
