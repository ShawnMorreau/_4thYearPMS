package sysc4806;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;

@Controller
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:63342"})
@RequestMapping(path="/project")
public class ProjectController {

    @Autowired
    private ProjectRepo projectRepo;

    @GetMapping(path="/add")
    public @ResponseBody String addNewProject (@RequestParam String title, @RequestParam String description, @RequestParam String programs, @RequestParam int maxStudents) {
        Project p = new Project();
        p.setTitle(title);
        p.setDescription(description);
        p.setStudentLimit(maxStudents);
        p.setPrograms(programs);
        projectRepo.save(p);
        return "Saved project";
    }

    @GetMapping(path="/getById")
    public @ResponseBody
    Optional<Project> getProjectById (@RequestParam long id) {
        return projectRepo.findById(id);
    }

    @GetMapping(path="/getStudents")
    public @ResponseBody
    List<Student> getStudents (@RequestParam long id) {
        Optional<Project> op =  projectRepo.findById(id);
        if(op.isPresent()){
            Project p = op.get();
            return p.getStudents();
        }else{
            return null;
        }
    }

    @GetMapping(path="/deleteById")
    public @ResponseBody String deleteProjectById (@RequestParam long id) {
        projectRepo.deleteById(id);
        return "DeleteId";
    }

    @GetMapping(path="/deleteAll")
    public @ResponseBody String deleteAllProjects () {
        projectRepo.findAll().forEach(project -> projectRepo.delete(project));
        return "Delete All";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Project> getAllStudents() {
        return projectRepo.findAll();
    }

    @GetMapping(path="/test")
    public @ResponseBody String returnHello() {
        return "Welcome to Project Page";
    }
}