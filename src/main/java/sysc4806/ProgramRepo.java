package sysc4806;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepo extends CrudRepository<Program, Long> {

}