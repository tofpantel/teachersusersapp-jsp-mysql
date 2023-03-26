package gr.aueb.cf.teachersusersapp.dao;

import gr.aueb.cf.teachersusersapp.dao.exceptions.UserDBDAOException;
import gr.aueb.cf.teachersusersapp.model.UserDB;

import gr.aueb.cf.teachersusersapp.service.util.DBUtil;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDBDAOImpl implements IUserDBDAO {

    @Override
    public UserDB insert(UserDB user) throws UserDBDAOException {
        String sql = "INSERT INTO USERS(USERNAME, PASSWORD) VALUES(?, ?)";
        int n;
        String inputUsername;
        String inputPassword;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {

            inputUsername = user.getUsername().trim();
            inputPassword = user.getPassword().trim();

            if(inputUsername.equals("") || inputPassword.equals("")) return null;

            int workload = 12;
            String salt = BCrypt.gensalt(workload);
            String hashedPassword = BCrypt.hashpw(inputPassword, salt);

            p.setString(1, inputUsername);
            p.setString(2, hashedPassword);

            p.executeUpdate();
            return user;
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            throw new UserDBDAOException("SQL Error in User " + user + " insertion");
        }
    }

    @Override
    public UserDB update(UserDB user) throws UserDBDAOException {
        String sql = "UPDATE USERS SET USERNAME = ?, PASSWORD = ? WHERE ID = ?";
//        int n;
        String inputUsername;
        String inputPassword;
        int inputId;

        try(Connection conn = DBUtil.getConnection();
            PreparedStatement p = conn.prepareStatement(sql)){

            int id = user.getId();
            inputUsername = user.getUsername().trim();
            inputPassword = String.valueOf(user.getPassword()).trim();
            inputId = user.getId();

            if(inputUsername.equals("") || inputPassword.equals("")) return null;

            int workload = 12;
            String salt = BCrypt.gensalt(workload);
            String hashedPassword = BCrypt.hashpw(inputPassword, salt);

            p.setString(1, inputUsername);
            p.setString(2, hashedPassword);
            p.setInt(3, inputId);

            p.executeUpdate();
            return user;
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            throw new UserDBDAOException("SQL Error in User " + user + "update");
        }
    }

    @Override
    public void delete(int id) throws UserDBDAOException {
        String sql = "DELETE FROM USERS WHERE ID = ?";

        try(Connection conn = DBUtil.getConnection();
            PreparedStatement p = conn.prepareStatement(sql)){
            p.setInt(1, id);
            p.executeUpdate();
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            throw new UserDBDAOException("SQL Error in User with id = " + id + "deleted");
        }

    }

    @Override
    public List<UserDB> getByUsername(String username) throws UserDBDAOException {
        String sql = "SELECT ID, USERNAME, PASSWORD FROM USERS WHERE USERNAME LIKE ?";
        ResultSet rs;
        List<UserDB> users = new ArrayList<>();

        try(Connection conn = DBUtil.getConnection();
            PreparedStatement p = conn.prepareStatement(sql)) {
            p.setString(1, username + '%');
            rs = p.executeQuery();

            while(rs.next()){
                UserDB user = new UserDB(
                        rs.getInt("ID"),
                        rs.getString("USERNAME"),
                        rs.getString("PASSWORD")
                );
                users.add(user);
            }
            return users;
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
            throw new UserDBDAOException("SQL Error in User with username = " + username);
        }
    }

    @Override
    public UserDB getById(int id) throws UserDBDAOException {
        UserDB user = null;
        ResultSet rs;
        String sql = "SELECT ID, USERNAME, PASSWORD FROM USERS WHERE ID = ?";

        try(Connection conn = DBUtil.getConnection();
            PreparedStatement p = conn.prepareStatement(sql)) {
            p.setInt(1, id);
            rs = p.executeQuery();

            if (rs.next()){
                user = new UserDB(
                        rs.getInt("ID"),
                        rs.getString("USERNAME"),
                        rs.getString("PASSWORD")
                );
            }
            return user;
        }catch (SQLException |ClassNotFoundException e){
            e.printStackTrace();
            throw new UserDBDAOException("SQL Error in User with id = " + id);
        }
    }
}
