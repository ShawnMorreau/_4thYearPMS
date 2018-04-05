package sysc4806;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProfRepo extends CrudRepository<Prof, Long> {
}
