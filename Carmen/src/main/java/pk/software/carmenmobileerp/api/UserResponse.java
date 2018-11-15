package pk.software.carmenmobileerp.api;

/**
 * Created by Piotr Karmiński on 27.01.2018.
 */

public class UserResponse {

    public String username;
    public String objectId;
    public String email;
    public String firstName;
    public String lastName;
    public String sessionToken;

    @Override
    public String toString() {
        return "UserResponse{" +
                "username='" + username + '\'' +
                ", objectId='" + objectId + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sessionToken='" + sessionToken + '\'' +
                '}';
    }

}
