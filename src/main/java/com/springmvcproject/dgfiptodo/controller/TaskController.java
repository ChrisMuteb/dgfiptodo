package com.springmvcproject.dgfiptodo.controller;

import com.springmvcproject.dgfiptodo.dto.RequestTask;
import com.springmvcproject.dgfiptodo.dto.ResponseTask;
import com.springmvcproject.dgfiptodo.entity.Status;
import com.springmvcproject.dgfiptodo.service.TaskService;
import com.springmvcproject.dgfiptodo.service.TaskServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskController {

    private static final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String loadTasks(Model model){
        // 1. Get data from the service layer
        List<ResponseTask> tasks = taskService.readTasks();
        for(ResponseTask rt: tasks)
            log.info("Task name: {}", rt.name());

        // 2. Add the data to the Model
        // The key "tasks" is what you will use to access the data in your HTML
        model.addAttribute("tasks", tasks);

        // 3. Return the name of the view (the HTML file without the .html extension)
        return "todo";
    }

    @PostMapping("/create-task")
    public String createTask(@RequestParam("taskName") String taskName){
        log.info("Input: {}", taskName);
        RequestTask task = new RequestTask(
                taskName,
                "description", "IN_PROGRESS", ""
        );
        // The taskName variable will automatically contain the value from your form input
        taskService.createTask(task);

        // Redirect back to the main task list page after adding the task
        return "redirect:/";
    }

    @GetMapping("/delete-task/{id}")
    public String deleteTask(@PathVariable("id") Long idTask){
        // The idTask variable automatically receives the ID from the URL

        // Log the ID being deleted (optional, but helpful for debugging)
        log.info("Deleting task with ID: {}", idTask);

        taskService.deleteTask(idTask);

        // Redirect back to the main task list page
        return "redirect:/";
    }

    @GetMapping("/update-task/{id}")
    public String updateTask(@PathVariable("id") Long idTask){
        log.info("Deleting task with ID: {}", idTask);

        taskService.updateTask(idTask);
        return "redirect:/";
    }
}
