package az.itstep.azjava.testapp.controller;


import az.itstep.azjava.testapp.model.Board;
import az.itstep.azjava.testapp.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

    private BoardService boardService;


    @PostMapping
    public void save(@RequestBody Board board){
        boardService.save(board);
    }

    @GetMapping("/{id}")
    public Board findById(@PathVariable Integer id){
        return boardService.findById(id);
    }

    @PutMapping
    public void update(@RequestBody  Board board){
        boardService.update(board);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id){
        boardService.deleteById(id);
    }


    @Autowired
    public void setBoardService(BoardService boardService) {
        this.boardService = boardService;
    }
}
