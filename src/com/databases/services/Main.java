package com.databases.services;
import com.databases.dao.person_DAO;
import com.databases.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {
//    public  static Connection con =  null;
    public static void main(String[] args){
        person_DAO person_dao = new person_DAO();
	// write your code here
        int menu;
        menu = 1;
        Scanner scan = new Scanner(System.in);
        ArrayList<Person> ps = null;
        do{
            menu = menu();
//
            switch (menu){
                case 1 -> {
                    try{
                        System.out.println("Enter id: ");
                        int id = parseInt(scan.nextLine());
                        System.out.println("Enter name: ");
                        String name = scan.nextLine();
                        System.out.println("Enter age: ");
                        int age = parseInt(scan.nextLine());
                        Person person = new Person(id, name, age);
                        person_dao.addUser(person);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
                case 2 -> {
                    try{
                        System.out.println("----------------------------------------");
                        ps = person_dao.getUser();
                        ps.stream().forEach(System.out::println);
                        System.out.println("----------------------------------------");
                    }catch(Exception e){
                        e.printStackTrace();
                    }

                }
                case 3 -> {
                    System.out.println("Enter id: ");
                    int id = parseInt(scan.nextLine());
                    try{
                        var p = person_dao.searchUser(id);
                        System.out.println("----------------------------------------");
                        System.out.println(p == null ? "Recorde not found" : p);
                        System.out.println("----------------------------------------");
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
                case 4 -> {
                    try{
                        System.out.println("Enter user ID: ");
                        int userId = parseInt(scan.nextLine());
                        var res = person_dao.updateUser(userId);
                            System.out.println(!res ? "failed to update" : "user updated successfully");
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
                case 5 -> {
                    System.out.println("Enter id: ");
                    int id = parseInt(scan.nextLine());
                    try{
                        var del = person_dao.deleteUser(id);
                        System.out.println("----------------------------------------");
                        System.out.println(!del ? "Something went wrong" : "Record deleted");
                        System.out.println("----------------------------------------");
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                }

            }


        }while (menu != 99);
        person_dao.closeCon();
    }

    public static int menu() {
        Scanner scan = new Scanner(System.in);
        System.out.println("1. Add user");
        System.out.println("2. show users");
        System.out.println("3. search user");
        System.out.println("4. update user");
        System.out.println("5. delete user");
        return parseInt(scan.nextLine());
    }
}
