
package Model;

import java.sql.*;
import javax.swing.JOptionPane;
public class Connectionn {
    private Connectionn(){
        
    }
    private static Connection connectt;
    private static Connectionn Instance;
    
    public static final String URL = "jdbc:mysql://localhost:3306/mvcaprendiz";
    public static final String USER = "root";
    public static final String PASSWORD = "";
     
     public Connection connect(){
         try {
             connectt =  DriverManager.getConnection(URL,USER,PASSWORD);
             
             return connectt;
         } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "error"+e);
         }
       return connectt;
     }
     public void endConnection() throws SQLException{
         try {
             
                connectt.close(); 
            
         } catch (SQLException e) {
         }
         
     }
     public static Connectionn getInstance(){
         if (Instance==null) {
             Instance = new Connectionn();
         }
         return Instance;
     }
}
