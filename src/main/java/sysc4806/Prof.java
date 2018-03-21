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
public class Prof {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer profId;
    private String name;

    public Prof() {

    }

    public void addProject(String title, String description, String programs, int studentLimit) {
        Project project_ = new Project(title, description, programs, studentLimit);

    }

    public void deleteProject(Project project_){

    }

    public void archiveProject(Project project_){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
