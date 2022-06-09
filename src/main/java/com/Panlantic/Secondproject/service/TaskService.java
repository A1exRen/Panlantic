package com.Panlantic.Secondproject.service;

import com.Panlantic.Secondproject.entity.Task;
import com.Panlantic.Secondproject.dto.TaskDto;
import com.Panlantic.Secondproject.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import static com.Panlantic.Secondproject.utils.MappingUtils.mapToEntity;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Collection<Task> getAll() {
        return taskRepository.findAll(Sort.by(Sort.Order.asc("datecreate")));
    }

    public Optional<Task> getTask(int id) {
        return taskRepository.findById(id);
    }

    public String updateStatusById(Integer id, String status ) throws  {
        Optional<Task> currentTaskOptional = taskRepository.findById(id);
        Task currentTask = currentTaskOptional.get();
        Date date = new Date();
        if (currentTask.getStatus().equals("Open") & status.equals("In work")) {
            currentTask.setDatechange(date);
            currentTask.setStatus(status);
            currentTask.setResponsible("Administrator");
            taskRepository.save(currentTask);
            return "Status of task:"+""+status;
        }else {
            if ((currentTask.getStatus().equals("In work") & status.equals("Closed"))){
                currentTask.setDatechange(date);
                currentTask.setStatus(status);
                currentTask.setResponsible("Administrator");
                taskRepository.save(currentTask);
                return "Status of task:"+""+status;
            }
        }
        return "Wrong status";
    }

    public Integer createTask(TaskDto TaskDto) {
        Task task  = mapToEntity(TaskDto);
        Date date = new Date();
        task.setDatecreate(date); //Исправить регистр слов
        task.setStatus("Open");
        task.setResponsible("not appointed");
        taskRepository.save(task);
        return task.getId();
    }

    public void delete(int id) {

        taskRepository.deleteById(id);
    }
}