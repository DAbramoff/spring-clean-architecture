package entity_layer;

public interface User {
    boolean passwordIsValid();

    String getName();

    String getPassword();
}