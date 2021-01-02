package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.CreatedTrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.javamail.JavaMailSender;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {
    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private AdminConfig adminConfig;

    @Mock
    private SimpleEmailService emailService;

    @Test
    public void testFetchTrelloBoards() {
        //Given
        List<TrelloBoardDto> trelloBoardsList = new ArrayList<>();
        trelloBoardsList.add(new TrelloBoardDto("1", "Board 1", new ArrayList<>()));
        trelloBoardsList.add(new TrelloBoardDto("2", "Board 2", new ArrayList<>()));
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardsList);

        //When
        List<TrelloBoardDto> fetchedTrelloBoardsList = trelloService.fetchTrelloBoards();

        //Then
        Assert.assertEquals(2, fetchedTrelloBoardsList.size());
    }

    @Test
    public void testCreateTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Cart1", "Test description", "Test pos", "TestListId");
        CreatedTrelloCardDto newCard = new CreatedTrelloCardDto("Test id", "Test name", "Test url");
        Mail mail = new Mail("Test", "Test", "Test");
        when(trelloClient.creteNewCard(trelloCardDto)).thenReturn(newCard);
        when(adminConfig.getAdminMail()).thenReturn("test email");

        //When
        CreatedTrelloCardDto createdTrelloCardDto = trelloService.createTrelloCard(trelloCardDto);


        //Then
        Assert.assertEquals(newCard, createdTrelloCardDto);
    }
}
