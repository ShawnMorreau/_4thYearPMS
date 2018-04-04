package sysc4806;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by CraigBook on 2018-03-13.
 */
@Controller
@RequestMapping(path="/")
public class MainController {


    @GetMapping("/")
    public String homePage(){return "home";}
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

    @GetMapping("/availability" )
    public String AvailabilityPage( ) {
        return "availability";
    }

    @GetMapping("/coordinator" )
    public String CoordinatorPage( ) {
        return "projectCoordinator";
    }

    @GetMapping("/fileSubmission" )
    public String SubmissionPage( ) {
        return "fileSubmission";
    }

}
