package Model;

import java.util.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class LearnerDAO {

    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Connectionn connect = Connectionn.getInstance();

    public List tolist() {
        List<Learner> data = new ArrayList<>();
        try {
            con = connect.connect();
            ps = con.prepareStatement("SELECT * FROM aprendiz");
            rs = ps.executeQuery();
            while (rs.next()) {
                Learner le = new Learner();
                le.setId(rs.getInt(1));
                le.setDoctype(rs.getString(2));
                le.setDocnum(rs.getString(3));
                le.setName(rs.getString(4));
                le.setBorn(rs.getDate(5));
                le.setGender(rs.getString(6));
                le.setCity(rs.getString(7));
                data.add(le);

            }
        } catch (SQLException e) {
        }
        return data;
    }

    public int add(Learner lear) {
        int r = 0;
        String sql = "INSERT INTO aprendiz(Doctype,Docnum,name,bornDate,gender,city)VALUES (?,?,?,?,?,?)";
        try {
            con = connect.connect();
            ps = con.prepareStatement(sql);
            ps.setString(1, getDocTyperIndex(lear.getDoctype()));
            ps.setString(2, lear.getDocnum());
            ps.setString(3, lear.getName());
            java.util.Date datee = lear.getBorn();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String formatt = sdf.format(datee);
            ps.setString(4, formatt);
            ps.setString(5, lear.getGender());
            ps.setString(6, lear.getCity());
            r = ps.executeUpdate();
        } catch (SQLException e) {
        }
        return r;
    }

    public int update(Learner lear) {
        int r = 0;
        String sql = "UPDATE aprendiz SET Doctype=?,Docnum=?,name=?,bornDate=?,gender=?,city=? WHERE id=?";
        try {
            con = connect.connect();
            ps = con.prepareStatement(sql);
            ps.setString(1, getDocTyperIndex(lear.getDoctype()));
            ps.setString(2, lear.getDocnum());
            ps.setString(3, lear.getName());
            java.util.Date datee = lear.getBorn();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String formatt = sdf.format(datee);
            ps.setString(4, formatt);
            ps.setString(5, lear.getGender());
            ps.setString(6, lear.getCity());
            ps.setInt(7, lear.getId());

            r = ps.executeUpdate();
            if (r == 1) {
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException e) {
        }
        return r;
    }

    public int Delete(int ida) {
        int r = 0;
        String sql = "DELETE FROM aprendiz WHERE id=" + ida;
        try {
            con = connect.connect();
            ps = con.prepareStatement(sql);
            r = ps.executeUpdate();
        } catch (SQLException e) {
        }
        return r;
    }

    private String getDocTyperIndex(String doctype) {
        return switch (doctype) {
            case "Citizenship Card" ->
                "C.C";
            case "ID Card" ->
                "I.C";
            case "Civil Register" ->
                "C.R";
            case "Foreign Card" ->
                "F.C";
            case "Passport" ->
                "P.P";
            default ->
                "";
        };
    }
}
