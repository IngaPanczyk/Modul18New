package com.crud.tasks;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MappersTestSuite {
    @Autowired
    TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "Test Name", true);
        List<TrelloListDto> trelloListDtoLists = new ArrayList<>();
        trelloListDtoLists.add(trelloListDto1);

        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("Test Name", "1", trelloListDtoLists);
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(trelloBoardDto1);

        //When
        List<TrelloBoard> mappedTrelloBoards = trelloMapper.mapToBoards(trelloBoardDtos);

        //Then
        Assert.assertEquals(TrelloBoard.class, mappedTrelloBoards.get(0).getClass());
        Assert.assertEquals("1", mappedTrelloBoards.get(0).getId());
        Assert.assertEquals("Test Name", mappedTrelloBoards.get(0).getName());
        Assert.assertEquals(TrelloList.class, mappedTrelloBoards.get(0).getLists().get(0).getClass());
        Assert.assertEquals("1", mappedTrelloBoards.get(0).getLists().get(0).getId());
        Assert.assertEquals("Test Name", mappedTrelloBoards.get(0).getLists().get(0).getName());
        Assert.assertEquals(true, mappedTrelloBoards.get(0).getLists().get(0).isClosed());
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        TrelloList trelloList1 = new TrelloList("1", "Test Name", true);
        List<TrelloList> trelloListLists = new ArrayList<>();
        trelloListLists.add(trelloList1);

        TrelloBoard trelloBoard1 = new TrelloBoard("Test Name", "1", trelloListLists);
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard1);

        //When
        List<TrelloBoardDto> mappedTrelloBoardsDto = trelloMapper.mapToBoardsDto(trelloBoards);

        //Then
        Assert.assertEquals(TrelloBoardDto.class, mappedTrelloBoardsDto.get(0).getClass());
        Assert.assertEquals("1", mappedTrelloBoardsDto.get(0).getId());
        Assert.assertEquals("Test Name", mappedTrelloBoardsDto.get(0).getName());
        Assert.assertEquals(TrelloListDto.class, mappedTrelloBoardsDto.get(0).getLists().get(0).getClass());
        Assert.assertEquals("1", mappedTrelloBoardsDto.get(0).getLists().get(0).getId());
        Assert.assertEquals("Test Name", mappedTrelloBoardsDto.get(0).getLists().get(0).getName());
        Assert.assertEquals(true, mappedTrelloBoardsDto.get(0).getLists().get(0).isClosed());
    }

    @Test
    public void testMapToList() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "Test Name", true);
        List<TrelloListDto> trelloListListsDto = new ArrayList<>();
        trelloListListsDto.add(trelloListDto1);

        //When
        List<TrelloList> mappedtTrelloListLists = trelloMapper.mapToList(trelloListListsDto);

        //Then
        Assert.assertEquals(TrelloList.class, mappedtTrelloListLists.get(0).getClass());
        Assert.assertEquals("1", mappedtTrelloListLists.get(0).getId());
        Assert.assertEquals("Test Name", mappedtTrelloListLists.get(0).getName());
        Assert.assertEquals(true, mappedtTrelloListLists.get(0).isClosed());
    }

    @Test
    public void testMapToListDto() {
        //Given
        TrelloList trelloList1 = new TrelloList("1", "Test Name", true);
        List<TrelloList> trelloListLists = new ArrayList<>();
        trelloListLists.add(trelloList1);

        //When
        List<TrelloListDto> trelloListDtoLists = trelloMapper.mapToListDto(trelloListLists);

        //Then
        Assert.assertEquals(TrelloListDto.class, trelloListDtoLists.get(0).getClass());
        Assert.assertEquals("1", trelloListDtoLists.get(0).getId());
        Assert.assertEquals("Test Name", trelloListDtoLists.get(0).getName());
        Assert.assertEquals(true, trelloListDtoLists.get(0).isClosed());
    }

    @Test
    public void testMapToTrelloCard() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Test name", "Test description", "Test pos", "TestId");

        //When
        TrelloCardDto mappedTrelloCardDto = trelloMapper.mapToTrelloCardDto(trelloCard);

        //Then
        Assert.assertEquals(TrelloCardDto.class, mappedTrelloCardDto.getClass());
        Assert.assertEquals("Test name", mappedTrelloCardDto.getName());
        Assert.assertEquals("Test description", mappedTrelloCardDto.getDescription());
        Assert.assertEquals("Test pos", mappedTrelloCardDto.getPos());
        Assert.assertEquals("TestId", mappedTrelloCardDto.getListId());
    }

    @Test
    public void testMapToTrelloCardDto() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Test name", "Test description", "Test pos", "TestId");

        //When
        TrelloCard mappedTrelloCard = trelloMapper.mapToTrelloCard(trelloCardDto);

        //Then
        Assert.assertEquals(TrelloCard.class, mappedTrelloCard.getClass());
        Assert.assertEquals("Test name", mappedTrelloCard.getName());
        Assert.assertEquals("Test description", mappedTrelloCard.getDescription());
        Assert.assertEquals("Test pos", mappedTrelloCard.getPos());
        Assert.assertEquals("TestId", mappedTrelloCard.getListId());
    }
}
