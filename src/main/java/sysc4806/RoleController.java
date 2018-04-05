package sysc4806;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/roles")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;
    @GetMapping(path="/all")
    public @ResponseBody
    Iterable<Role> getAllRoles() {
        // This returns a JSON or XML with the users
        return roleRepository.findAll();
    }
    @GetMapping(path="/add")
    public @ResponseBody String addNewRole ( @RequestParam String role) {

        Role r = new Role();
        //r.setId(id);
        r.setRole(role);
        roleRepository.save(r);
        return "Saved program";
    }
    @GetMapping(path="/delete")
    public @ResponseBody String deleteAllRoles () {

        roleRepository.deleteAll();
        return "Delete All";
    }
}
