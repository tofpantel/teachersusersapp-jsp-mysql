package gr.aueb.cf.teachersusersapp.dao;


import gr.aueb.cf.teachersusersapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.teachersusersapp.model.Teacher;

import java.util.List;

public interface ITeacherDAO {

    Teacher insert(Teacher teacher) throws TeacherDAOException;
    Teacher update(Teacher teacher) throws TeacherDAOException;
    void delete(int id) throws TeacherDAOException;
    List<Teacher> getByLastname(String lastname) throws TeacherDAOException;
    Teacher getById(int id) throws TeacherDAOException;
}
