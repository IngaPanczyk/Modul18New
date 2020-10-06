package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/v1/task")
public class TaskController {
    @Autowired
    private DbService service;
    @Autowired
    private TaskMapper taskMapper;

    @RequestMapping(method = RequestMethod.GET,value ="getTasks" )
    public List<TaskDto> getTasks() {
        return taskMapper.mapToTaskDtoList(service.getAllTasks());
    }

    @RequestMapping(method = RequestMethod.GET,value ="getTask" )
    public TaskDto getTask(@RequestParam Long taskId) throws TaskNotFoundException{
        return taskMapper.mapToTaskDto(service.getTask(taskId).orElseThrow(TaskNotFoundException::new));
    }
    @RequestMapping(method = RequestMethod.POST,value ="createTask",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskDto taskDto) {
        service.saveTask(taskMapper.mapToTask(taskDto));
    }
    /*
    @RequestMapping(method = RequestMethod.GET,value ="getTask" )
    public TaskDto getTask(Long taskId) {
        return new TaskDto(1L, "test title", "test contenet");
    }
    @RequestMapping(method = RequestMethod.DELETE,value ="deleteTask" )
    public void deleteTask(Long id) {
    }
    @RequestMapping(method = RequestMethod.PUT,value ="updateTask" )
    public TaskDto updateTask(TaskDto taskDto) {
        return new TaskDto(1L, "test title", "test content");
    }*/

}
