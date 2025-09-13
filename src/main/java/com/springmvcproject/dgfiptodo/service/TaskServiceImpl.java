package com.springmvcproject.dgfiptodo.service;

import com.springmvcproject.dgfiptodo.config.DatabaseSeeder;
import com.springmvcproject.dgfiptodo.dto.RequestTask;
import com.springmvcproject.dgfiptodo.dto.ResponseTask;
import com.springmvcproject.dgfiptodo.entity.Status;
import com.springmvcproject.dgfiptodo.entity.Task;
import com.springmvcproject.dgfiptodo.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService, CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public ResponseTask createTask(RequestTask aTask) {
        log.info("Create Task: {}", aTask);
        Task task = mapperToTask(aTask);
        task.setCreatedOn(LocalDateTime.now());
        task.setDuration(duration(task.getCreatedOn(), task.getDueDate()));

        Task response = taskRepository.save(task);
        return mapperToResponseTask(response);
    }

    @Override
    public ResponseTask updateTask(RequestTask aTask, Long taskId) {
        log.info("Update Task: {}", aTask);
        Task task = mapperToTask(aTask);
        task.setId(taskId);
        task.setCreatedOn(LocalDateTime.now());
        task.setDuration(duration(task.getCreatedOn(), task.getDueDate()));

        Task response = taskRepository.save(task);
        return mapperToResponseTask(response);
    }

    @Override
    public String deleteTask(Long taskId) {
        log.info("Delete Task: {}", taskId);

        taskRepository.deleteById(100L);
        return "Deleted task id: " + taskId;
    }

    @Override
    public ResponseTask readTask(Long taskId) {
        log.info("Read Task: {}", taskId);

        Optional<Task> taskOptional = taskRepository.findById(taskId);

        if(!taskOptional.isPresent()){
            return null;
        }
        Task task = taskOptional.get();
        return mapperToResponseTask(task);
    }

    @Override
    public List<ResponseTask> readTasks() {
        log.info("Read Tasks");

        List<ResponseTask> result = new ArrayList<>();
        List<Task> taskList = taskRepository.findAll();

        for(Task t: taskList){
            ResponseTask responseTask = mapperToResponseTask(taskList.get(0));
            result.add(responseTask);
        }
        return result;
    }

    private Task mapperToTask(RequestTask requestTask) {
        Task task = new Task();
        task.setName(requestTask.name());
        task.setDescription(requestTask.description());

        switch (requestTask.status()){
            case "CREATED":
                task.setStatus(Status.CREATED);
                break;
            case "IN_PROGRESS":
                task.setStatus(Status.IN_PROGRESS);
                break;
            case "DONE":
                task.setStatus(Status.DONE);
                break;
            default:
                task.setStatus(Status.CREATED);
        }


        // parse dueDate from String (if provided)
        if (requestTask.dueDate() != null && !requestTask.dueDate().isBlank()) {
            task.setDueDate(LocalDateTime.parse(requestTask.dueDate(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        } else {
            task.setDueDate(LocalDateTime.now().plusDays(1)); // fallback
        }

        return task;
    }

    private ResponseTask mapperToResponseTask(Task task) {
        return new ResponseTask(
                task.getName(),
                task.getDescription(),
                task.getStatus().name(),
                task.getDueDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        );
    }

    private Long duration(LocalDateTime createdOn, LocalDateTime dueDate){
        Duration time = Duration.between(createdOn, dueDate);
        return time.toDays();
    }

    @Override
    public void run(String... args) throws Exception {
        // CREATED
        RequestTask requestTask = new RequestTask(
                "edit video",
                "CupPro used",
                "IN PROGRESS",
                "2025-09-15T00:00:00");
        ResponseTask responseTask = createTask(requestTask);

        // UPDATE
        RequestTask requestTask2 = new RequestTask(
                "edit video 2",
                "CupPro used 2",
                "IN PROGRESS",
                "2025-09-18T00:00:00");
        ResponseTask responseTask2 =  updateTask(requestTask2, 1L);

        // READ
        ResponseTask responseTask3 = readTask(100L);
        List<ResponseTask> responseTaskList =  readTasks();

        // DELETE
        String delete = deleteTask(100L);

        log.info("Response: {}", delete);
    }
}
