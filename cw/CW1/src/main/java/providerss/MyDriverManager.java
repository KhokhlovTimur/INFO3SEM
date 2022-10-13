package providerss;

import properties.PropertiesDriverManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDriverManager {
    public static Connection getConnection(){
        try {
            Class.forName(PropertiesDriverManager.DRIVER);
        }
        catch (ClassNotFoundException e){

        }
        try {
            return DriverManager.getConnection(PropertiesDriverManager.URL, PropertiesDriverManager.LOGIN, PropertiesDriverManager.PASSWORD);
        }
        catch (SQLException e){
            throw new RuntimeException();
        }
    }
}
