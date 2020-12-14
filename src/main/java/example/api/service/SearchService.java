package example.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import example.api.dao.SearchRepository;
import example.api.dao.UserEntity;
import example.api.model.User;
import example.api.model.UserSearchRequest;
import example.api.model.UserSearchResults;
import example.api.model.UserSearchResultsPaging;
import example.api.transformer.SearchTransformer;
import example.api.transformer.UserTransformer;

/**
 * Handles searching of data within the resource.
 */
@Service
public class SearchService {

    @Autowired
    private SearchRepository repository;

    @Autowired
    private SearchTransformer searchTransformer;

    @Autowired
    private UserTransformer userTransformer;

    /**
     * Perform a search for users with optional filters using
     * an {@link Example}. This will ignore any null fields
     * when filtering.
     * 
     * @param search Data for configuring the search (pagination, sorting and filtering)
     * @return results Result data (list of users and paging)
     */
    public UserSearchResults search(UserSearchRequest search) {
        // Create pagination and sorting criteria
        Pageable pageable = searchTransformer.transformSearchPagingRequest(search);

        // Create filtering criteria (Exact matching only. Null fields will be ignored)
        Example<UserEntity> searchEntity = searchTransformer.transformSearchCriteria(search.getFilters());

        // Perform search with pagination and ordering
        Page<UserEntity> results = repository.findAll(searchEntity, pageable);

        // Transform results of search from data store models into API models
        List<User> users = userTransformer.transform(results.toList());
        UserSearchResultsPaging resultPaging = searchTransformer.transformSearchPagingResult(results);

        return new UserSearchResults(users, resultPaging);
    }

    /**
     * Perform a search for users with the optional filters
     * using a JPA entity query.
     * 
     * @param search Data for configuring the search (pagination, sorting and filtering)
     * @return results Result data (list of users and paging)
     */
    public UserSearchResults searchWithJPA(UserSearchRequest search) {
        // Create pagination and sorting criteria
        Pageable pageable = searchTransformer.transformSearchPagingRequest(search);

        // Perform search with pagination and ordering
        Page<UserEntity> results = repository.customJPAQuery(search.getFilters().getFirstName(), search.getFilters().getLastName(), pageable);

        // Transform results of search from data store models into API models
        List<User> users = userTransformer.transform(results.toList());
        UserSearchResultsPaging resultPaging = searchTransformer.transformSearchPagingResult(results);

        return new UserSearchResults(users, resultPaging);
    }

    /**
     * Perform a search for users with the optional filters
     * using a native SQL query.
     * 
     * @param search Data for configuring the search (pagination, sorting and filtering)
     * @return results Result data (list of users and paging)
     */
    public UserSearchResults searchWithSQL(UserSearchRequest search) {
    	// Create pagination and sorting criteria
        Pageable pageable = searchTransformer.transformSearchPagingRequest(search);

        // Perform search with pagination and ordering
        Page<UserEntity> results = repository.customSQLQuery(search.getFilters().getFirstName(), search.getFilters().getLastName(), pageable);

        // Transform results of search from data store models into API models
        List<User> users = userTransformer.transform(results.toList());
        UserSearchResultsPaging resultPaging = searchTransformer.transformSearchPagingResult(results);

        return new UserSearchResults(users, resultPaging);
    }
}
