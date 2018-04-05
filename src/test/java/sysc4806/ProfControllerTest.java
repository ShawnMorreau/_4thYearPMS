package sysc4806;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.List;

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

        conn = DriverManager.getConnection(url, "root", "password");

        resetProfTable();
        resetProjectTable();
    }

    public void resetProfTable() throws SQLException {
        String resetProf = "alter table prof auto_increment = 1";
        st = conn.createStatement();
        st.executeUpdate(resetProf);
    }

    public void resetProjectTable() throws SQLException {
        String resetProj = "alter table project auto_increment = 1";
        st = conn.createStatement();
        st.executeUpdate(resetProj);
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
        String actualProf = this.testRestTemplate.getForObject("/prof/add?name=ProfTest&email=profemail@carleton.ca", String.class);
        String expectedProf = "Saved Prof";
        assertThat(actualProf).isEqualTo(expectedProf);

        String actualProject = this.testRestTemplate.getForObject("/prof/project/add?profId=1&title=TestProject&description=thisIsATestYouWillPass&programs=SE,CS&maxStudents=2", String.class);
        String expectedProjAdd = "Project added";
        assertThat(actualProject).isNotNull();
        assertThat(actualProject).isNotEmpty();
        assertThat(actualProject).isEqualTo(expectedProjAdd);
    }

    @Test
    public void deleteAllProfs () throws Exception {
//        Add sample profs
        String actualProf = this.testRestTemplate.getForObject("/prof/add?name=ProfTest&email=profemail@carleton.ca", String.class);
        String expectedProf = "Saved Prof";
        assertThat(actualProf).isEqualTo(expectedProf);

        String actualProf1 = this.testRestTemplate.getForObject("/prof/add?name=ProfTest0&email=profemail0@carleton.ca", String.class);
        String expectedProf1 = "Saved Prof";
        assertThat(actualProf1).isEqualTo(expectedProf1);

//        Check if they've all been added to the appropriate table/repository
        String profList = this.testRestTemplate.getForObject("/prof/all", String.class);
        assertThat(profList).isNotNull();
        assertThat(profList).isNotEmpty();

        List<Prof> profs = new ObjectMapper().readValue(profList, new TypeReference <List<Prof>> () {});
        assertThat(profs).isNotEmpty();
        assertThat(profs.size()).isEqualTo(2);

        //add project by a prof
        String actualProject = this.testRestTemplate.getForObject("/prof/project/add?profId=1&title=TestProject&description=thisIsATestYouWillPass&programs=SE,CS&maxStudents=2", String.class);
        String expectedProjAdd = "Project added";
        assertThat(actualProject).isNotNull();
        assertThat(actualProject).isNotEmpty();
        assertThat(actualProject).isEqualTo(expectedProjAdd);

        //get all projects
        String projectList = this.testRestTemplate.getForObject("/project/all", String.class);
        assertThat(projectList).isNotNull();
        List<Project> projects = new ObjectMapper().readValue(projectList, new TypeReference <List<Project>> () {});
        assertThat(projects).isNotNull();
        assertThat(projects.size()).isEqualTo(1);

        //delete profs
        String deleted = this.testRestTemplate.getForObject("/prof/deleteAll", String.class);
        assertThat(deleted).isNotNull();
        assertThat(deleted).isNotEmpty();
        assertThat(deleted).isEqualTo("Delete All");

        //profs gone, projects also deleted
        String projectList2 = this.testRestTemplate.getForObject("/project/all", String.class);
        assertThat(projectList2).isNotNull();
        System.out.println(projectList2);
        List<Project> projects2 = new ObjectMapper().readValue(projectList2, new TypeReference <List<Project>> () {});
        assertThat(projects2).isNotNull();
        assertThat(projects2.size()).isEqualTo(0);
    }


    @Test
    public void returnHello() throws Exception {
        String message = "Welcome to the Prof Page";
        String body = this.testRestTemplate.getForObject("/prof/test", String.class);
        assertThat(body).isEqualTo(message);
    }

}