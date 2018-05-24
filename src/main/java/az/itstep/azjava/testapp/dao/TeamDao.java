package az.itstep.azjava.testapp.dao;

import az.itstep.azjava.testapp.model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TeamDao extends CrudRepository<Team, Integer> {


}
