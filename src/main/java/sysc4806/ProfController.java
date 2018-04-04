package sysc4806;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:63342"})
@RequestMapping(path="/prof")
public class ProfController {

    @Autowired
    private ProfRepo profRepo;

    @Autowired
    private ProjectRepo projectRepo;

    @GetMapping(path="/add")
    public @ResponseBody String addNewStudent (@RequestParam String name, @RequestParam String email) {
        Prof s = new Prof();
        s.setName(name);
        s.setEmail(email);
        profRepo.save(s);
        return "Saved Prof";
    }

    @GetMapping(path="/getById")
    public @ResponseBody
    Optional<Prof> getProfById (@RequestParam long id) {
        return profRepo.findById(id);
    }

    @GetMapping(path="/project/add")
    public @ResponseBody String addNewProject (@RequestParam Long profId, @RequestParam String title, @RequestParam String description, @RequestParam String programs, @RequestParam int maxStudents) {
        Optional<Prof> op = profRepo.findById(profId);
        System.out.println("Number of Profs: " + profRepo.count());
        profRepo.findAll().forEach(prof -> System.out.println(prof.getId()));
        System.out.println("Profs: " + profRepo.findAll());
        if(op.isPresent()){
            Prof prof = op.get();
            Project p = new Project(title, description, programs, maxStudents);
            p.addProf(prof);
            prof.addProject(p);
            projectRepo.save(p);
            return "Project added";
        }else{

            return "Prof not found";
        }
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Prof> getAllProfs() {
        return profRepo.findAll();
    }

    @GetMapping(path="/deleteAll")
    public @ResponseBody String deleteAllProjects () {
        profRepo.deleteAll();
//        projectRepo.findAll().forEach(project -> projectRepo.delete(project));
        return "Delete All";
    }

    @GetMapping(path="/test")
    public @ResponseBody String returnHello() {
        return "Welcome to the Prof Page";
    }
}
