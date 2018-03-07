import java.util.ArrayList;
import java.util.List;

/**
 * Created by CraigBook on 2018-03-06.
 */
public class Project_Repo {
    private List<Prof_> prof_s = new ArrayList<>();

    public List<Prof_> getProf_s() {
        return prof_s;
    }

    public List<Student_> getStudent_s() {
        return student_s;
    }

    private List<Student_> student_s = new ArrayList<>();

    private List <Project_> archivedProjects = new ArrayList<>();

    public List<Project_> getArchivedProjects() {
        return archivedProjects;
    }

    public List<Project_> getProject_s() {
        return project_s;
    }

    private List <Project_> project_s = new ArrayList<>();
    public void addProject(Project_ project_) {
        project_s.add(project_);
    }

    public void deleteProject(Project_ project_) {
        project_s.remove(project_);
    }

    public void archiveProject(Project_ project_) {
        archivedProjects.add(project_);
        deleteProject(project_);
    }

    public void addProf(Prof_ prof_) {
        prof_s.add(prof_);
    }

    public void addStudent_(Student_ student_) {
        student_s.add(student_);
    }
}
