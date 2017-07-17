package OTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by azhilin on 26.01.2017.
 */
@Entity(name = "managers")
public class MyManager {
    @Id
    @GeneratedValue
    int id;
    @Column(name = "name")
    String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    List<MyClient> clients = new ArrayList<MyClient>();
}
