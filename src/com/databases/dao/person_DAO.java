package com.databases.dao;

import com.databases.model.Person;

import javax.management.Query;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class person_DAO implements  i_person_dao{
    private String url;
    private String usname;
    private String pword;
    private Connection connection = null;

    public person_DAO() {
        this.url = "jdbc:mysql://127.0.0.1:3306/crudapp?useSSL=false";
        this.usname = "root";
        this.pword = "root";
        connection = this.getDBconnection();
    }

    @Override
    public Connection getDBconnection(){
        Connection con = null;
        try{

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.getUrl(), this.getUsname(), this.getPword());
        }catch (Exception e){
            e.printStackTrace();
        }
        return con;
    }

    @Override
    public ArrayList<Person> getUser(){
        ArrayList<Person> people = new ArrayList<>();
        String query = "SELECT * FROM person";
        try {
//            Connection con = this.getDBconnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
                people.add(new Person(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age")
                ));
            st.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return people;
    }
    @Override
    public void addUser(Person person){
        String query = "INSERT INTO person values(?, ?, ?)";
        try {
//            Connection con = this.getDBconnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, person.getId());
            ps.setString(2, person.getName());
            ps.setInt(3, person.getAge());
            int affected = ps.executeUpdate();
            if(affected > 0)
                System.out.println("Rows affected: "+ affected);
            ps.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public Person searchUser(int id) {
        Person p = null;
        String query = "SELECT * FROM person WHERE id ="+ id;
        try{
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(query);
            if(rs.next()){
                int uid = rs.getInt("id");
                String uname = rs.getString("name");
                int uage = rs.getInt("age");
                p = new Person(uid ,uname, uage);

            }
        }catch (Exception ex){
            ex.printStackTrace();

        }
        return p;
    }
    @Override
    public boolean updateUser(int id) {
        boolean response = false;
        System.out.println("Enter new name: ");
        String name = new Scanner(System.in).nextLine();
        try{
            String query = "UPDATE person SET name = ?";
            PreparedStatement prepStmt = connection.prepareStatement(query);
            prepStmt.setString(1, name);
            int resunt = prepStmt.executeUpdate();
            if(resunt > 0)
                response = true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public boolean deleteUser(int id) {
        boolean flag = false;
        String deleteSQL = "Delete person from person Where id ="+ id;
        try{
            Statement stm = connection.createStatement();
            var del = stm.executeUpdate(deleteSQL);
            if(del > 0)
                flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String getUsname() {
        return usname;
    }

    private void setUsname(String usname) {
        this.usname = usname;
    }

    private String getPword() {
        return pword;
    }

    private void setPword(String pword) {
        this.pword = pword;
    }

public void closeCon(){
    try {
        connection.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}
