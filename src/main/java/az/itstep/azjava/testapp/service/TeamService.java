package az.itstep.azjava.testapp.service;

import az.itstep.azjava.testapp.model.Team;

public interface TeamService {
    void save(Team team);
    Team findById(Integer id);
    void update(Team team);
    void deleteById(Integer id);
}
