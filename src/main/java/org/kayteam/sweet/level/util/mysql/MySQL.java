package org.kayteam.sweet.level.util.mysql;

import java.sql.*;

public class MySQL { // Need Changes for use

    private final String ip;
    private final String port;
    private final String dataBase;

    private final String userName;
    private final String password;

    private PreparedStatement preparedStatement;
    private Connection connection = null;

    public MySQL(String ip, String port, String dataBase, String userName, String password) {
        this.ip = ip;
        this.port = port;
        this.dataBase = dataBase;
        this.userName = userName;
        this.password = password;
    }

    public boolean startConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + dataBase + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",userName,password);
            return connection != null;
        } catch(SQLException e) {
            System.out.println(e);
        } catch(ClassNotFoundException e) {
            System.out.println(e);
        }
        return false;
    }

    public Connection getConnection(){
        return connection;
    }

    public void closeConnection() {
        try{
            connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }

    public void executeUpdate(String update) {
        try {
            preparedStatement = connection.prepareStatement(update);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public ResultSet executeSelect(String table, String column, String value) {
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `" + table + "` WHERE `" + column +"` = ?");
            preparedStatement.setString(1, value);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void executeUpdate(String table, String column, String uuid, String newValue) {
        try {
            preparedStatement = connection.prepareStatement("UPDATE `"+ table +"` SET `" + column + "` = ? WHERE `uuid` = ?");
            preparedStatement.setString(1, newValue);
            preparedStatement.setString(2, uuid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void executeUpdate(String table, String column, String uuid, int newValue) {
        try {
            preparedStatement = connection.prepareStatement("UPDATE `"+ table +"` SET `" + column + "` = ? WHERE `uuid` = ?");
            preparedStatement.setInt(1, newValue);
            preparedStatement.setString(2, uuid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void executeUpdate(String table, String column, String uuid, double newValue) {
        try {
            preparedStatement = connection.prepareStatement("UPDATE `"+ table +"` SET `" + column + "` = ? WHERE `uuid` = ?");
            preparedStatement.setDouble(1, newValue);
            preparedStatement.setString(2, uuid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void executeInsert(String table, String uuid, int level, double experience) {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO `" + table + "` (uuid, level, experience) VALUES (?, ?, ?)");
            preparedStatement.setString(1, uuid);
            preparedStatement.setInt(2, level);
            preparedStatement.setDouble(3, experience);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void executeDelete(String table, String column, String value) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM `" + table + "` WHERE `" + column + "` ?");
            preparedStatement.setString(1 , value);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closePreparedStatement() {
        try {
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
