package com.searchink.sample.controllers;

import com.searchink.sample.domain.Task;
import com.searchink.sample.domain.TaskRepository;
import com.searchink.sample.domain.TaskStatus;
import com.searchink.sample.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/tasks")
class TaskController {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @RequestMapping("/{id}")
    public TaskDetailResponse detailOf(@PathVariable UUID id) {
        Task task = taskRepository.findOne(id);

        if (task == null) throw new TaskNotFoundException();

        return new TaskDetailResponse(
                task.getId(), task.getCreatedAt(),
                task.getTitle(), task.getDescription(),
                task.getDueDate(), task.getPriority());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        taskRepository.delete(id);
    }

    @RequestMapping
    public Iterable<TaskResponse> list() {

        return StreamSupport.stream(taskRepository.findAll().spliterator(), false)
                .map((t) -> new TaskResponse(t.getId(), t.getTitle(),
                        t.getDescription(), t.getDueDate(), t.getPriority(), t.getStatus()))
                .collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDetailResponse create(@Valid @RequestBody AddTaskRequest request) {
        Task task = new Task(request.title, request.description, request.dueAt, request.priority);

        taskRepository.save(task);

        return new TaskDetailResponse(task.getId(), task.getCreatedAt(),
                task.getTitle(), task.getDescription(), task.getDueDate(), task.getPriority());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void update(@PathVariable UUID id, @Valid @RequestBody UpdateTaskDetailRequest detail) {
        Task task = taskRepository.findOne(id);

        if (task == null) throw new TaskNotFoundException();

        task.setDescription(detail.description);
        task.setTitle(detail.title);
        task.setDueDate(detail.dueAt);
        task.setPriority(detail.priority);

        taskRepository.save(task);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delay(@PathVariable UUID id, @RequestBody TaskChangeStatusRequest request) {
        if (request.status == TaskStatus.DONE || request.status == TaskStatus.RUNNING)
            throw new InvalidStatusChange("Task status cannot be set to running or done");

        Task task = taskRepository.findOne(id);

        if (task == null) throw new TaskNotFoundException();

        if (task.getStatus() == TaskStatus.DONE || task.getStatus() == TaskStatus.RUNNING)
            throw new InvalidStatusChange("Task is already running or executed");

        if (request.status == TaskStatus.DELAYED && task.getStatus() == TaskStatus.DELAYED)
            throw new InvalidStatusChange("Task is already delayed");

        if (request.status == TaskStatus.WAITING && task.getStatus() == TaskStatus.WAITING)
            throw new InvalidStatusChange("Task is already waiting for execution");

        task.setStatus(request.status);
        taskRepository.save(task);
    }
}
