import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/test")
public class MainController {

    @Autowired
    private Student_Repo studentRepo;

    @GetMapping(path="/add")
    public @ResponseBody String addNewStudent (@RequestParam Double id) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Student_ s = new Student_(id);
        studentRepo.save(s);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Student_> getAllStudents() {
        // This returns a JSON or XML with the users
        return studentRepo.findAll();
    }

}
