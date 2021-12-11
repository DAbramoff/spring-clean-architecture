package interface_adapters;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import use_case_layer.UserInputBoundary;
import use_case_layer.UserRequestModel;
import use_case_layer.UserResponseModel;

@RestController
public class UserRegisterController {

    final UserInputBoundary userInput;

    UserRegisterController(UserInputBoundary accountGateway) {
        this.userInput = accountGateway;
    }

    @PostMapping("/user")
    UserResponseModel create(@RequestBody UserRequestModel requestModel) {
        return userInput.create(requestModel);
    }
}