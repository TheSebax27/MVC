
package Model;

import View.Ficha;
import java.util.*;
import java.sql.*;

public class TabDAO {
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Connectionn connect = Connectionn.getInstance();
    public List tolist() {
        List<Tab> data = new ArrayList<>();
        try {
            con = connect.connect();
            ps = con.prepareStatement("SELECT *FROM ficha JOIN aprendiz ON ficha.id_aprendiz = aprendiz.id");
            rs = ps.executeQuery();
            while (rs.next()) {
                Tab t = new Tab();
                t.setId(rs.getInt(1));
                t.setTabnum(rs.getString(2));
                t.setTabname(rs.getString(3));
                t.setSite(rs.getString(4));
                t.setCity(rs.getString(5));
                t.setIdAprendiz(rs.getInt(6));
                data.add(t);
            }
        } catch (SQLException e) {
        }
        return data;
    }
    public int addF(Tab tb){
        int r = 0;
         String sql = "INSERT INTO ficha(numeroficha,nombreficha,sede,ciudad,id_aprendiz)VALUES (?,?,?,?,?)";
         try {
             con = connect.connect();
            ps = con.prepareStatement(sql);
             
            ps.setString(1, tb.getTabnum());
            ps.setString(2, tb.getTabname());
            ps.setString(3, tb.getSite());
            ps.setString(4, tb.getCity());
            ps.setInt(5, tb.getIdAprendiz());
            r = ps.executeUpdate();
             
        } catch (SQLException e) {
        }
         return r;
    }
    public int updateF(Tab tb){
        int r = 0;
         String sql = "UPDATE ficha SET numeroficha=?, nombreficha=?,sede=?,ciudad=?,id_aprendiz=? WHERE id_ficha=?";
         try {
             con = connect.connect();
            ps = con.prepareStatement(sql);
            ps.setString(1, tb.getTabnum());
            ps.setString(2, tb.getTabname());
            ps.setString(3, tb.getSite());
            ps.setString(4, tb.getCity());
            ps.setInt(5, tb.getIdAprendiz());
            ps.setInt(6, tb.getId());
            r = ps.executeUpdate();
             if (r==1) {
                 return 1;
             } else {
                 return 0;
             }
        } catch (SQLException e) {
        }
         return r;
    }
      public int DeleteF(int ida) {
        int r = 0;
        String sql = "DELETE FROM ficha WHERE id_ficha=" + ida;
        try {
            con = connect.connect();
            ps = con.prepareStatement(sql);
            r = ps.executeUpdate();
        } catch (SQLException e) {
        }
        return r;
    }
      
}
