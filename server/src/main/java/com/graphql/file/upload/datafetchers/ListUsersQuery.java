package com.graphql.file.upload.datafetchers;

import com.graphql.file.upload.models.types.User;
import com.graphql.file.upload.service.UserService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;

import java.util.List;

@DgsComponent
public class ListUsersQuery {
    private final UserService userService;

    public ListUsersQuery(UserService userService) {
        this.userService = userService;
    }

    @DgsQuery
    public List<User> users() {
        return userService.getAllUsers();
    }
}
