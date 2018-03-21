package sysc4806;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by CraigBook on 2018-03-21.
 */
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
