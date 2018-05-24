package az.itstep.azjava.testapp.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "tr_team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;


    @OneToMany(mappedBy = "team")
    private List<Member> memberList;


    @OneToMany(mappedBy = "team")
    private List<Board> boardList;

}
