package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TaskMapperTest {

    @Autowired
    TaskMapper taskMapper;

    @Test
    public void testMapToTask(){
        //Given
        TaskDto taskDto = new TaskDto(1L, "Test title", "Test content" );

        //When
        Task mappedTask = taskMapper.mapToTask(taskDto);

        //Then
        Assert.assertEquals(Task.class,mappedTask.getClass() );
        Assert.assertEquals(java.util.Optional.of(1L),java.util.Optional.of(mappedTask.getId()));
        Assert.assertEquals("Test title",mappedTask.getTitle());
        Assert.assertEquals("Test content",mappedTask.getContent());
    }

    @Test
    public void testMapToTaskDto(){
        //Given
        Task task = new Task(1L, "Test title", "Test content" );

        //When
        TaskDto mappedTaskDto = taskMapper.mapToTaskDto(task);

        //Then
        Assert.assertEquals(TaskDto.class,mappedTaskDto.getClass() );
        Assert.assertEquals(java.util.Optional.of(1L),java.util.Optional.of(mappedTaskDto.getId()));
        Assert.assertEquals("Test title",mappedTaskDto.getTitle());
        Assert.assertEquals("Test content",mappedTaskDto.getContent());
    }

    @Test
    public void testMapToTaskDtoList(){
        //Given
        Task task1 = new Task(1L, "Test title", "Test content" );
        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);

        //When
        List<TaskDto> mappedTaskListDto = taskMapper.mapToTaskDtoList(taskList);

        //Then
        Assert.assertEquals(TaskDto.class,mappedTaskListDto.get(0).getClass() );
        Assert.assertEquals(java.util.Optional.of(1L),java.util.Optional.of(mappedTaskListDto.get(0).getId()));
        Assert.assertEquals("Test title",mappedTaskListDto.get(0).getTitle());
        Assert.assertEquals("Test content",mappedTaskListDto.get(0).getContent());
    }
}
