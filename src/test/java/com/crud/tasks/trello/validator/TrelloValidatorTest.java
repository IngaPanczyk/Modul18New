package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCard;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class TrelloValidatorTest {
    @InjectMocks
    TrelloValidator trelloValidator;
    @Mock
    TrelloCard trelloCard;


    @Test
    public void testValidateCard() {
        //Given
        TrelloCard trelloCard1 = new TrelloCard("test", "Test description", "test pos", "Test Id");
        when(trelloCard.getName()).thenReturn(trelloCard1.getName());

        //When
        trelloValidator.validateCard(trelloCard);

        //Then
        Mockito.verify(trelloCard).getName();
    }

    @Test
    public void testValidateTrelloBoards() {
        //Given
        List<TrelloBoard> trelloBoardsList = new ArrayList<>();
        trelloBoardsList.add(new TrelloBoard("1", "test", new ArrayList<>()));
        trelloBoardsList.add(new TrelloBoard("2", "Board 2", new ArrayList<>()));

        //When
        List<TrelloBoard> validatedList = trelloValidator.validateTrelloBoards(trelloBoardsList);

        //Then
        Assert.assertEquals(1, validatedList.size());

    }

}
