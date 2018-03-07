import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by CraigBook on 2018-03-06.
 */
@Entity
public class Student_ {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Double studentId;

    private Project_Repo project_repo;


    private Project_ currentProject = new Project_();
    public Student_(Double studentId, Project_Repo project_repo) {
        this.project_repo = project_repo;
        this.studentId = studentId;
        project_repo.addStudent_(this);
    }

    public Student_(Double id) {
        this.studentId = id;
    }

    public void chooseProject(Project_ prospectiveProject){
        currentProject = prospectiveProject;
    }

    public Project_ getCurrentProject() {
        return currentProject;
    }

    public Double getStudentId() {
        return studentId;
    }

    public void setName(Double studentId) {
        this.studentId = studentId;
    }
}
