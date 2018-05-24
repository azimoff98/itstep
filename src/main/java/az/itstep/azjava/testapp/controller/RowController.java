package az.itstep.azjava.testapp.controller;


import az.itstep.azjava.testapp.model.Row;
import az.itstep.azjava.testapp.service.RowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rows")
public class RowController {

    private RowService rowService;



    @PostMapping
    public void save(@RequestBody Row row){
        rowService.save(row);
    }

    @GetMapping("/{id}")
    public Row findById(@PathVariable Integer id){
        return rowService.findById(id);
    }

    @PutMapping
    public void update(@RequestBody Row row){
        rowService.update(row);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id){
        rowService.deleteById(id);
    }


    @Autowired
    public void setRowService(RowService rowService) {
        this.rowService = rowService;
    }
}
