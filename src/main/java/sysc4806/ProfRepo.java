package sysc4806;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by CraigBook on 2018-03-21.
 */
@Repository
public interface ProfRepo extends CrudRepository<Prof, Long> {
}