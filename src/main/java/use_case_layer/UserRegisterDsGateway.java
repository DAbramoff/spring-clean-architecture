package use_case_layer;

public interface UserRegisterDsGateway {
    boolean existsByName(String identifier);

    void save(UserDsRequestModel requestModel);
}