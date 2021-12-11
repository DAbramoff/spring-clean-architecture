package use_case_layer;

public interface UserInputBoundary {
    UserResponseModel create(UserRequestModel requestModel);
}