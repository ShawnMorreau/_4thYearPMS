package sysc4806;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProjectRepo extends CrudRepository<Project, Long> {
}
