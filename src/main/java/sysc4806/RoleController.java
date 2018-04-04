package sysc4806;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;
    @GetMapping(path="/roles")
    public @ResponseBody
    Iterable<Role> getAllRoles() {
        // This returns a JSON or XML with the users
        return roleRepository.findAll();
    }
}
