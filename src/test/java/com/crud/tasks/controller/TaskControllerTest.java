package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.DbService;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.facade.TrelloFacade;
import com.crud.tasks.trello.validator.TrelloValidator;
import com.google.gson.Gson;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.web.servlet.function.RequestPredicates.accept;


@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @MockBean
    private TrelloFacade trelloFacade;

    @MockBean
    private TaskRepository taskRepository;


    @Test
    public void shouldGetAllTasks() throws Exception {
        //Given
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(new TaskDto(1L, "Test title", "Test content"));

        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L, "Test title", "Test content"));

        when(taskMapper.mapToTaskDtoList(any())).thenReturn(taskDtoList);
        when(dbService.getAllTasks()).thenReturn(taskList);

        //When&Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                //trello task fields
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("[0].id", is(1)))
                .andExpect(jsonPath("[0].title", is("Test title")))
                .andExpect(jsonPath("[0].content", is("Test content")));
    }

    @Test
    public void shouldGetTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Test title", "Test content");

        Task task = new Task(1L, "Test title", "Test content");

        when(taskMapper.mapToTaskDto(any())).thenReturn(taskDto);
        when(dbService.getTask(taskDto.getId())).thenReturn(java.util.Optional.of(task));

        //When&Then
        mockMvc.perform(get("/v1/task/getTask").param("taskId", "1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                //trello task fields
                .andExpect(jsonPath("id", is(1)))
                .andExpect(jsonPath("title", is("Test title")))
                .andExpect(jsonPath("content", is("Test content")));
    }
/*
    @Test
    public void shouldCreateTask() throws Exception {
       // TaskDto taskDto = new TaskDto(1L, "Test title", "Test content");

        Task createdTask = new Task(1L, "Test title", "Test content");

        when(taskMapper.mapToTask(any())).thenReturn(createdTask);
        when(dbService.saveTask(ArgumentMatchers.any(Task.class))).thenReturn(createdTask);


        Gson gson = new Gson();
        String jsonContent = gson.toJson(createdTask);

        //When&Then
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                //.andExpect(status().isCreated())
                .andExpect(jsonPath("$.createdTask.id", is(1)))
                .andExpect(jsonPath("$.createdTask.title", is("Test title")))
                .andExpect(jsonPath("$.createdTas.content", is("Test content")));
    }

    @Test
    public void updateTask() throws Exception {
        TaskDto taskDto = new TaskDto(1L, "Test title", "Test content");

        Task task = new Task(1L, "Test title", "Test content");
        Task task1 = new Task(2L, "Test title1", "Test content1");

        when(dbService.saveTask(ArgumentMatchers.any(Task.class))).thenReturn(task);
        when(taskMapper.mapToTask(any())).thenReturn(task);
        when(taskMapper.mapToTaskDto(any())).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When&Then
        mockMvc.perform(put("/v1/task/updateTask" + task.getId())
                .param("title", "Updated test title")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Updated test title")))
                .andExpect(jsonPath("$.content", is("Test content")));
    }

    @Test
    public void deleteTask() throws Exception {

        Task task = new Task(1L, "Test title", "Test content");

        taskRepository.save(task);
        Mockito.verify(dbService).deleteTask(task.getId());

        //When&Then
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/task/deleteTask/1")
                //.param("id", String.valueOf(id))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }*/

}
