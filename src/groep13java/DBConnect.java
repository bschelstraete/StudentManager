package groep13java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    private static DBConnect instance = new DBConnect();
    private Connection conn;
    
    public static DBConnect getInstance()
    {
        return instance;
    }
    public Connection getConnection()
    {
        return conn;
    }
    
    private DBConnect()
    {
        try{
            String host = "jdbc:derby://localhost:8080/javadb";
            String uName = "root";
            String uPassword = "gojira";
            conn = DriverManager.getConnection(host, uName, uPassword);
        }
        catch(SQLException ex){
                System.err.println(ex.getMessage());
        }
        catch(ClassNotFoundException ex){
            System.err.println(ex.getMessage()));
        }
        catch(InstantiationException ex){
            System.err.println(ex.getMessage());
        }
        catch(IllegalAccessException ex){
            System.err.println(ex.getMessage());
        }
    }
}
