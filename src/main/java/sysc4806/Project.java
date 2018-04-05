package sysc4806;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/*
*
 * Created by CraigBook on 2018-03-06.
*/

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String programs;
    private int studentLimit;

    @OneToMany(targetEntity=Student.class, mappedBy="project", fetch=FetchType.LAZY)
    private List<Student> students;

    @ManyToMany(cascade=CascadeType.ALL, mappedBy = "projects")
    @NotEmpty
    private List<Prof> profs;

    public Project(){
        this("","", "", 0);
    }

    public Project(String title, String description, String programs, int studentLimit) {
        this(title, description, programs, studentLimit, new ArrayList<Prof>(), new ArrayList<Student>());
    }

    public Project(String title, String description, String programs, int studentLimit, List<Prof> profs, List<Student> students) {
        this.title = title;
        this.description = description;
        this.programs = programs;
        this.studentLimit = studentLimit;
        this.profs = profs;
        this.students = students;
    }

    public boolean validProject(){
        return (title != "" && description != "" && programs != "" && studentLimit!=0);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrograms() {
        return programs;
    }

    public void setPrograms(String programs) {
        this.programs = programs;
    }

    public int getStudentLimit() {
        return studentLimit;
    }

    public void setStudentLimit(int studentLimit) {
        this.studentLimit = studentLimit;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) throws Exception {
        if(getStudents().size() < getStudentLimit()){
            this.students.add(student);
        } else {
            throw new Exception("Project is full");
        }
    }

    public List<Prof> getProfs() {
        return profs;
    }

    public void setProfs(List<Prof> profs) {
        this.profs = profs;
    }

    public void addProf(Prof prof) {
        this.profs.add(prof);
    }
}
