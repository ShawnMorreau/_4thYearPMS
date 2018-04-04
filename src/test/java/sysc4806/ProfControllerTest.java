package sysc4806;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by CraigBook on 2018-03-21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProfControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    private static final String deleteAllProfsURL = "/prof/deleteAll";
    private static final String deleteAllProjectsURL= "/project/deleteAll";
//    private static final String deleteAllStudentsURL= "/student/deleteAll";

    private static final String url = "jdbc:mysql://localhost:3306/sysc";
    Connection conn = null;
    Statement st = null;
    
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        String clearProfs = this.testRestTemplate.getForObject(deleteAllProfsURL, String.class);
        System.out.println(clearProfs);
        String clearProjects = this.testRestTemplate.getForObject(deleteAllProjectsURL, String.class);
        System.out.println(clearProjects);
//        String clearStudents = this.testRestTemplate.getForObject(deleteAllStudentsURL, String.class);
//        System.out.println(clearStudents);


        conn = DriverManager.getConnection(url, "root", "");

        resetProfTable();
        resetProjectTable();
//        resetStudentTable();
    }

    public void resetProfTable() throws SQLException {

        String resetProf = "ALTER TABLE PROF AUTO_INCREMENT = 1";
        st = conn.createStatement();
        st.executeUpdate(resetProf);
    }

    public void resetProjectTable() throws SQLException {
        String resetProj = "ALTER TABLE PROJECT AUTO_INCREMENT = 1";
        st = conn.createStatement();
        st.executeUpdate(resetProj);
    }

    public void resetStudentTable() throws SQLException {
        String resetStudent = "ALTER TABLE PROJECT AUTO_INCREMENT = 1";
        st = conn.createStatement();
        st.executeUpdate(resetStudent);
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