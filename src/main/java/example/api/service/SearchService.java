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
     * Perform a search for users with the optional filters
     * 
     * @param searchFilters
     */
    public UserSearchResults search(UserSearchRequest search) {
        // Create pagination, sorting and filter criteria
        Pageable pageable = searchTransformer.transformSearchPagingRequest(search);
        Example<UserEntity> searchEntity = searchTransformer.transformSearchCriteria(search.getFilters());

        // Perform search
        Page<UserEntity> results = repository.findAll(searchEntity, pageable);

        // Transform results of search from data store models into API models
        List<User> users = userTransformer.transform(results.toList());
        UserSearchResultsPaging resultPaging = searchTransformer.transformSearchPagingResult(results);

        return new UserSearchResults(users, resultPaging);
    }
}
