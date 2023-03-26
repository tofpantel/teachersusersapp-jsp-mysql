package gr.aueb.cf.teachersusersapp.validation;

import gr.aueb.cf.teachersusersapp.dto.TeacherDTO;
import gr.aueb.cf.teachersusersapp.dto.UserDBDTO;

public class ValidatorUser {
    private ValidatorUser() {}

    public static String validate(UserDBDTO dto) {
        if (dto.getUsername().equals(""))  {
            return "Username: Empty";
        }
        if (dto.getPassword().equals("")){
            return "Password Empty";
        }
        return "";
    }
}
