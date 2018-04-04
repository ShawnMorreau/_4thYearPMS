package sysc4806;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
*
 * Created by CraigBook on 2018-03-06.
*/

@Entity
public class ProjectCoordinator {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer pcId;
    private String name;

//    public List<Student> projectlessStudents;

    public ProjectCoordinator(){

    }

    public void checkRogueStudents(){

    }

    public void notifyStudents(String message) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
