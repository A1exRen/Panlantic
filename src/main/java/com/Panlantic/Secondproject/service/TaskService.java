package com.Panlantic.Secondproject.service;

import com.Panlantic.Secondproject.entity.Task;
import com.Panlantic.Secondproject.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAll() {
        return taskRepository.findAll(Sort.by(Sort.Order.asc("datecreate")));
    }

    public Optional<Task> getTask(int id) {
        return taskRepository.findById(id);
    }

    public void updateStatusById(Integer id, String status ) {
        Optional<Task> currentTaskOptional = taskRepository.findById(id);
        Task currentTask = currentTaskOptional.get();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        currentTask.setDatechange(formatter);
        currentTask.setStatus(status);
        taskRepository.save(currentTask);
    }

    public void save(Task task) {
        taskRepository.save(task);
    }

    public void delete(int id) {
        taskRepository.deleteById(id);
    }
}