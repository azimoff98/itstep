package az.itstep.azjava.testapp.service;

import az.itstep.azjava.testapp.model.Board;

public interface BoardService {
    void save(Board board);
    Board findById(Integer id);
    void update(Board board);
    void deleteById(Integer id);
}
