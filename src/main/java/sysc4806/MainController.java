package sysc4806;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by CraigBook on 2018-03-13.
 */
@Controller
@RequestMapping(path="/")
public class MainController {

    @GetMapping(path="/")
    public @ResponseBody
    String returnHello() {
        // This returns a JSON or XML with the users
        return "Welcome to Project Management System";
    }

    @GetMapping("/index")
    public String indexPage( ) {
        return "index";
    }

    @GetMapping("/student")
    public String studentPage( ) {
        return "studentHomepage";
    }

    @GetMapping("/project" )
    public String projectPage( ) {
        return "projectSelection";
    }


}
