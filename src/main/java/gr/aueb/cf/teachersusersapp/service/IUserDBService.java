package gr.aueb.cf.teachersusersapp.service;

import gr.aueb.cf.teachersusersapp.dao.exceptions.UserDBDAOException;
import gr.aueb.cf.teachersusersapp.dto.UserDBDTO;
import gr.aueb.cf.teachersusersapp.model.UserDB;
import gr.aueb.cf.teachersusersapp.service.exceptions.UserDBNotFoundException;

import java.util.List;

public interface IUserDBService {
    UserDB insertUser(UserDBDTO userToInsert) throws UserDBDAOException;
    UserDB updateUser(UserDBDTO userToUpdate)
            throws  UserDBDAOException, UserDBNotFoundException;
    void deleteUser(int id) throws UserDBDAOException, UserDBNotFoundException;
    List<UserDB> getUsersByUsername(String username) throws UserDBDAOException;

}
