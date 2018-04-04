package sysc4806;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:63342"})
@RequestMapping(path="/student")
public class StudentController {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private ProjectRepo projectRepo;

    @GetMapping(path="/add")
    public @ResponseBody String addNewStudent (@RequestParam String name, @RequestParam String email, @RequestParam String program) {
        Student s = new Student();
        s.setName(name);
        s.setEmail(email);
        s.setProgram(program);
        studentRepo.save(s);
        return "Saved Student";
    }

    @GetMapping(path="/getById")
    public @ResponseBody
    Optional<Student> getStudentById (@RequestParam long id) {
        return studentRepo.findById(id);
    }

    @GetMapping(path="/project/add")
    public @ResponseBody
    String addStudentToProject (@RequestParam long studentId, @RequestParam long projectId) {
        Optional<Student> s = studentRepo.findById(studentId);
        Optional<Project> p = projectRepo.findById(projectId);

        if(s.isPresent() && p.isPresent()){
            Student st = s.get();
            st.setProject(p.get());
            studentRepo.save(st);
            return "Project Set";
        }else{
            return "Not found";
        }
    }

    @GetMapping(path="/project/remove")
    public @ResponseBody
    String removeStudentFromProject (@RequestParam long studentId, @RequestParam long projectId) {
        Optional<Student> s = studentRepo.findById(studentId);
        Optional<Project> p = projectRepo.findById(projectId);

        if(s.isPresent() && p.isPresent()){
            Student st = s.get();
            st.setProject(null);
            studentRepo.save(st);
            return "Project Removed";
        }else{
            return "Not found";
        }
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    @GetMapping(path="/deleteAll")
    public @ResponseBody void deleteAllStudents() {
        studentRepo.deleteAll();
    }

    @GetMapping(path="/test")
    public @ResponseBody String returnHello() {
        return "Welcome to the Student Page";
    }
}