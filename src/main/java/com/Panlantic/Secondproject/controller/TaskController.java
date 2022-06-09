package com.Panlantic.Secondproject.controller;


import com.Panlantic.Secondproject.dto.LogAuth;
import com.Panlantic.Secondproject.entity.Task;
import com.Panlantic.Secondproject.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.Collection;
import java.util.Optional;

import static com.Panlantic.Secondproject.utils.Validate.validateToken;

@Controller
@RequestMapping
public class TaskController {

    @Autowired
    private TaskService taskService;


    @GetMapping("/")
    public ResponseEntity<?> getAll(@RequestParam("token") String token, Model model) {
        try{
            validateToken(token);
            Collection<Task> taskList = taskService.getAll();
            model.addAttribute("taskList", taskList);
            model.addAttribute("taskSize", taskList.size());
            LogAuth log = new LogAuth();
            log.transfer("token is right", "getAll");
            return ResponseEntity.ok(taskList);
        } catch (AccessDeniedException e){
            LogAuth log = new LogAuth();
            log.transfer("wrong token", "getAll");
            return new ResponseEntity<String>("Wrong token", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/find-task-ById") // Поиск по Id
    public ResponseEntity<?> getTaskById(@RequestParam("id") Integer id) {
        Optional<Task> task = taskService.getTask(id);
        return task.isPresent()
                ? ResponseEntity.ok(task)
                : ResponseEntity.ok().body(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/update") // Изменения статуса заявки
    public ResponseEntity<String> update(@RequestParam("id") Integer id, @RequestParam("status") String status, @RequestParam("token") String token) {
        try {
            validateToken(token);//Токен администратора
            String l = taskService.updateStatusById(id, status);
            LogAuth log = new LogAuth();
            log.transfer("token is right", "update");
            return new ResponseEntity<String>(l, HttpStatus.OK);
        } catch (AccessDeniedException e) {
            LogAuth log = new LogAuth();
            log.transfer("wrong token", "update");
            return new ResponseEntity<String>("Wrong token", HttpStatus.FORBIDDEN);
        } catch  (IdNotfoundException e){

        }
    }

    @GetMapping("/delete")// Удаление заявки
    public ResponseEntity<String> deleteTask(@RequestParam("id") Integer id, @RequestParam("token") String token) {
        try {
            validateToken(token);//Токен администратора
            taskService.delete(id);
            LogAuth log = new LogAuth();
            log.transfer("token is right", "delete");
            return new ResponseEntity<String>("Task was deleted", HttpStatus.OK);
        } catch (AccessDeniedException e) {
            LogAuth log = new LogAuth();
            log.transfer("wrong token", "delete");
            return new ResponseEntity<String>("wrong token", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTask(@RequestBody TaskDto TaskDto) {
        Integer k = taskService.createTask(TaskDto);
        return new ResponseEntity<String>("Task id is:" + " " + k, HttpStatus.OK);
    }
}

//*spring path variables + spring postbody
//* отправить exception  в случае неправильного токена