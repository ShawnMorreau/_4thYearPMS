package sysc4806;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(path="/prof")
public class profController {

    @GetMapping(path="/test")
    public @ResponseBody String returnHello() {

        return "Hello";
    }

    @RequestMapping(value = "/select")
    public String redirect() {
        return "redirect:/src/index.html";
    }



}
