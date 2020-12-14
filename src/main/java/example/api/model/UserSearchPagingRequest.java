package example.api.model;

import org.springframework.stereotype.Component;

/**
 * Data for applying pagination to the search
 */
@Component
public class UserSearchPagingRequest {

    /** Page to return */
    private int page;

    /** Number of results to return */
    private int limit;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
