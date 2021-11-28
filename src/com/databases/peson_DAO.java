package com.databases;

import java.sql.*;
import java.util.ArrayList;

public class peson_DAO {
    private String url;
    private String usname;
    private String pword;
    private Connection con;
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsname() {
        return usname;
    }

    public void setUsname(String usname) {
        this.usname = usname;
    }

    public String getPword() {
        return pword;
    }

    public void setPword(String pword) {
        this.pword = pword;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public peson_DAO() {
        this.url = "jdbc:mysql://127.0.0.1:3306/crudapp?useSSL=false";
        this.usname = "root";
        this.pword = "root";
    }

    public void getDBconnection(){
        try{

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, usname, pword);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public ArrayList<Person> getUser(){
        ArrayList<Person> people = new ArrayList<>();
        String query = "SELECT * FROM person";
        try {
            Statement st = con.createStatement();
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
    public void addUser(Person person){
        String query = "INSERT INTO person values(?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
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
}
