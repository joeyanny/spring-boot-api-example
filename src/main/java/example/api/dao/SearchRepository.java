package example.api.dao;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Data access object for integrating with the data store
 * for searching for user data.
 * 
 * Spring Data JPA will automatically create the implementation
 * when the application is run using the 2 parameters of
 * {@link JpaRepository} as the entity and ID types.
 * 
 * {@link JpaRepository} extends {@link PagingAndSortingRepository}
 * which will handle the sorting and pagination.
 * 
 * {@link JpaRepository} extends {@link QueryByExampleExecutor}
 * which will handle filtering of the results by the data supplied
 * within an {@link Example}. Any null fields will be ignored when
 * filtering.
 */
public interface SearchRepository extends JpaRepository<UserEntity, Long> {
}
