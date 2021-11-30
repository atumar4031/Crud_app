package com.databases.dao;

import com.databases.model.Person;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface i_person_dao {

    Connection getDBconnection() throws SQLException ;
    ArrayList<Person> getUser() throws SQLException ;
    void addUser(Person person) throws SQLException ;
    Person searchUser(int id) throws SQLException ;
    boolean updateUser(int id) throws SQLException ;
    boolean deleteUser(int id) throws SQLException ;

}
