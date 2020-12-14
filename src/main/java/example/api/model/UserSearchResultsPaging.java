package example.api.model;

import org.springframework.stereotype.Component;

@Component
public class UserSearchResultsPaging {

    private long page;
    private long items;
    private long totalPages;
    private long totalMatches;

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public long getItems() {
        return items;
    }

    public void setItems(long items) {
        this.items = items;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalMatches() {
        return totalMatches;
    }

    public void setTotalMatches(long totalMatches) {
        this.totalMatches = totalMatches;
    }

}
