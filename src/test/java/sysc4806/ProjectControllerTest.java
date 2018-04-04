package sysc4806;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by CraigBook on 2018-03-20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProjectControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void addNewProject() throws Exception {
        addValidProject();
    }

    @Test
    public void getProjectById() throws Exception {
        addValidProject();
        Project p = this.testRestTemplate.getForObject("/project/getById?id=1", Project.class);
        assertThat(p).isNotNull();
        assertThat(p.getTitle()).isNotEmpty();
        assertThat(p.getDescription()).isNotEmpty();
        assertThat(p.getPrograms()).isNotEmpty();
        assertThat(p.getStudentLimit()).isGreaterThan(0);
        assertThat(p.getStudents()).isNotNull().isEmpty();
    }

    @Test
    public void returnHello() throws Exception {
            String message = "Welcome to Project Page";
            String body = this.testRestTemplate.getForObject("/project/test", String.class);
            assertThat(body).isEqualTo(message);
    }

    private void addValidProject() throws Exception {
        //add Prof
        String actulProf = this.testRestTemplate.getForObject("/prof/add?name=ProfTest&email=profemail@carleton.ca", String.class);
        assertThat(actulProf).isEqualTo("Saved Prof");
        //prof adds Project
        String actulProject = this.testRestTemplate.getForObject("/prof/project/add?profId=1&title=TestProject&description=thisIsATestYouWillPass&programs=SE,CS&maxStudents=2", String.class);
        assertThat(actulProject).isEqualTo("Project added");
    }
}