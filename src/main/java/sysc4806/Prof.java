package sysc4806;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.*;
/*
*
 * Created by CraigBook on 2018-03-06.
*/

@Entity
public class Prof {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;

    ProjectRepo_ project_repo;
    public Prof() {
        this("");
    }

    public Prof(String name) {
        this.name = name;
    }

    /*public Prof(ProjectRepo_ project_repo) {
        this.project_repo = project_repo;
        project_repo.addProf(this);
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
/*
    public void addProject(String title, String description, String programs, int studentLimit) {
        Project project_ = new Project(title, description, programs, studentLimit);
        project_repo.addProject(project_);
    }

    public void deleteProject(Project project_){
        project_repo.deleteProject(project_);
    }

    public void archiveProject(Project project_){
        project_repo.archiveProject(project_);
    }*/
}
