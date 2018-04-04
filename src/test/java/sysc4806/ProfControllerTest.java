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
 * Created by CraigBook on 2018-03-21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProfControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void addProf() throws Exception {
        String actulProf = this.testRestTemplate.getForObject("/prof/add?name=ProfTest&email=profemail@carleton.ca", String.class);
        String expectedProf = "Saved Prof";
        assertThat(actulProf).isEqualTo(expectedProf);
    }

    @Test
    public void getById() throws Exception {
        String actulProf = this.testRestTemplate.getForObject("/prof/add?name=ProfTest&email=profemail@carleton.ca", String.class);
        String expectedProf = "Saved Prof";
        assertThat(actulProf).isEqualTo(expectedProf);
        Prof p = this.testRestTemplate.getForObject("/prof/getById?id=1", Prof.class);
        assertThat(p).isNotNull();
        assertThat(p.getName()).isNotEmpty();
        assertThat(p.getEmail()).isNotEmpty();
        assertThat(p.getProjects()).isEmpty();
    }

    @Test
    public void addProjectByProf() throws Exception {
        String actulProf = this.testRestTemplate.getForObject("/prof/add?name=ProfTest&email=profemail@carleton.ca", String.class);
        String expectedProf = "Saved Prof";
        assertThat(actulProf).isEqualTo(expectedProf);

        String actulProject = this.testRestTemplate.getForObject("/prof/project/add?profId=1&title=TestProject&description=thisIsATestYouWillPass&programs=SE,CS&maxStudents=2", String.class);
        String expectedProjAdd = "Project added";
        assertThat(actulProject).isNotNull();
        assertThat(actulProject).isNotEmpty();
        assertThat(actulProject).isEqualTo(expectedProjAdd);
    }


    @Test
    public void returnHello() throws Exception {
        String message = "Welcome to the Prof Page";
        String body = this.testRestTemplate.getForObject("/prof/test", String.class);
        assertThat(body).isEqualTo(message);
    }

}