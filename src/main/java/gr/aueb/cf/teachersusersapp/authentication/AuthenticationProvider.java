package gr.aueb.cf.teachersusersapp.authentication;


import gr.aueb.cf.teachersusersapp.dao.IUserDAO;
import gr.aueb.cf.teachersusersapp.dao.UserDAOImpl;
import gr.aueb.cf.teachersusersapp.dto.UserDTO;
import gr.aueb.cf.teachersusersapp.model.User;

public class AuthenticationProvider {

    private static final IUserDAO userDAO = new UserDAOImpl();

    private AuthenticationProvider() {}

    public static User authenticate(UserDTO userDTO) {
        if (!userDAO.isUserValid(userDTO.getUsername(), userDTO.getPassword())) {
            return null;
        }

        return new User(userDTO.getId(), userDTO.getUsername(), userDTO.getPassword());
    }
}
