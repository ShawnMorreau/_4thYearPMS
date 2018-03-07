import java.util.ArrayList;
import java.util.List;

/**
 * Created by CraigBook on 2018-03-06.
 */
public class Project_ {
    private String description;
    private List<String> programs;
    private int studentLimit;
    public Project_(){
//        this();
        this("", new ArrayList<>(), 0);
//        this
    }

    public Project_(String description, List<String> programs, int studentLimit) {
        this.description = description;
        this.programs = programs;
        this.studentLimit = 0;
    }

    public boolean validProject(){
        return (description != "" && programs.size()!=0 && studentLimit!=0);
    }
}
