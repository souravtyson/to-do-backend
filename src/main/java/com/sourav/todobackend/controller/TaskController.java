package com.sourav.todobackend.controller;


import com.sourav.todobackend.model.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class TaskController {

    /* TODO: 1. add hot reload concept - done
             2. add logging - done
             3. add exception handling
             4. add swagger
             5. add security
             6. header validation with custom exception handling
             7. Return with ResponseEntity instead of List<Task> or check for better approach
             8. Best way to create a unique id for each task
             9. Add code for health check for the application
             10. Add code for delete task
             11. Add code for update task
             12. Add code for complete/incomplete task
             13. configure actuator
             14. spring profiling - done
             15. spring boot AOP
     */

    ArrayList<Task> tasks = new ArrayList<>();

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getTasks(@RequestHeader HttpHeaders headers) {
        if(tasks.isEmpty()) {
            Task task = new Task("1", "Learn Java", false);
            Task task2 = new Task("2", "Learn Spring", false);
            Task task3 = new Task("3", "Learn React", false);

            tasks.add(task);
            tasks.add(task2);
            tasks.add(task3);
        }
        System.out.println(headers.getContentType());
        log.debug("header found is {}", headers.getContentType());
        return ResponseEntity.ok(tasks);
    }

    @PostMapping("/task/save")
    public ResponseEntity<String> saveTask(@RequestBody Task task, @RequestHeader HttpHeaders headers) {
        if (Objects.nonNull(task) || Objects.nonNull(task.title()) || !task.title().isEmpty()) {
            String id = UUID.randomUUID().toString();
            Task newTask = new Task(id, task.title(), false);
            tasks.add(newTask);
            return ResponseEntity.ok(id);
        } else {
            return ResponseEntity.badRequest().body("Task title is required");
        }
    }

    @PostMapping("/task/{taskId}/status/{taskStatus}")
    public String updateStatus(@PathVariable(value = "taskStatus") String taskStatus, @RequestHeader HttpHeaders headers, @PathVariable(value = "taskId") String taskId) {
        log.debug("header found is {}", headers.getContentType());
        log.debug("task id {}, status changed to {}", taskId, taskStatus);
        return "done";
    }
}
