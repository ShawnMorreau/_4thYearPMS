package sysc4806;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by CraigBook on 2018-03-20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProjectControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    private static final String addNewProjectURL = "/project/add";

    private static final String getProjectByIdURL= "/project/getById";
    private static final String getStudentsURL= "/project/getStudents";
    private static final String getAllProjectsURL= "/project/all";

    private static final String deleteProjectByIdURL= "/project/deleteById";
    private static final String deleteAllProjectsURL= "/project/deleteAll";
    private static final String testURL= "/project/test";

    String actualProf;
    String actualProject;

    @Before
    public void setUp() throws Exception {
        actualProf = this.testRestTemplate.getForObject("/prof/add?name=ProfTest&email=profemail@carleton.ca", String.class);
        actualProject = this.testRestTemplate.getForObject("/prof/project/add?profId=1&title=TestProject&description=thisIsATestYouWillPass&programs=SE,CS&maxStudents=2", String.class);
    }

    @Test
    public void addNewProject() throws Exception {
        assertThat(actualProject).isEqualTo("Project added");
        assertThat(actualProf).isEqualTo("Saved Prof");
    }

    @Test
    public void getProjectById() throws Exception {
        Project p = this.testRestTemplate.getForObject("/project/getById?id=1", Project.class);
        assertThat(p).isNotNull();
        assertThat(p.getTitle()).isNotEmpty();
        assertThat(p.getDescription()).isNotEmpty();
        assertThat(p.getPrograms()).isNotEmpty();
        assertThat(p.getStudentLimit()).isGreaterThan(0);
        assertThat(p.getStudents()).isNotNull().isEmpty();
    }

    @Test
    public void getStudents() throws Exception {
        String studentList = this.testRestTemplate.getForObject("/project/getStudents?id=1", String.class);
        assertThat(studentList).isNotNull();
        List<Student> students = new ObjectMapper().readValue(studentList, new TypeReference<List<Student>>(){});
        assertThat(students.size()).isEqualTo(0);
        assertThat(students).isNotNull();
    }

    @Test
    public void getAllProjects() throws Exception {

    }

    @Test
    public void deleteProjectById() throws Exception {

    }

    @Test
    public void deleteAllProjects() throws Exception {

    }
    @Test
    public void returnHello() throws Exception {
            String message = "Welcome to Project Page";
            String body = this.testRestTemplate.getForObject(testURL, String.class);
            assertThat(body).isEqualTo(message);
    }
}