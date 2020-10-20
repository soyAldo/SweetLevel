package org.kayteam.sweet.level.storage;

import org.bukkit.configuration.file.FileConfiguration;
import org.kayteam.sweet.level.SweetLevel;
import org.kayteam.sweet.level.storage.enums.StorageType;
import org.kayteam.sweet.level.util.message.StringMessage;
import org.kayteam.sweet.level.util.mysql.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class MySqlStorage extends Storage{

    private final SweetLevel sweetLevel;
    private final MySQL mySQL;

    public MySqlStorage(SweetLevel sweetLevel) {
        super(StorageType.MYSQL);
        this.sweetLevel = sweetLevel;
        FileConfiguration settings = sweetLevel.getSettings().getFileConfiguration();
        String ip = settings.getString("mysql.ip");
        String port = settings.getString("mysql.port");
        String dataBase = settings.getString("mysql.database");
        String userName = settings.getString("mysql.username");
        String password = settings.getString("mysql.password");
        mySQL = new MySQL(ip, port, dataBase, userName, password);
    }

    public void setupMySQL() {
        if (mySQL.startConnection()) {
            mySQL.executeUpdate("CREATE TABLE IF NOT EXISTS user(" +
                    "uuid varchar(40) NOT NULL PRIMARY KEY," +
                    "level integer," +
                    "experience double)");
            mySQL.closePreparedStatement();
            mySQL.closeConnection();
        } else {
            FileConfiguration settings = sweetLevel.getSettings().getFileConfiguration();
            if (settings.getBoolean("stopServerFailMysqlConnection")) {
                StringMessage mysqlStopFailConnect = new StringMessage(sweetLevel.getMessages().getFileConfiguration().getString("mysqlStopFailConnect"));
                mysqlStopFailConnect.addColor();
                sweetLevel.getServer().getConsoleSender().sendMessage(mysqlStopFailConnect.getMessage());
                sweetLevel.getServer().shutdown();
            }
        }
    }

    @Override
    public int getLevel(UUID uuid) {
        int level = 0;
        mySQL.startConnection();
        ResultSet resultSet = mySQL.executeSelect("user", "uuid", uuid.toString());
        try {
            while (resultSet.next()) {
                level = resultSet.getInt("level");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mySQL.closePreparedStatement();
        mySQL.closeConnection();
        return level;
    }

    @Override
    public void setLevel(UUID uuid, int level) {
        boolean exist = false;
        mySQL.startConnection();
        ResultSet resultSet = mySQL.executeSelect("user", "uuid", uuid.toString());
        try {
            while (resultSet.next()) {
                exist = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (exist) {
            mySQL.executeUpdate("user", "level", uuid.toString(), level);
        } else {
            mySQL.executeInsert("user", uuid.toString(), level, 0);
        }
        mySQL.closePreparedStatement();
        mySQL.closeConnection();
    }

    @Override
    public double getExperience(UUID uuid) {
        double experience = 0;
        mySQL.startConnection();
        ResultSet resultSet = mySQL.executeSelect("user", "uuid", uuid.toString());
        try {
            while (resultSet.next()) {
                experience = resultSet.getDouble("experience");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mySQL.closePreparedStatement();
        mySQL.closeConnection();
        return experience;
    }

    @Override
    public void setExperience(UUID uuid, double experience) {
        boolean exist = false;
        mySQL.startConnection();
        ResultSet resultSet = mySQL.executeSelect("user", "uuid", uuid.toString());
        try {
            while (resultSet.next()) {
                exist = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (exist) {
            mySQL.executeUpdate("user", "level", uuid.toString(), experience);
        } else {
            mySQL.executeInsert("user", uuid.toString(), 1, experience);
        }
        mySQL.closePreparedStatement();
        mySQL.closeConnection();
    }
}
