package az.itstep.azjava.testapp.controller;

import az.itstep.azjava.testapp.model.Team;
import az.itstep.azjava.testapp.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private TeamService teamService;



    @PostMapping
    public void save(@RequestBody Team team){
        teamService.save(team);
    }

    @GetMapping("/{id}")
    public Team findById(@PathVariable Integer id){
        return teamService.findById(id);
    }

    @PutMapping
    public void update(@RequestBody Team team){
        teamService.update(team);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id){
        teamService.deleteById(id);
    }


    @Autowired
    public void setTeamService(TeamService teamService) {
        this.teamService = teamService;
    }
}
