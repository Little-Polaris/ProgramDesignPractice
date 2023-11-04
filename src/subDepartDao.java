import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xsw on 2017/6/2.
 面向用户的预定
 */
public class subDepartDao {
    private JdbcConnection dbUtils=new JdbcConnection();//数据库连接类对象
    //学院信息查询，返回值为subDepart对象
    public List<subDepart> findAll() throws Exception{
        //将查询的每一条数据封装为subDepart对象
        List<subDepart>dList=new ArrayList<>();
        String sql="select user_name, user_id, telNum, email, Rnum, using_date,starting_time,ending_time,RUsage from roommessage";//查询命令
        Connection conn =dbUtils.getConnection();//获得数据库连接对象
        PreparedStatement pstmt = conn.prepareStatement(sql);//创建对象
        ResultSet rs = pstmt.executeQuery();
        //将查询返回多条记录，对查询结果的每一行进行解析
        while(rs.next()){
            String user_name=rs.getString("user_name");
            String user_id=rs.getString("user_id");
            String telNum=rs.getString("telNum");
            String email=rs.getString("email");
            String Rnum=rs.getString("Rnum");
            String using_date=rs.getString("using_date");
            String starting_time=rs.getString("starting_time");
            String ending_time=rs.getString("ending_time");
            String RUsage=rs.getString("RUsage");
            //将数据表的记录封装为Depart对象，在把对象存在list集合中
            subDepart d=new subDepart(user_name, user_id, telNum, email, Rnum,using_date,starting_time,ending_time,RUsage);
            dList.add(d);
        }
        dbUtils.close(rs,pstmt,conn);
        return dList;
    }
    //
    public void save(subDepart d) throws Exception{
        // 插入数据的SQL语句
        String sql = "insert into confirm_sub(user_name, user_id, telNum, email, RNum,date,time,ending_time,RUsage) values(?,?,?,?,?,?,?,?,?)";
        // 获得数据库连接对象
        Connection conn = dbUtils.getConnection();
        // 创建PreparedStatement对象
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //为动态参数赋值
        pstmt.setString(1,d.getUser_name());
        pstmt.setString(2,d.getUser_id());
        pstmt.setString(3,d.getTelNum());
        pstmt.setString(4,d.getEmail());
        pstmt.setString(5,d.getRNum());
        pstmt.setString(6,d.getusing_date());
        pstmt.setObject(7,d.getstarting_time());
        pstmt.setObject(8,d.getending_time());
        pstmt.setString(9,d.getRUsage());
        //提交数据
        pstmt.executeUpdate();
        //关闭数据库的连接
        dbUtils.close(null, pstmt, conn);
    }

    public void con_save(subDepart d) throws Exception{
        // 插入数据的SQL语句
        String sql = "insert into sub_info(user_name, user_id, telNum, email, RNum,using_date,starting_time,ending_time,RUsage) values(?,?,?,?,?,?,?,?,?)";
        // 获得数据库连接对象
        Connection conn = dbUtils.getConnection();
        // 创建PreparedStatement对象
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //为动态参数赋值
        pstmt.setString(1,d.getUser_name());
        pstmt.setString(2,d.getUser_id());
        pstmt.setString(3,d.getTelNum());
        pstmt.setString(4,d.getEmail());
        pstmt.setString(5,d.getRNum());
        pstmt.setString(6,d.getusing_date());
        pstmt.setString(7,d.getstarting_time());
        pstmt.setString(8,d.getending_time());
        pstmt.setString(9,d.getRUsage());
        //提交数据
        pstmt.executeUpdate();
        //关闭数据库的连接
        dbUtils.close(null, pstmt, conn);
    }
}