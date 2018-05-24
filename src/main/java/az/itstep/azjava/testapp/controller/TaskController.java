package az.itstep.azjava.testapp.controller;

import az.itstep.azjava.testapp.model.Task;
import az.itstep.azjava.testapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private TaskService taskService;




    @PostMapping
    public void save(@RequestBody Task task){
        taskService.save(task);
    }

    @GetMapping("/{id}")
    public Task findById(@PathVariable Integer id){
        return taskService.findById(id);
    }

    @PutMapping
    public void update(@RequestBody Task task){
        taskService.update(task);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id){
        taskService.deleteById(id);
    }




    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }
}
