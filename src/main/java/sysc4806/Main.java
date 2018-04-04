package sysc4806;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.*;

@SpringBootApplication
public class Main {
    //it's here man

    public boolean return0() {
        return false;
    }

    /*public static void sysc4806.Main (String [] args) {
        System.out.println("It works");
        //Test commit
    }*/

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    public void run(String... args) throws Exception {
        // Create a User instance
        Role role1 = new Role(1,"ADMIN");
        Role role2 = new Role(2,"PROFESSOR");
        Role role3 = new Role(3,"STUDENT");

        roleRepository.save(role1);
        roleRepository.save(role2);
        roleRepository.save(role3);
    }
}

