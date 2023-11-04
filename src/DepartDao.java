/**
 * Created by xsw on 2017/5/30.
 */
//完成教室信息添加、修改、查询等操作的的业务逻辑处理类

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;

public class DepartDao {
    private JdbcConnection dbUtils=new JdbcConnection();//数据库连接类对象
    //学院信息查询，返回值为Depart对象
    public List<Depart>findAll() throws Exception{
        //将查询的每一条数据封装为Depart对象
        List<Depart>dList=new ArrayList<Depart>();
        String sql="select RName,RNum,RMember_count,RArea,RPic_dir from RoomMessage";//查询命令
        Connection conn =dbUtils.getConnection();//获得数据库连接对象
        PreparedStatement pstmt = conn.prepareStatement(sql);//创建对象
        ResultSet rs = pstmt.executeQuery();
        //将查询返回多条记录，对查询结果的每一行进行解析
        while(rs.next()){
            String RName=rs.getString("RName");
            String RNum=rs.getString("RNum");
            String RMember_count=rs.getString("RMember_count");
            String RArea=rs.getString("RArea");
            String RPic_dir=rs.getString("RPic_dir");
            //String use_time=rs.getString("use_time");
            //将数据表的记录封装为Depart对象，在把对象存在list集合中
            Depart d=new Depart(RName,RNum,RMember_count,RArea,RPic_dir);
            dList.add(d);
        }
        dbUtils.close(rs,pstmt,conn);
        return dList;
    }
    //
   public void save(Depart d) throws Exception{
        // 插入数据的SQL语句
        String sql = "insert into clr_info(RName,RNum,RMember_count,RArea,RPic_dir) values(?,?,?,?,?)";
        // 获得数据库连接对象
        Connection conn = dbUtils.getConnection();
        // 创建PreparedStatement对象
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //为动态参数赋值
        pstmt.setString(1,d.getRName());
        pstmt.setString(2,d.getRNum());
        pstmt.setString(3,d.getRMember_count());
        pstmt.setString(4,d.getRArea());
        pstmt.setString(5,d.getRPic_dir());
        //pstmt.setString(6,d.getUse_time());
        //提交数据
        pstmt.executeUpdate();
        //关闭数据库的连接
        dbUtils.close(null, pstmt, conn);
    }

    /*public void modify(Depart d) throws Exception{
        //插入数据的SQL语句
        String sql="delete from clr_info where oid=?";
        //插入数据的SQL语句
        Connection conn = dbUtils.getConnection();
        // 创建PreparedStatement对象
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //为动态参数赋值
        pstmt.setString(1,d.getRName());
        pstmt.setString(2,d.getRNum());
        pstmt.setString(3,d.getRMember_count());
        pstmt.setString(4,d.getRArea());
        pstmt.setString(5,d.getRPic_dir());
        //pstmt.setString(6,d.getUse_time());
        //提交数据
        pstmt.executeUpdate();
        //关闭数据库的连接
        dbUtils.close(null, pstmt, conn);
    }*/
    public void modify(Depart d) throws  Exception{
        String sql = "update clr_info set RNum='"+d.getRNum()+"',RMember_count='"+d.getRMember_count()+"',RArea='"+d.getRArea()+"',RPic_dir='"+d.getRPic_dir()+"' where RName='"+d.getRName()+"'";
        System.out.println(sql);
        Connection conn = dbUtils.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.executeUpdate();
        dbUtils.close(null,pstmt,conn);
    }
    public void delete(Depart d)throws Exception{
        String sql = "delete from clr_info where RName='"+d.getRName()+"'";
        System.out.println(sql);
        Connection conn = dbUtils.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.executeUpdate();
        dbUtils.close(null,pstmt,conn);
    }

    public void query(Depart d) throws Exception{
        String sql="select from clr_info where RNum='"+d.getRNum()+"' and RMember_count='"+d.getRMember_count()+"' and RArea='"+d.getRArea()+"'and RPic_dir='"+d.getRPic_dir()+"' and RName='"+d.getRName()+"'";
        System.out.println(sql);
        Connection conn = dbUtils.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.executeUpdate();
        dbUtils.close(null,pstmt,conn);
    }
}
