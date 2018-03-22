package sysc4806;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(path="/prof")
public class profController {

    @GetMapping(path="/prof")
    public @ResponseBody String returnHello() {

        return "Hello";
    }





}
