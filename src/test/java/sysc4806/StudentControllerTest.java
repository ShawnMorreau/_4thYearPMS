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
public class StudentControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void addNewStudent() throws Exception {
        String actual = this.testRestTemplate.getForObject("/student/add?name=JaspreetSanghra&email=jaspreet@gmail.com&program=SE", String.class);
        String expected = "Saved Student";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void getById() throws Exception {
        String actual = this.testRestTemplate.getForObject("/student/add?name=JaspreetSanghra&email=jaspreet@gmail.com&program=SE", String.class);
        String expected = "Saved Student";
        assertThat(actual).isEqualTo(expected);
        Student s = this.testRestTemplate.getForObject("/student/getById?id=1", Student.class);
        assertThat(s).isNotNull();
        assertThat(s.getName()).isNotEmpty();
        assertThat(s.getEmail()).isNotEmpty();
        assertThat(s.getProgram()).isNotEmpty();
        assertThat(s.toString()).isNotEmpty();
    }

    @Test
    public void studentJoinsProject() throws Exception {
        //add student
        String actual = this.testRestTemplate.getForObject("/student/add?name=JaspreetSanghra&email=jaspreet@gmail.com&program=SE", String.class);
        String expected = "Saved Student";
        assertThat(actual).isEqualTo(expected);
        Student s = this.testRestTemplate.getForObject("/student/getById?id=1", Student.class);
        assertThat(s).isNotNull();
        assertThat(s.getName()).isNotEmpty();
        assertThat(s.getEmail()).isNotEmpty();
        assertThat(s.getProgram()).isNotEmpty();
        assertThat(s.toString()).isNotEmpty();
        //add project
        addValidProject();
        //get project
        Project p = this.testRestTemplate.getForObject("/project/getById?id=1", Project.class);
        assertThat(p).isNotNull();
        assertThat(p.getTitle()).isNotEmpty();
        assertThat(p.getDescription()).isNotEmpty();
        assertThat(p.getPrograms()).isNotEmpty();
        assertThat(p.getStudentLimit()).isGreaterThan(0);
        assertThat(p.getStudents()).isNotNull().isEmpty();
        //add and verify student to project
        String result = this.testRestTemplate.getForObject("/student/project/add?studentId=1&projectId=1", String.class);
        String expectedResult = "Project Set";
        assertThat(result).isEqualTo(expectedResult);
        //get project
        Project pp = this.testRestTemplate.getForObject("/project/getById?id=1", Project.class);
        assertThat(pp).isNotNull();
        assertThat(pp.getStudents()).isNotNull();
        assertThat(pp.getStudents().size()).isEqualTo(1);
        //remove student from project
        String resultRemove = this.testRestTemplate.getForObject("/student/project/remove?studentId=1&projectId=1", String.class);
        String expectedRemoveResult = "Project Removed";
        assertThat(result).isEqualTo(expectedResult);
        //verify student is removed
        pp = this.testRestTemplate.getForObject("/project/getById?id=1", Project.class);
        assertThat(pp).isNotNull();
        assertThat(pp.getStudents()).isNotNull();
        assertThat(pp.getStudents().size()).isEqualTo(0);
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