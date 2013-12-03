package groep13java.database;

import java.sql.*;

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
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String host = "jdbc:mysql://localhost/javadb";
            String uName = "root";
            String uPassword = "gojira";
            conn = DriverManager.getConnection(host, uName, uPassword);
        }
        catch(SQLException | ClassNotFoundException |InstantiationException | IllegalAccessException ex){
                System.err.println(ex.getMessage());
        }
    }
}
