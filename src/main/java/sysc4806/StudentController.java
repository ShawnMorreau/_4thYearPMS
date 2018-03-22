package sysc4806;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(path="/test")
public class StudentController {

    @Autowired
    private StudentRepo studentRepo;

    @GetMapping(path="/add")
    public @ResponseBody String addNewStudent (@RequestParam String name, @RequestParam int studentId, @RequestParam String email, @RequestParam String program) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Student s = new Student();
        s.setName(name);
        s.setStudentId(studentId);
        s.setEmail(email);
        s.setProgram(program);
        studentRepo.save(s);
        return "Saved Student";
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Student> getAllStudents() {
        // This returns a JSON or XML with the users
        return studentRepo.findAll();
    }

    @GetMapping(path="/test")
    public @ResponseBody String returnHello() {
        // This returns a JSON or XML with the users
        return "Hello";
    }
}