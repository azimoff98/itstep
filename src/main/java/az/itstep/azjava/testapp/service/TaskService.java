package az.itstep.azjava.testapp.service;

import az.itstep.azjava.testapp.model.Task;

public interface TaskService {
    void save(Task task);
    Task findById(Integer id);
    void update(Task task);
    void deleteById(Integer id);

}
