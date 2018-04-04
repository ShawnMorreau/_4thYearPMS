package sysc4806;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private int id;

    @Column(name = "role")
    private String role;

    public Role(){
        this(-1,"");
    }

    public Role(int value, String description) {
        this.id = value;
        this.role = description;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getRole(){
        return role;
    }
    public void setRole(String role){
        this.role = role;
    }


}
