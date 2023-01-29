package server;

import java.awt.*;
import java.sql.*;

public class DatabaseHelper extends Config {

    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public void addPlayer(String name, Integer mmr) {
        String addUser = "INSERT INTO " + Constants.PLAYERS_TABLE +
                "(" + Constants.PLAYERS_NAME + "," + Constants.PLAYERS_MMR + ")" +
                "VALUES(?,?)";

        try {PreparedStatement prState = getDbConnection().prepareStatement(addUser);
            prState.setString(1, name);
            prState.setInt(2, mmr);

            prState.executeUpdate();

        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
        } catch (ClassNotFoundException cnfE){
            cnfE.printStackTrace();
        }

    }

}
