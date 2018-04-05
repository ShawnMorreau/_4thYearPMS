package sysc4806;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long user_id;

    private String email;
    private String firstName;
    private String LastName;

    public User(Long id, String email, String firstName, String lastName){

    }

    //user can join iff

}
