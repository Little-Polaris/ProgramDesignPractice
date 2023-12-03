/**
 * Created by xsw on 2017/5/30.
 */
//完成教室信息添加、修改、查询等操作的的业务逻辑处理类

import java.lang.reflect.GenericDeclaration;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;

public class DepartDao {
    private JdbcConnection dbUtils = new JdbcConnection();
    public void save(String type, Depart d) throws Exception {
        if (type.equals("room")) {
            String sql = "insert into roommessage(RName, RNum, RMemberCount, RArea, RPicDir, RUsage) values(?,?,?,?,?,?)";
            Connection conn = dbUtils.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, d.getRName());
            pstmt.setString(2, d.getRNum());
            pstmt.setString(3, d.getRMemberCount());
            pstmt.setString(4, d.getRArea());
            pstmt.setString(5, d.getRPicDir());
            pstmt.setString(6, d.getRUsage());
            pstmt.executeUpdate();
            dbUtils.close(null, pstmt, conn);
        } else if (type.equals("admin")) {
            String sql = "insert into administrator(Name, Id, Gender, Pwd, Tel, Email) values(?,?,?,?,?,?)";
            Connection conn = dbUtils.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, d.getAdName());
            pstmt.setString(2, d.getAdId());
            pstmt.setString(3, d.getAdGender());
            pstmt.setString(4, d.getAdPwd());
            pstmt.setString(5, d.getAdTel());
            pstmt.setString(6, d.getAdEmail());
            pstmt.executeUpdate();
            dbUtils.close(null, pstmt, conn);
        } else if (type.equals("user")) {
            String sql = "insert into reged_user(Name, Id, Gender, Pwd, Tel, Email) values(?,?,?,?,?,?)";
            Connection conn = dbUtils.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, d.getUserName());
            pstmt.setString(2, d.getUserId());
            pstmt.setString(3, d.getUserGender());
            pstmt.setString(4, d.getUserPwd());
            pstmt.setString(5, d.getUserTel());
            pstmt.setString(6, d.getUserEmail());
            pstmt.executeUpdate();
            dbUtils.close(null, pstmt, conn);

        }
    }

    public void modify(String type, Depart d) throws Exception {
        String sql = null;
        if (type.equals("room")) {
            sql = "update roommessage set RName = '" + d.getRName() + "', RNum = '" + d.getRNum() + "', RMemberCount = '" + d.getRMemberCount() + "', RArea = '" + d.getRArea() + "', RPicDir = '" + d.getRPicDir() + "', RUsage = '" + d.getRUsage() + "' where RName = '" + d.getRName() + "'";
        } else if (type.equals("admin")) {
            sql = "update administrator set Name = '" + d.getAdName() + "', ID = '" + d.getAdId() + "', Gender = '" + d.getAdGender() + "', Pwd = '" + d.getAdPwd() + "', Tel = '" + d.getAdTel() + "', Email = '" + d.getAdEmail() + "' where Name = '" + d.getAdName() + "'";

        } else if (type.equals("user")) {
            sql = "update reged_user set Name = '" + d.getUserName() + "', ID = '" + d.getUserId() + "', Gender = '" + d.getAdGender() + "', Pwd = '" + d.getUserPwd() + "', Tel = '" + d.getUserTel() + "', Email = '" + d.getUserEmail() + "' where Name = '" + d.getUserName() + "'";
        }
        System.out.println(sql);
        Connection conn = dbUtils.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.executeUpdate();
        dbUtils.close(null, pstmt, conn);
    }

    public void delete(String type, Depart d) throws Exception {
        String sql = null;
        if (type.equals("room")) {
            sql = "delete from roommessage where RName = '" + d.getRName() + "'";
        } else if (type.equals("admin")) {
            sql = "delete from administrator where Name = '" + d.getAdName() + "'";
        } else if (type.equals("user")) {
            sql = "delete from reged_user where Name = '" + d.getUserName() + "'";
        }
        System.out.println(sql);
        Connection conn = dbUtils.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.executeUpdate();
        dbUtils.close(null, pstmt, conn);
    }

    public Depart query(String type, String primayKey) throws Exception {
        String sql = null;
        if (type.equals("room")) {
            sql = "select * from roommessage where RNum = '" + primayKey + "'";
        } else if (type.equals("admin")) {
            sql = "select * from administrator where Id = '" + primayKey + "'";
        } else if (type.equals("user")) {
            sql = "select * from reged_user where Id = '" + primayKey + "'";
        }
        System.out.println(sql);
        Connection conn = dbUtils.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        Depart d1 = null;
        if (type.equals("room")) {
            String RName = rs.getString("RName");
            String RNum = rs.getString("RNum");
            String RMemberCount = rs.getString("RMemberCount");
            String RArea = rs.getString("RArea");
            String RPicDir = rs.getString("RPicDir");
            String RUsage = rs.getString("RUsage");
            String RUserName = rs.getString("RUserName");
            String RUserId = rs.getString("RUserId");
            String RUserTel = rs.getString("RUserTel");
            String RUserEmail = rs.getString("RUserEmail");
            String UsingDate = rs.getString("UsingDate");
            String StartingTime = rs.getString("StartingTime");
            String EndingTime = rs.getString("EndingTime");
            String RUserUsage = rs.getString("RUserUsage");
            String Flag = rs.getString("Flag");
            d1 = new Depart(type, RName, RNum, RMemberCount, RArea, RPicDir, RUsage, RUserName, RUserId, RUserTel, RUserEmail, UsingDate, StartingTime, EndingTime, RUserUsage, Flag);
        } else if (type.equals("admin")) {
            String AdName = rs.getString("Name");
            String AdId = rs.getString("Id");
            String AdGender = rs.getString("gender");
            String AdPwd = rs.getString("Pwd");
            String AdTel = rs.getString("Tel");
            String AdEmail = rs.getString("Email");
            d1 = new Depart(type, AdName, AdId, AdGender, AdPwd, AdTel, AdEmail);
        } else if (type.equals("user")) {
            String UserName = rs.getString("Name");
            String UserId = rs.getString("Id");
            String UserGender = rs.getString("gender");
            String UserPwd = rs.getString("Pwd");
            String UserTel = rs.getString("Tel");
            String UserEmail = rs.getString("Email");
            d1 = new Depart(type, UserName, UserId, UserGender, UserPwd, UserTel, UserEmail);
        }
        dbUtils.close(rs, pstmt, conn);
        return d1;
    }

    public List<Depart> queryAll(String type) throws Exception {
        List<Depart> DepartList = new ArrayList<Depart>();
        String sql = null;
        if (type.equals("room")) {
            sql = "select * from roommessage";
        } else if (type.equals("admin")) {
            sql = "select * from administrator";
        } else if (type.equals("user")) {
            sql = "select * from reged_user";
        }
        System.out.println(sql);
        Connection conn = dbUtils.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        if (type.equals("room")) {
            while (rs.next()) {
                String RName = rs.getString("RName");
                String RNum = rs.getString("RNum");
                String RMemberCount = rs.getString("RMemberCount");
                String RArea = rs.getString("RArea");
                String RPicDir = rs.getString("RPicDir");
                String RUsage = rs.getString("RUsage");
                String RUserName = rs.getString("RUserName");
                String RUserId = rs.getString("RUserId");
                String RUserTel = rs.getString("RUserTel");
                String RUserEmail = rs.getString("RUserEmail");
                String UsingDate = rs.getString("UsingDate");
                String StartingTime = rs.getString("StartingTime");
                String EndingTime = rs.getString("EndingTime");
                String RUserUsage = rs.getString("RUserUsage");
                String Flag = rs.getString("Flag");
                Depart d = new Depart(type, RName, RNum, RMemberCount, RArea, RPicDir, RUsage, RUserName, RUserId, RUserTel, RUserEmail, UsingDate, StartingTime, EndingTime, RUserUsage, Flag);
                DepartList.add(d);
            }
        } else if (type.equals("admin")) {
            while (rs.next()) {
                String AdName = rs.getString("Name");
                String AdId = rs.getString("Id");
                String AdGender = rs.getString("gender");
                String AdPwd = rs.getString("Pwd");
                String AdTel = rs.getString("Tel");
                String AdEmail = rs.getString("Email");
                Depart d = new Depart(type, AdName, AdId, AdGender, AdPwd, AdTel, AdEmail);
                DepartList.add(d);
            }
        } else if (type.equals("user")) {
            while (rs.next()) {
                String UserName = rs.getString("Name");
                String UserId = rs.getString("Id");
                String UserGender = rs.getString("gender");
                String UserPwd = rs.getString("Pwd");
                String UserTel = rs.getString("Tel");
                String UserEmail = rs.getString("Email");
                Depart d = new Depart(type, UserName, UserId, UserGender, UserPwd, UserTel, UserEmail);
                DepartList.add(d);
            }
        }
        dbUtils.close(rs, pstmt, conn);
        return DepartList;
    }
}
