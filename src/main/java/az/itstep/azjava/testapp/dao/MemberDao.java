package az.itstep.azjava.testapp.dao;

import az.itstep.azjava.testapp.model.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MemberDao extends CrudRepository<Member, Integer> {
    Optional<Member> findByUsername(String username);
}
