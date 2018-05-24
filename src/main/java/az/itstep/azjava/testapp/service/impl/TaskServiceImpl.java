package az.itstep.azjava.testapp.service.impl;

import az.itstep.azjava.testapp.dao.TaskDao;
import az.itstep.azjava.testapp.model.Task;
import az.itstep.azjava.testapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class TaskServiceImpl implements TaskService {

    private TaskDao taskDao;

    @Override
    public void save(Task task) {
        if(Objects.isNull(task.getText())){
            throw new RuntimeException("BOARD NAME CANNOT BE NULL!");
        }
        taskDao.save(task);
    }

    @Override
    public Task findById(Integer id) {
        if(!taskDao.findById(id).isPresent()){
            throw new RuntimeException("WRONG ID");
        }

        return taskDao.findById(id).get();
    }

    @Override
    public void update(Task task) {
        taskDao.save(task);
    }

    @Override
    public void deleteById(Integer id) {
        if(!taskDao.findById(id).isPresent()){
            throw new RuntimeException("WRONG ID");
        }

        taskDao.deleteById(id);
    }


    @Autowired
    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }
}
