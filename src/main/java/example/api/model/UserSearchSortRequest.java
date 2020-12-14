package example.api.model;

import org.springframework.stereotype.Component;

/**
 * Data for sorting the results of the search
 */
@Component
public class UserSearchSortRequest {

    /** Name of field to sort on */
    private String order;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
