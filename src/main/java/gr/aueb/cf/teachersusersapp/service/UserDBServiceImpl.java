package gr.aueb.cf.teachersusersapp.service;


import gr.aueb.cf.teachersusersapp.dao.IUserDBDAO;
import gr.aueb.cf.teachersusersapp.dao.exceptions.UserDBDAOException;
import gr.aueb.cf.teachersusersapp.dto.UserDBDTO;
import gr.aueb.cf.teachersusersapp.model.UserDB;
import gr.aueb.cf.teachersusersapp.service.exceptions.UserDBNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserDBServiceImpl implements IUserDBService {
    // dependency injection
    private final IUserDBDAO userDAO;

    public UserDBServiceImpl(IUserDBDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDB insertUser(UserDBDTO userToInsert) throws UserDBDAOException {
        if (userToInsert == null) return null;

        try{
            UserDB user = mapUser(userToInsert);
            return userDAO.insert(user);

        } catch (UserDBDAOException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public UserDB updateUser(UserDBDTO userToUpdate) throws UserDBDAOException, UserDBNotFoundException {
        if (userToUpdate == null) return null;

        try {
            if (userDAO.getById(userToUpdate.getId()) == null){
                throw new UserDBNotFoundException("User with id " + userToUpdate.getId());
            }
            UserDB user = mapUser(userToUpdate);
            return userDAO.update(user);
        }catch (UserDBDAOException | UserDBNotFoundException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void deleteUser(int id) throws UserDBDAOException, UserDBNotFoundException {
        try{
            if (userDAO.getById(id) == null) {
                throw new UserDBNotFoundException("User with id " + id+ "not found");
            }
            userDAO.delete(id);
        } catch (UserDBDAOException | UserDBNotFoundException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<UserDB> getUsersByUsername(String username) throws UserDBDAOException {
        List<UserDB> users = new ArrayList<>();
        if (username == null ) return users;
        try {
            users = userDAO.getByUsername(username);
//            if (users.size() == 0) throw new UserNotFoundException("Not user found with username starting with "
//                    + username);
            return users;
        }  catch (UserDBDAOException  e){
            e.printStackTrace();
            throw e;
        }
    }

    private UserDB mapUser(UserDBDTO dto){
        return new UserDB(dto.getId(),dto.getUsername(), dto.getPassword());
    }
}
