package groep13java.DAO;

import java.sql.*;
import javax.swing.JOptionPane;

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
            String uPassword = "";
            conn = DriverManager.getConnection(host, uName, uPassword);
        }
        catch(SQLException | ClassNotFoundException |InstantiationException | IllegalAccessException | NullPointerException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
                System.exit(1); //(0) -> zegt de shell dat het programma is afgesloten door valid-reasons, elk ander nummer is "errorcode"
        }
    }
}
