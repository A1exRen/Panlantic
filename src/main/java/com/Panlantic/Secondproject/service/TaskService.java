package com.Panlantic.Secondproject.service;

import com.Panlantic.Secondproject.entity.Task;
import com.Panlantic.Secondproject.entity.TaskDto;
import com.Panlantic.Secondproject.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.Panlantic.Secondproject.utils.MappingUtils.mapToEntity;

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
        Date date = new Date();
        currentTask.setDatechange(date);
        currentTask.setStatus(status);
        taskRepository.save(currentTask);
    }

    public Integer savenewTask(TaskDto TaskDto) {
        Task task  = mapToEntity(TaskDto);
        Date date = new Date();
        task.setDatecreate(date);
        task.setDatechange(null);
        task.setStatus("Open");
        task.setResponsible("not appointed");
        taskRepository.save(task);
        return task.getId();
    }

    public void delete(int id) {

        taskRepository.deleteById(id);
    }
}