package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCard;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TrelloValidatorTest {
    @Autowired
    TrelloValidator trelloValidator;

    @Test
    public void testValidateCard() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Test name", "Test description", "test pos", "Test Id");

        //When
        trelloValidator.validateCard(trelloCard);

        //Then
        //Jak sprwdzić metodę void// Mockito. verify
    }
    @Test
    public void testValidateTrelloBoards(){
        //Given
        List<TrelloBoard> trelloBoardsList = new ArrayList<>();
        trelloBoardsList.add(new TrelloBoard("1", "test", new ArrayList<>()));
        trelloBoardsList.add(new TrelloBoard("2", "Board 2", new ArrayList<>()));

        //When
        List<TrelloBoard> validatedList = trelloValidator.validateTrelloBoards(trelloBoardsList);

        //Then
        Assert.assertEquals(1,validatedList.size());

    }

}
