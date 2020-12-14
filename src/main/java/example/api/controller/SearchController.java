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

    @PostMapping("/search/users")
    public UserSearchResults search(@RequestBody UserSearchRequest search) {
        return searchService.search(search);
    }
}
