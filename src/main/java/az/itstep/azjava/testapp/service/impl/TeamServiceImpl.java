package az.itstep.azjava.testapp.service.impl;

import az.itstep.azjava.testapp.dao.TeamDao;
import az.itstep.azjava.testapp.model.Team;
import az.itstep.azjava.testapp.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TeamServiceImpl implements TeamService {

    private TeamDao teamDao;

    @Override
    public void save(Team team) {
        if(Objects.isNull(team.getName())){
            throw new RuntimeException("BOARD NAME CANNOT BE NULL!");
        }
        teamDao.save(team);
    }

    @Override
    public Team findById(Integer id) {
        if(!teamDao.findById(id).isPresent()){
            throw new RuntimeException("WRONG ID");
        }

        return teamDao.findById(id).get();
    }

    @Override
    public void update(Team team) {
        teamDao.save(team);
    }

    @Override
    public void deleteById(Integer id) {
        if(!teamDao.findById(id).isPresent()){
            throw new RuntimeException("WRONG ID");
        }

        teamDao.deleteById(id);
    }


    @Autowired
    public void setTeamDao(TeamDao teamDao) {
        this.teamDao = teamDao;
    }
}
