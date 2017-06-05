package kz.keste.dao;

import kz.keste.models.School;

import java.sql.SQLException;
import java.util.Map;

public interface SchoolDAO {

    void registerSchool(School school) throws Exception;
    void changeEmail(String email);
    void changePassword(String password);
    Map<Integer,School> findByName(String schoolName) throws SQLException;
    boolean checkAvailableEmail(String email) throws SQLException;
}
