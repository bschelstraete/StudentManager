package groep13java.DAO;

import java.sql.*;

public class DAO {
    private static DAO instance = new DAO();
    private Connection conn;
    
    public static DAO getInstance()
    {
        return instance;
    }
    public Connection getConnection()
    {
        return conn;
    }
    
    private DAO()
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
