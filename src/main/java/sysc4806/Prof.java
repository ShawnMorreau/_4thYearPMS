package sysc4806;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;
/*
*
 * Created by CraigBook on 2018-03-06.
*/

@Entity
public class Prof {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @JsonIgnore
    @ManyToMany(cascade=CascadeType.ALL, mappedBy="profs")
    private List<Project> projects;

    public Prof() {
        this("", "");
    }

    public Prof(String name, String email) {
        this.name = name;
        this.email = email;
        this.projects = new ArrayList<Project>();
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public void addProject(Project project) {
        this.projects.add(project);
    }
}
