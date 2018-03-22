package sysc4806;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by CraigBook on 2018-03-13.
 */
@Controller
@RequestMapping(path="/home")
public class MainController {



    @GetMapping("/index")
    public String indexPage( ) {
        return "index";
    }

    @GetMapping("/student")
    public String studentPage( ) {
        return "studentHomepage";
    }

    @GetMapping("/select" )
    public String projectPage( ) {
        return "projectSelection";
    }


}
