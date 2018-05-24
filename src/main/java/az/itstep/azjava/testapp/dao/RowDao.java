package az.itstep.azjava.testapp.dao;

import az.itstep.azjava.testapp.model.Row;
import org.springframework.data.repository.CrudRepository;

public interface RowDao extends CrudRepository<Row, Integer> {
}
