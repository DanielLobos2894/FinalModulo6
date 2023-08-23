package cl.prueba.spring.repositories;

import cl.prueba.spring.models.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository  extends CrudRepository<RoleEntity, Long> {
}
