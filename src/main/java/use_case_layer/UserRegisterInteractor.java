package use_case_layer;

import entity_layer.User;
import entity_layer.UserFactory;
import use_case_layer.UserInputBoundary;
import use_case_layer.UserPresenter;

import java.time.LocalDateTime;

public class UserRegisterInteractor implements UserInputBoundary {

    final UserRegisterDsGateway userDsGateway;
    final UserPresenter userPresenter;
    final UserFactory userFactory;

    UserRegisterInteractor(UserRegisterDsGateway userRegisterDfGateway, UserPresenter userPresenter,
                           UserFactory userFactory) {
        this.userDsGateway = userRegisterDfGateway;
        this.userPresenter = userPresenter;
        this.userFactory = userFactory;
    }

    @Override
    public UserResponseModel create(UserRequestModel requestModel) {
        if (userDsGateway.existsByName(requestModel.getName())) {
            return userPresenter.prepareFailView("entity_layer.User already exists.");
        }
        User user = userFactory.create(requestModel.getName(), requestModel.getPassword());
        if (!user.passwordIsValid()) {
            return userPresenter.prepareFailView("entity_layer.User password must have more than 5 characters.");
        }
        LocalDateTime now = LocalDateTime.now();
        UserDsRequestModel userDsModel = new UserDsRequestModel(user.getName(), user.getPassword(), now);

        userDsGateway.save(userDsModel);

        UserResponseModel accountResponseModel = new UserResponseModel(user.getName(), now.toString());
        return userPresenter.prepareSuccessView(accountResponseModel);
    }
}
