package org.kayteam.sweet.level.util.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestingMySQL {

    public static void main(String[] args) {
        MySQL mySQL = new MySQL("66.11.113.170", "3306", "s25825_pruebas", "u25825_HoPPFXMZtb", "M2aKohMz+NAMhgxAl^oLRT.n");
        // Create
        mySQL.startConnection();
        mySQL.executeUpdate("CREATE TABLE IF NOT EXISTS user(uuid varchar(40) NOT NULL PRIMARY KEY,username varchar(225) NOT NULL UNIQUE,password varchar(225))");
        mySQL.closePreparedStatement();
        mySQL.closeConnection();

        // Update
        mySQL.startConnection();
        mySQL.executeUpdate("user", "username" , "AldoGamer", "Aldo");
        mySQL.closePreparedStatement();
        mySQL.closeConnection();

        // Select
        mySQL.startConnection();
        ResultSet resultSet = mySQL.executeSelect("user",  "uuid", "815b84b8-16e3-4ffe-8dcc-c8619e26d0cc");

        try {
            while (resultSet.next()) {
                System.out.println("UUID: " +resultSet.getString("uuid"));
                System.out.println("USERNAME: " +resultSet.getString("username"));
                System.out.println("PASSWORD: " +resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mySQL.closePreparedStatement();
        mySQL.closeConnection();

        /*
        //Insert
        mySQL.startConnection();
        mySQL.executeInsert("user", "4a6ee232-e641-4db6-a7b2-412288d3365c", "SirOswaldo", "Capitan1234");
        mySQL.closePreparedStatement();
        mySQL.closeConnection();

        //Insert
        mySQL.startConnection();
        mySQL.executeInsert("user", "4a6ee232-e641-4db6-a7b2-412288d3365c", "SirOswaldo", "Capitan1234");
        mySQL.closePreparedStatement();
        mySQL.closeConnection();


         */
        //Delete
        mySQL.startConnection();
        mySQL.executeDelete("user", "username", "Aldo");
        mySQL.closePreparedStatement();
        mySQL.closeConnection();
    }
}
