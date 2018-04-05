package sysc4806;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * Created by CraigBook on 2018-03-06.
 */
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long studentId;

    @NotEmpty
    private String name;

    @NotEmpty
    private String email;

    @NotEmpty
    private String program;

    @JsonIgnore
    @ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="projectId")
    private Project project;

    public Student(){
        this("",0,"","");
    }

    public Student(String name, long studentId, String email, String program){
        this.name = name;
        this.studentId = studentId;
        this.email = email;
        this.program = program;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        String project = "-";
        if(getProject() != null){
            project = getProject().getId().toString();
        }
        return String.format("Student Id:%d Name:%s Email:%s Program:%s Project:%s", getStudentId(), getName(), getEmail(), getProgram(), project);
    }
}