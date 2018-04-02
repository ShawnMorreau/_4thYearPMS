package sysc4806;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by CraigBook on 2018-03-21.
 */
@Controller
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:63342"})
@RequestMapping(path="/proj_coord")
public class ProjectCoordinatorController {
    @Autowired
    private ProjectCoordinatorRepo projectCoordinatorRepo;

    @GetMapping(path="/add")
    public @ResponseBody
    String addNewProgram (@RequestParam String name) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        ProjectCoordinator p = new ProjectCoordinator();
        p.setName(name);
        projectCoordinatorRepo.save(p);
        return "Saved projectCoordinator: " + name;
    }

    @GetMapping(path="/delete")
    public @ResponseBody String deleteAllPrograms () {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        projectCoordinatorRepo.deleteAll();
        return "Delete All";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<ProjectCoordinator> getAllStudents() {
        // This returns a JSON or XML with the users
        return projectCoordinatorRepo.findAll();
    }

    @GetMapping(path="/test")
    public @ResponseBody String returnHello() {
        // This returns a JSON or XML with the users
        return "Welcome to the Project Coordinator Page";
    }
}
