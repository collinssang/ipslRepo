package com.ipsl.taskmanagement.service;

import com.ipsl.taskmanagement.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();

    Task saveTasks(Task task);

    Task updateTasks(Long id, Task task);

    ResponseEntity<?> deleteTask(Long id) throws Exception;

    Page<Task> getPagedTasks(Pageable pageable);
}
