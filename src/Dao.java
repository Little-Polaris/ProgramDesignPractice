//Created by MrQi on 2023/12/2.
//Modified by MrQi on 2023/12/9.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;


public class Dao {
    private JdbcConnection dbUtils = new JdbcConnection();
    public void insert(Admin admin) throws SQLException, ClassNotFoundException {
        String sql = "insert into admin values(?,?,?,?,?,?)";
        Connection conn = dbUtils.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, admin.getName());
        pstmt.setString(2, admin.getId());
        pstmt.setString(3, admin.getGender());
        pstmt.setString(4, admin.getPwd());
        pstmt.setString(5, admin.getTel());
        pstmt.setString(6, admin.getEmail());
        pstmt.executeUpdate();
        dbUtils.close(null, pstmt, conn);
    }
    public void insert(User user) throws SQLException, ClassNotFoundException {
        String sql = "insert into user values(?,?,?,?,?,?)";
        Connection conn = dbUtils.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, user.getName());
        pstmt.setString(2, user.getId());
        pstmt.setString(3, user.getGender());
        pstmt.setString(4, user.getPwd());
        pstmt.setString(5, user.getTel());
        pstmt.setString(6, user.getEmail());
        pstmt.executeUpdate();
        dbUtils.close(null, pstmt, conn);
    }
    public void insert(Room room) throws SQLException, ClassNotFoundException {
        String sql = "insert into room values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection conn = dbUtils.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, room.getRName());
        pstmt.setString(2, room.getRNum());
        pstmt.setString(3, room.getRMemberCount());
        pstmt.setString(4, room.getRArea());
        pstmt.setString(5, room.getRPicDir());
        pstmt.setString(6, room.getRUsage());
        pstmt.setString(7, room.getRUserName());
        pstmt.setString(8, room.getRUserId());
        pstmt.setString(9, room.getRUserTel());
        pstmt.setString(10, room.getRUserEmail());
        pstmt.setString(11, room.getUseDate());
        pstmt.setString(12, room.getStartTime());
        pstmt.setString(13, room.getEndTime());
        pstmt.setString(14, room.getRUserUsage());
        pstmt.setString(15, room.getStatus());
        pstmt.executeUpdate();
        dbUtils.close(null, pstmt, conn);
    }
    public void insert(Reservation reservation) throws SQLException, ClassNotFoundException {
        String sql = "insert into reservation values(?,?,?,?,?,?,?)";
        Connection conn = dbUtils.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, reservation.getResID());
        pstmt.setString(2, reservation.getUserID());
        pstmt.setString(3, reservation.getRNum());
        pstmt.setString(4, reservation.getSubTime());
        pstmt.setString(5, reservation.getCheckTime());
        pstmt.setString(6, reservation.getCheckStatus());
        pstmt.setString(7, reservation.getNote());
        pstmt.executeUpdate();
        dbUtils.close(null, pstmt, conn);
    }
    public void delete (Admin admin) throws SQLException, ClassNotFoundException {
        String sql = "delete from admin where Id = ?";
        Connection conn = dbUtils.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, admin.getId());
        pstmt.executeUpdate();
        dbUtils.close(null, pstmt, conn);
    }
    public void delete (User user) throws SQLException, ClassNotFoundException {
        String sql = "delete from user where Id = ?";
        Connection conn = dbUtils.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, user.getId());
        pstmt.executeUpdate();
        dbUtils.close(null, pstmt, conn);
    }
    public void delete (Room room) throws SQLException, ClassNotFoundException {
        String sql = "delete from room where RNum = ?";
        Connection conn = dbUtils.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, room.getRNum());
        pstmt.executeUpdate();
        dbUtils.close(null, pstmt, conn);
    }
    public void delete (Reservation reservation) throws SQLException, ClassNotFoundException {
        String sql = "delete from reservation where resID = ?";
        Connection conn = dbUtils.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, reservation.getResID());
        pstmt.executeUpdate();
        dbUtils.close(null, pstmt, conn);
    }
    public void update(Admin oldAdmin, Admin newAdmin) throws SQLException, ClassNotFoundException {
        String sql = "update admin set Name = ?, Id = ?, Gender = ?, Pwd = ?, Tel = ?, Email = ? where Id = ?";
        Connection conn = dbUtils.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, newAdmin.getName());
        pstmt.setString(2, newAdmin.getId());
        pstmt.setString(3, newAdmin.getGender());
        pstmt.setString(4, newAdmin.getPwd());
        pstmt.setString(5, newAdmin.getTel());
        pstmt.setString(6, newAdmin.getEmail());
        pstmt.setString(7, oldAdmin.getId());
        pstmt.executeUpdate();
        dbUtils.close(null, pstmt, conn);
    }
    public void update(User oldUser, User newUser ) throws SQLException, ClassNotFoundException {
        String sql = "update user set Name = ?, Id = ?, Gender = ?, Pwd = ?, Tel = ?, Email = ? where Id = ?";
        Connection conn = dbUtils.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, newUser.getName());
        pstmt.setString(2, newUser.getId());
        pstmt.setString(3, newUser.getGender());
        pstmt.setString(4, newUser.getPwd());
        pstmt.setString(5, newUser.getTel());
        pstmt.setString(6, newUser.getEmail());
        pstmt.setString(7, oldUser.getId());
        pstmt.executeUpdate();
        dbUtils.close(null, pstmt, conn);
    }
    public void update(Room oldRoom, Room newRoom) throws SQLException, ClassNotFoundException {
        String sql = "update room set RName = ?, RNum = ?, RMemberCount = ?, RArea = ?, RPicDir = ?, RUsage = ?, RUserName = ?, RUserId = ?, RUserTel = ?, RUserEmail = ?, UseDate = ?, StartTime = ?, EndTime = ?, RUserUsage = ?, Status = ? where RNum = ?";
        Connection conn = dbUtils.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, newRoom.getRName());
        pstmt.setString(2, newRoom.getRNum());
        pstmt.setString(3, newRoom.getRMemberCount());
        pstmt.setString(4, newRoom.getRArea());
        pstmt.setString(5, newRoom.getRPicDir());
        pstmt.setString(6, newRoom.getRUsage());
        pstmt.setString(7, newRoom.getRUserName());
        pstmt.setString(8, newRoom.getRUserId());
        pstmt.setString(9, newRoom.getRUserTel());
        pstmt.setString(10, newRoom.getRUserEmail());
        pstmt.setString(11, newRoom.getUseDate());
        pstmt.setString(12, newRoom.getStartTime());
        pstmt.setString(13, newRoom.getEndTime());
        pstmt.setString(14, newRoom.getRUserUsage());
        pstmt.setString(15, newRoom.getStatus());
        pstmt.setString(16, oldRoom.getRNum());
        pstmt.executeUpdate();
        dbUtils.close(null, pstmt, conn);
    }
    public void update(Reservation oldReservation, Reservation newReservation) throws SQLException, ClassNotFoundException {
        String sql = "update reservation set userID = ?, RNum = ?, subTime = ?, checkTime = ?, checkStatus = ?, note = ? where resID = ?";
        Connection conn = dbUtils.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, newReservation.getUserID());
        pstmt.setString(2, newReservation.getRNum());
        pstmt.setString(3, newReservation.getSubTime());
        pstmt.setString(4, newReservation.getCheckTime());
        pstmt.setString(5, newReservation.getCheckStatus());
        pstmt.setString(6, newReservation.getNote());
        pstmt.setString(7, oldReservation.getResID());
        pstmt.executeUpdate();
        dbUtils.close(null, pstmt, conn);
    }
    public List<Admin> query(Admin admin, String key, String value) throws SQLException, ClassNotFoundException {
        String sql = "select * from admin where " + key + " = '" + value + "'";
        Connection conn = dbUtils.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Admin> adminList = new ArrayList<>();
        while(rs.next()){
            String Name = rs.getString("Name");
            String Id = rs.getString("Id");
            String gender = rs.getString("gender");
            String Pwd = rs.getString("Pwd");
            String Tel = rs.getString("Tel");
            String Email = rs.getString("Email");
            Admin tempAdmin = new Admin(Name, Id, gender, Pwd, Tel, Email);
            adminList.add(tempAdmin);
        }
        dbUtils.close(rs, pstmt, conn);
        return adminList;
    }
    public List<User> query(User user, String key, String value) throws SQLException, ClassNotFoundException {
        String sql = "select * from user where " + key + " = '" + value + "'";
        Connection conn = dbUtils.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<User> adminList = new ArrayList<>();
        while(rs.next()){
            String Name = rs.getString("Name");
            String Id = rs.getString("Id");
            String gender = rs.getString("gender");
            String Pwd = rs.getString("Pwd");
            String Tel = rs.getString("Tel");
            String Email = rs.getString("Email");
            User tempUser = new User(Name, Id, gender, Pwd, Tel, Email);
            adminList.add(tempUser);
        }
        dbUtils.close(rs, pstmt, conn);
        return adminList;
    }
    public List<Room> query(Room room, String key, String value) throws SQLException, ClassNotFoundException {
        String sql = "select * from room where " + key + " = '" + value + "'";
        if(key.equals("RMemberCount") || key.equals("RArea")){
            sql = "select * from room where " + key + " >= '" + value + "'";
        }
        Connection conn = dbUtils.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Room> adminList = new ArrayList<>();
        while(rs.next()){
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
            String UseDate = rs.getString("UseDate");
            String StartTime = rs.getString("StartTime");
            String EndTime = rs.getString("EndTime");
            String RUserUsage = rs.getString("RUserUsage");
            String Status = rs.getString("Status");
            Room tempRoom = new Room(RName, RNum, RMemberCount, RArea, RPicDir, RUsage, RUserName, RUserId, RUserTel, RUserEmail, UseDate, StartTime, EndTime, RUserUsage, Status);
            adminList.add(tempRoom);
        }
        dbUtils.close(rs, pstmt, conn);
        return adminList;
    }
    public List<Reservation> query(Reservation reservation, String key, String value) throws SQLException, ClassNotFoundException {
        String sql = "select * from reservation where " + key + " = '" + value + "'";
        Connection conn = dbUtils.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Reservation> adminList = new ArrayList<>();
        while(rs.next()){
            String resID = rs.getString("resID");
            String userID = rs.getString("userID");
            String RNum = rs.getString("RNum");
            String subTime = rs.getString("subTime");
            String checkTime = rs.getString("checkTime");
            String checkStatus = rs.getString("checkStatus");
            String note = rs.getString("note");
            Reservation tempReservation = new Reservation(resID, userID, RNum, subTime, checkTime, checkStatus, note);
            adminList.add(tempReservation);
        }
        dbUtils.close(rs, pstmt, conn);
        return adminList;
    }
    public List<Admin> query(Admin admin) throws SQLException, ClassNotFoundException {
        String sql = "select * from admin";
        Connection conn = dbUtils.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Admin> adminList = new ArrayList<>();
        while(rs.next()){
            String Name = rs.getString("Name");
            String Id = rs.getString("Id");
            String gender = rs.getString("gender");
            String Pwd = rs.getString("Pwd");
            String Tel = rs.getString("Tel");
            String Email = rs.getString("Email");
            Admin tempAdmin = new Admin(Name, Id, gender, Pwd, Tel, Email);
            adminList.add(tempAdmin);
        }
        dbUtils.close(rs, pstmt, conn);
        return adminList;
    }
    public List<User> query(User user) throws SQLException, ClassNotFoundException {
        String sql = "select * from user";
        Connection conn = dbUtils.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<User> adminList = new ArrayList<>();
        while(rs.next()){
            String Name = rs.getString("Name");
            String Id = rs.getString("Id");
            String gender = rs.getString("gender");
            String Pwd = rs.getString("Pwd");
            String Tel = rs.getString("Tel");
            String Email = rs.getString("Email");
            User tempUser = new User(Name, Id, gender, Pwd, Tel, Email);
            adminList.add(tempUser);
        }
        dbUtils.close(rs, pstmt, conn);
        return adminList;
    }
    public List<Room> query(Room room) throws SQLException, ClassNotFoundException {
        String sql = "select * from room";
        Connection conn = dbUtils.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Room> adminList = new ArrayList<>();
        while(rs.next()){
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
            String UseDate = rs.getString("UseDate");
            String StartTime = rs.getString("StartTime");
            String EndTime = rs.getString("EndTime");
            String RUserUsage = rs.getString("RUserUsage");
            String Status = rs.getString("Status");
            Room tempRoom = new Room(RName, RNum, RMemberCount, RArea, RPicDir, RUsage, RUserName, RUserId, RUserTel, RUserEmail, UseDate, StartTime, EndTime, RUserUsage, Status);
            adminList.add(tempRoom);
        }
        dbUtils.close(rs, pstmt, conn);
        return adminList;
    }
    public List<Reservation> query(Reservation reservation) throws SQLException, ClassNotFoundException {
        String sql = "select * from reservation";
        Connection conn = dbUtils.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Reservation> adminList = new ArrayList<>();
        while(rs.next()){
            String resID = rs.getString("resID");
            String userID = rs.getString("userID");
            String RNum = rs.getString("RNum");
            String subTime = rs.getString("subTime");
            String checkTime = rs.getString("checkTime");
            String checkStatus = rs.getString("checkStatus");
            String note = rs.getString("note");
            Reservation tempReservation = new Reservation(resID, userID, RNum, subTime, checkTime, checkStatus, note);
            adminList.add(tempReservation);
        }
        dbUtils.close(rs, pstmt, conn);
        return adminList;
    }
}
