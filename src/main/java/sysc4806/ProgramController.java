package sysc4806;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:63342"})
@RequestMapping(path="/program")
public class ProgramController {

    @Autowired
    private ProgramRepo programRepo;

    @GetMapping(path="/add")
    public @ResponseBody String addNewProgram (@RequestParam String name, @RequestParam String shortName) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Program p = new Program();
        p.setName(name);
        p.setShortName(shortName);
        programRepo.save(p);
        return "Saved program: " + name;
    }

    @GetMapping(path="/delete")
    public @ResponseBody String deleteAllPrograms () {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        programRepo.deleteAll();
        return "Delete All";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Program> getAllStudents() {
        // This returns a JSON or XML with the users
        return programRepo.findAll();
    }

    @GetMapping(path="/test")
    public @ResponseBody String returnHello() {
        // This returns a JSON or XML with the users
        return "Welcome to the Program Page";
    }
}
