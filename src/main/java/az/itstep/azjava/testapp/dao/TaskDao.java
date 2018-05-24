package az.itstep.azjava.testapp.dao;

import az.itstep.azjava.testapp.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDao extends CrudRepository<Task, Integer > {
}
