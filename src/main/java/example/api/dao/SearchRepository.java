package example.api.dao;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

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

    /**
     * Perform a custom search using a JPA entity query.
     */
    @Query(value = "SELECT ue FROM UserEntity ue "
                 + "WHERE (:firstName is null OR ue.firstName = :firstName) "
                 + "AND (:lastName is null OR ue.lastName = :lastName)")
    Page<UserEntity> customJPAQuery(String firstName, String lastName, Pageable pageable);

    /**
     * Perform a custom search using a native SQL query.
     */
    @Query(value = "SELECT * FROM user u "
                 + "WHERE (:firstName is null OR u.first_name = :firstName) "
                 + "AND (:lastName is null OR u.last_name = :lastName)",
           nativeQuery=true)
    Page<UserEntity> customSQLQuery(String firstName, String lastName, Pageable pageable);
}
