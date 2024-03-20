package com.ipsl.taskmanagement.controller;

import com.ipsl.taskmanagement.helpers.ErrorResponses;
import com.ipsl.taskmanagement.model.Task;
import com.ipsl.taskmanagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@Validated
public class TaskController {
    private final TaskService taskService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public TaskController(TaskService taskService, AuthenticationManager authenticationManager) {
        this.taskService = taskService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping
    public ResponseEntity<?> getAllTasks() {

        List<Task> taskList = taskService.getAllTasks();
        if (taskList.isEmpty()) {
            ErrorResponses errorResponses = new ErrorResponses(HttpStatus.NO_CONTENT.value(), "No Content found");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(errorResponses);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(taskList);
        }
    }

    @GetMapping("/page")
    public ResponseEntity<?> getPagedTasks(Pageable pageable) {
        Page<Task> taskPage = taskService.getPagedTasks(pageable);
        if (taskPage.isEmpty()) {
            ErrorResponses errorResponses = new ErrorResponses(HttpStatus.NO_CONTENT.value(), "No Content found");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(errorResponses);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(taskPage);
        }
    }

    @PostMapping
    public ResponseEntity<?> saveTasks(@RequestBody Task task) {

        Task task1 = taskService.saveTasks(task);
        if (task1 == null) {
            ErrorResponses errorResponses = new ErrorResponses(HttpStatus.BAD_REQUEST.value(), "Task Not Saved");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(errorResponses);
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(task1);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTasks(@PathVariable("id") Long id, @RequestBody Task task) {
        Task task1 = taskService.updateTasks(id, task);
        if (task1 == null) {
            ErrorResponses errorResponses = new ErrorResponses(HttpStatus.NO_CONTENT.value(), "Task Not Found");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(errorResponses);
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(task1);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable("id") Long id) throws Exception {
        return taskService.deleteTask(id);
    }
}
