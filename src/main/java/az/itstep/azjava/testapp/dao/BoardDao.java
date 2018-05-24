package az.itstep.azjava.testapp.dao;

import az.itstep.azjava.testapp.model.Board;
import org.springframework.data.repository.CrudRepository;

public interface BoardDao extends CrudRepository<Board, Integer> {
}
