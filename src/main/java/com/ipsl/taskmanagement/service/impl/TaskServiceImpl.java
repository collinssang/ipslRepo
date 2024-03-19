package com.ipsl.taskmanagement.service.impl;

import com.ipsl.taskmanagement.helpers.ErrorResponses;
import com.ipsl.taskmanagement.model.Task;
import com.ipsl.taskmanagement.repository.TaskRepository;
import com.ipsl.taskmanagement.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    @Cacheable("tasks")
    @Transactional
    public synchronized List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    @Transactional
    public synchronized Task saveTasks(Task task) {
        return taskRepository.saveAndFlush(task);
    }

    @Override
    @Transactional
    public synchronized Task updateTasks(Long id, Task task) {
        Task task1 = taskRepository.findById(id).orElse(null);
        if (task1 != null) {
            task.setId(task1.getId());
            taskRepository.save(task);
            return task;
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public synchronized ResponseEntity<?> deleteTask(Long id) throws Exception {
        Task task1 = taskRepository.findById(id).orElse(null);
        if (task1 != null) {
            taskRepository.delete(task1);
            return ResponseEntity.status(HttpStatus.CREATED).body(task1);
        } else {
            ErrorResponses errorResponses = new ErrorResponses(HttpStatus.NO_CONTENT.value(), "Task Not Found");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(errorResponses);
        }


    }

    @Override
    @Cacheable("pagedTasks")
    @Transactional
    public synchronized Page<Task> getPagedTasks(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }
}
