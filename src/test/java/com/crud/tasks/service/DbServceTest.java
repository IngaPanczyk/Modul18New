package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServceTest {
    @InjectMocks // jak to rozumieć?
    private DbService dbService;
    @Mock
    TaskRepository repository;

    @Test
    public void testGetAllTasks() {
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L, "Test title", "Test description"));
        taskList.add(new Task(2L, "Test title", "Test description"));
        when(repository.findAll()).thenReturn(taskList);

        //When
        List<Task> createdList = dbService.getAllTasks();

        //Then
        Assert.assertEquals(taskList, createdList);
    }

    @Test
    public void testSaveTask() {
        //Given
        Task task = new Task(1L, "Test title", "Test description");

        when(repository.save(task)).thenReturn(task);

        //When
        Task savedTask = dbService.saveTask(task);

        //Then
        Assert.assertEquals(task, savedTask);
    }
    @Test
    public void testGetTask() {
        //Given
        Task task = new Task(1L, "Test title", "Test description");

        when(repository.findById(1L)).thenReturn(Optional.of(task));

        //When
        Optional<Task> savedTask = dbService.getTask(1L);

        //Then
        Assert.assertTrue(savedTask.isPresent());
    }
    @Test
    public void testDeleteTask() {
        //Given
        Task task = new Task(1L, "Test title", "Test description");
        //Jak zrobić mocka dla metody która jest void

        //When


        //Then

    }

}
