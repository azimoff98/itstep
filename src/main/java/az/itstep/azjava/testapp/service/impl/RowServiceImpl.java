package az.itstep.azjava.testapp.service.impl;

import az.itstep.azjava.testapp.dao.RowDao;
import az.itstep.azjava.testapp.model.Row;
import az.itstep.azjava.testapp.service.RowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RowServiceImpl implements RowService {

    private RowDao rowDao;

    @Override
    public void save(Row row) {
        if(Objects.isNull(row.getName())){
            throw new RuntimeException("BOARD NAME CANNOT BE NULL!");
        }
        rowDao.save(row);
    }

    @Override
    public Row findById(Integer id) {
        if(!rowDao.findById(id).isPresent()){
            throw new RuntimeException("WRONG ID");
        }

        return rowDao.findById(id).get();
    }

    @Override
    public void update(Row row) {
        rowDao.save(row);
    }

    @Override
    public void deleteById(Integer id) {
        if(!rowDao.findById(id).isPresent()){
            throw new RuntimeException("WRONG ID");
        }

        rowDao.deleteById(id);
    }



    @Autowired
    public void setRowDao(RowDao rowDao) {
        this.rowDao = rowDao;
    }
}
