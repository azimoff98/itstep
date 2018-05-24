package az.itstep.azjava.testapp.service.impl;


import az.itstep.azjava.testapp.dao.BoardDao;
import az.itstep.azjava.testapp.model.Board;
import az.itstep.azjava.testapp.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BoardServiceImpl implements BoardService {


    private BoardDao boardDao;

    @Override
    public void save(Board board){
        if(Objects.isNull(board.getName())){
            throw new RuntimeException("BOARD NAME CANNOT BE NULL!");
        }
        boardDao.save(board);
    }
    @Override
    public Board findById(Integer id){
        if(!boardDao.findById(id).isPresent()){
            throw new RuntimeException("WRONG ID");
        }

        return boardDao.findById(id).get();
    }
    @Override
    public void update(Board board){
        boardDao.save(board);
    }

    @Override
    public void deleteById(Integer id) {
        if(!boardDao.findById(id).isPresent()){
            throw new RuntimeException("WRONG ID");
        }
        boardDao.deleteById(id);
    }


    @Autowired
    public void setBoardDao(BoardDao boardDao) {
        this.boardDao = boardDao;
    }
}
