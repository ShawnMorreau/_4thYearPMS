package sysc4806;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by CraigBook on 2018-03-06.
 */
@Entity
public class Student {

    @Id
    private Integer studentId;

    private String name;
    private String email;
    private String program;

    //    private ProjectRepo project_repo;
//
//
//    private Project currentProject = new Project();
//    public Student(Double studentId, ProjectRepo project_repo) {
//        this.project_repo = project_repo;
//        this.studentId = studentId;
//        project_repo.addStudent_(this);
//    }
//
//    public Student(Double id) {
//        this.studentId = id;
//    }
    public Student(){
        this("",0,"","");
    }

    public Student(String name, Integer studentId, String email, String program){
        this.name = name;
        this.studentId = studentId;
        this.email = email;
        this.program = program;
    }

//    public void chooseProject(Project prospectiveProject){
//        currentProject = prospectiveProject;
//    }
//
//    public Project getCurrentProject() {
//        return currentProject;
//    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
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

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }
}