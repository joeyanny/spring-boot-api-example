package example.api.transformer;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import example.api.dao.UserEntity;
import example.api.model.UserSearchRequest;
import example.api.model.UserSearchFilters;
import example.api.model.UserSearchPagingRequest;
import example.api.model.UserSearchResultsPaging;
import example.api.model.UserSearchSortRequest;

/**
 * Transformer used to convert between the API input and
 * required models for the integration with the data store.
 * This is used to decouple the API endpoints from the data store.
 */
@Component
public class SearchTransformer {

    /**
     * Create the {@link Pageable} object required for applying
     * pagination to the search.
     * 
     * @param searchPaging
     * @param searchSort
     * @return {@link Pageable}
     */
    public Pageable transformSearchPagingRequest(UserSearchRequest search)
    {
        UserSearchPagingRequest searchPaging = search.getPaging();
        UserSearchSortRequest searchSort = search.getSort();

        return PageRequest.of(searchPaging.getPage(), searchPaging.getLimit(), Sort.by(searchSort.getOrder()));
    }

    /**
     * Create the search criteria to filter the results
     * of the DB search using "query by example".
     * 
     * The search will only filter on non-null fields.
     * 
     * The {@link Example} type much match the type of the model
     * returned from the search ({@link UserEntity}).
     * 
     * @return example
     */
    public Example<UserEntity> transformSearchCriteria(UserSearchFilters filters) {
        UserEntity searchEntity = new UserEntity();
        searchEntity.setFirstName(filters.getFirstName());
        searchEntity.setLastName(filters.getLastName());

        return Example.of(searchEntity);
    }

    /**
     * Read the paging data from the search result.
     * 
     * @return {@link UserSearchResultsPaging}
     */
    public UserSearchResultsPaging transformSearchPagingResult(Page<UserEntity> results)
    {
        UserSearchResultsPaging resultsPaging = new UserSearchResultsPaging();
        resultsPaging.setPage(results.getNumber());
        resultsPaging.setItems(results.getNumberOfElements());
        resultsPaging.setTotalMatches(results.getTotalElements());
        resultsPaging.setTotalPages(results.getTotalPages());

        return resultsPaging;
    }

}
