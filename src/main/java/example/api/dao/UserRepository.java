package example.api.dao;

import org.springframework.data.repository.CrudRepository;

/**
 * Data access object for integrating with the data source
 * for storing and retrieving user data.
 * 
 * Spring Data JPA will automatically create the implementation
 * when the application is run using the 2 parameters of
 * {@link CrudRepository} as the entity and ID types.
 */
public interface UserRepository extends CrudRepository<UserEntity, Long> {

}
