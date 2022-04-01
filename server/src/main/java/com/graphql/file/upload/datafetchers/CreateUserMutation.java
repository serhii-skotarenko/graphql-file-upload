package com.graphql.file.upload.datafetchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphql.file.upload.models.inputs.UserInput;
import com.graphql.file.upload.models.types.User;
import com.graphql.file.upload.service.UserService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@DgsComponent
public class CreateUserMutation {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final UserService userService;

    public CreateUserMutation(UserService userService) {
        this.userService = userService;
    }

    @DgsMutation
    public User createUser(DataFetchingEnvironment dfe) throws IOException {
        UserInput userInput = objectMapper.convertValue(dfe.getArgument("user"), UserInput.class);
        MultipartFile multipartFile = dfe.getArgument("avatar");
        if (multipartFile != null && !multipartFile.isEmpty()) {
            userInput.setAvatar(multipartFile.getBytes());
        }
        return userService.createUser(userInput);
    }

}
