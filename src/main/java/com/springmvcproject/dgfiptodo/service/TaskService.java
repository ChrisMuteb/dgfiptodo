package com.springmvcproject.dgfiptodo.service;

import com.springmvcproject.dgfiptodo.dto.RequestTask;
import com.springmvcproject.dgfiptodo.dto.ResponseTask;

import java.util.List;

public interface TaskService {
    ResponseTask createTask(RequestTask aTask);
    ResponseTask updateTask(RequestTask aTask, Long taskId);
    ResponseTask updateTask( Long taskId);
    String deleteTask( Long taskId);
    ResponseTask readTask( Long taskId);
    List<ResponseTask> readTasks();
}
