import java.util.List;

/**
 * Created by CraigBook on 2018-03-06.
 */
public class ProjectCoordinator_ {
    public List<Student_> projectlessStudents;
    public Project_Repo project_repo;
    public ProjectCoordinator_(Project_Repo project_repo){
        this.project_repo = project_repo;
        checkRogueStudents();
    }

    public void checkRogueStudents(){
        for (Student_ student_: project_repo.getStudent_s()){
            if(!student_.getCurrentProject().validProject()){
                projectlessStudents.add(student_);
            }
        }
    }

    public void notifyStudents(String message) {
        
    }
}
