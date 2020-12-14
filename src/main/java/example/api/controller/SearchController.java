package example.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import example.api.model.UserSearchRequest;
import example.api.model.UserSearchResults;
import example.api.service.SearchService;

@RestController
public class SearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping("/users/search")
    public UserSearchResults search(@RequestBody UserSearchRequest search) {
        return searchService.search(search);
    }

    @PostMapping("/users/search/jpa")
    public UserSearchResults searchWithJPA(@RequestBody UserSearchRequest search) {
        return searchService.searchWithJPA(search);
    }

    @PostMapping("/users/search/sql")
    public UserSearchResults searchWithJSQL(@RequestBody UserSearchRequest search) {
        return searchService.searchWithSQL(search);
    }
}
