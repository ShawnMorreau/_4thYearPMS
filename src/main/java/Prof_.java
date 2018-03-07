/**
 * Created by CraigBook on 2018-03-06.
 */
public class Prof_ {
    Project_Repo project_repo;
    public Prof_() {

    }

    public Prof_ (Project_Repo project_repo) {
        this.project_repo = project_repo;
        project_repo.addProf(this);
    }

    public void addProject(String description, String [] programs, int studentLimit) {
        Project_ project_ = new Project_(description, programs, studentLimit);
        project_repo.addProject(project_);
    }

    public void deleteProject(Project_ project_){
        project_repo.deleteProject(project_);
    }

    public void archiveProject(Project_ project_){
        project_repo.archiveProject(project_);
    }
}
