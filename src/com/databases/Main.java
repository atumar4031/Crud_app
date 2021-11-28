package com.databases;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {
//    public  static Connection con =  null;
    public static void main(String[] args){
	// write your code here
        peson_DOT peson_dot = new peson_DOT();
        int menu = 1;
        Scanner scan = new Scanner(System.in);
        ArrayList<Person> ps = null;
        do{
            menu = menu();
            peson_dot.getDBconnection();
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
                        peson_dot.addUser(person);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
                case 2 -> {
                    try{
                        System.out.println("----------------------------------------");
                        ps = peson_dot.getUser();
                        ps.stream().forEach(System.out::println);
                        System.out.println("----------------------------------------");
                    }catch(Exception e){
                        e.printStackTrace();
                    }

                }
                case 3 -> {
                    try{
//                        updateUser(id); // todo
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
                case 4 -> {
                    try{
//                        deleteUser("Delete user"); // todo
                        System.out.println("delete me");
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
            try {
                var con = peson_dot.getCon();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }while (menu != 99);
    }

    public static int menu() {
        Scanner scan = new Scanner(System.in);
        System.out.println("1. Add user");
        System.out.println("2. show user");
        System.out.println("3. Update user");
        System.out.println("4. Delete user");
        return parseInt(scan.nextLine());
    }
}
