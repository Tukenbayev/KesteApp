package kz.keste.service;

import kz.keste.dao.SchoolDAO;
import kz.keste.dao.SchoolDAOImpl;
import kz.keste.models.School;

import java.sql.SQLException;
import java.util.Map;

public class SchoolService {

    private SchoolDAO schoolDAO = new SchoolDAOImpl();

    public void registerSchool(School school) throws Exception {
        schoolDAO.registerSchool(school);
    }

    public Map<Integer,School> findByName(String schoolName) throws SQLException {
        return schoolDAO.findByName(schoolName);
    }

    public boolean checkAvailableEmail(String email) throws SQLException {
        return schoolDAO.checkAvailableEmail(email);

    }

}
