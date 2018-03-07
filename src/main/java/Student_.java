/**
 * Created by CraigBook on 2018-03-06.
 */
public class Student_ {
    private Project_Repo project_repo;

    private Project_ currentProject = new Project_();
    public Student_(Project_Repo project_repo) {
        this.project_repo = project_repo;
        project_repo.addStudent_(this);
    }

    public void chooseProject(Project_ prospectiveProject){
        currentProject = prospectiveProject;
    }

    public Project_ getCurrentProject() {
        return currentProject;
    }
}
