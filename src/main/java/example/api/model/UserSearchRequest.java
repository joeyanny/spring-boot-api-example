package example.api.model;

import org.springframework.stereotype.Component;

@Component
public class UserSearchRequest {

    private UserSearchFilters filters;
    private UserSearchPagingRequest paging;
    private UserSearchSortRequest sort;

    public UserSearchFilters getFilters() {
        return filters;
    }

    public void setFilters(UserSearchFilters filters) {
        this.filters = filters;
    }

    public UserSearchPagingRequest getPaging() {
        return paging;
    }

    public void setPaging(UserSearchPagingRequest paging) {
        this.paging = paging;
    }

    public UserSearchSortRequest getSort() {
        return sort;
    }

    public void setSort(UserSearchSortRequest sort) {
        this.sort = sort;
    }
}
