package gr.aueb.cf.teachersusersapp.validation;


import gr.aueb.cf.teachersusersapp.dto.TeacherDTO;

public class Validator {
    private Validator() {}

    public static String validate(TeacherDTO dto) {
        if (dto.getFirstname().equals(""))  {
            return "Firstname: Empty";
        }

        if ((dto.getLastname().equals(""))) {
            return "Lastname: Empty";
        }

        return "";
    }
}
