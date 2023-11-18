package constacygraph.api.models;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "graphs")
public class Graphs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dataDia", nullable = false)
    private String dataDia;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "numeroDoDia", nullable = false)
    private Long numeroDoDia;

    @Column(name = "objetivo", nullable = false)
    private String objetivo;


}
