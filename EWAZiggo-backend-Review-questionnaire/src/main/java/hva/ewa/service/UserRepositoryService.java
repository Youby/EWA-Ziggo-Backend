package hva.ewa.service;

import java.util.List;

import hva.ewa.model.User;

import javax.ws.rs.core.UriInfo;

/**
 * An interface containing utility methods to manage flash card data
 * @author Emre Efe
 */
public interface UserRepositoryService {

    /**
     * Getting all users
     * @return
     */
    List<User> getAllUsers();

    /**
     * Getting a specific user
     * @param user
     * @return
     */
    User getUser(User user);

    /**
     * Adding a user
     * @param user
     */
    void addUser(User user);

    /**
     * Reverted old login. If we want to use JWT, uncomment the code below.
     * Uncomment in the following:
     * UserResource, UserRepositoryService, UserRepositoryServiceImpl.
     */
//    Boolean checkCredentials(String email, String password);

    User checkCredentials(String email, String password);

    User getUserFromId(int id);

    void deleteUser(User user);

    void changeUser(User user);

    String issueToken(String email, UriInfo uri);

}
