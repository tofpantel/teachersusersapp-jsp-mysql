package gr.aueb.cf.teachersusersapp.dao;

import gr.aueb.cf.teachersusersapp.dao.exceptions.UserDBDAOException;
import gr.aueb.cf.teachersusersapp.model.UserDB;


import java.util.List;

public interface IUserDBDAO {
    UserDB insert(UserDB user) throws UserDBDAOException;
    UserDB update(UserDB user) throws UserDBDAOException;

    void delete(int id) throws UserDBDAOException;
    List<UserDB> getByUsername(String username) throws UserDBDAOException;
    UserDB getById(int id) throws UserDBDAOException;

}
