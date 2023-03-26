package gr.aueb.cf.teachersusersapp.service.exceptions;


import gr.aueb.cf.teachersusersapp.model.UserDB;

public class UserDBNotFoundException extends Exception{

    private final static long serialVersionUID = 1L;

    public UserDBNotFoundException(UserDB user) {
        super("User with id " + user.getId() + " does not exist");
    }

    public UserDBNotFoundException(String s) {
        super(s);
    }
}

