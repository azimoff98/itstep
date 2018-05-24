package az.itstep.azjava.testapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "tr_board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;



    @OneToMany(mappedBy = "board", orphanRemoval = true)
    private List<Row> rowList;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;



}
