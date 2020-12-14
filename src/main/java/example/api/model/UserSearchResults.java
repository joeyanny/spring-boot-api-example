package example.api.model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserSearchResults {

    private List<User> users;
    private UserSearchResultsPaging paging;

    public UserSearchResults(List<User> users, UserSearchResultsPaging paging) {
        this.users = users;
        this.paging = paging;
    }

    public List<User> getUsers() {
        return users;
    }

    public UserSearchResultsPaging getPaging() {
        return paging;
    }
}
