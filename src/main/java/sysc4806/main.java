package sysc4806;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;

@SpringBootApplication
public class main {
    //it's here man

    public boolean return0 () {
        return false;
    }

    /*public static void sysc4806.main (String [] args) {
        System.out.println("It works");
        //Test commit
    }*/

    public static void main(String[] args) {
        SpringApplication.run(main.class, args);
    }

}
