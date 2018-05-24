package az.itstep.azjava.testapp.service;

import az.itstep.azjava.testapp.model.Row;

public interface RowService{
    void save(Row row);
    Row findById(Integer id);
    void update(Row row);
    void deleteById(Integer id);
}
