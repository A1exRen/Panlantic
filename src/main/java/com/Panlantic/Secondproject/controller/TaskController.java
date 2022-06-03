package com.Panlantic.Secondproject.controller;


import com.Panlantic.Secondproject.entity.Task;
import com.Panlantic.Secondproject.entity.TaskDto;
import com.Panlantic.Secondproject.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping(value = "/")
    public String getAll(Model model) {
        List<Task> taskList = taskService.getAll();
        model.addAttribute("taskList", taskList);
        model.addAttribute("taskSize", taskList.size());
        return "index";
    }

    @GetMapping(path = "/find-task-ById/{id}", params = {"id"})
    public Optional<Task> getTaskById(@PathVariable Integer id) {
        return taskService.getTask(id);
    }

    @PostMapping(path = "/update")
    public String update(@RequestParam("id") Integer id, @RequestParam("name") String status) {
        taskService.updateStatusById(id, status);
        return "redirect:/";
    }

    @RequestMapping("/delete/{id}")
    public String deleteTask(@PathVariable int id) {
        taskService.delete(id);
        return "redirect:/";
    }

    @PostMapping(value ="/add")
    public String addTask(@RequestBody TaskDto TaskDto) {

        return "Task id:"+""+taskService.savenewTask(TaskDto);
    }
}

//*spring path variables + spring postbody