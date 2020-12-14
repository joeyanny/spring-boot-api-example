package example.api.model;

import org.springframework.stereotype.Component;

/**
 * Filters for searching for users
 */
@Component
public class UserSearchFilters {

    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
