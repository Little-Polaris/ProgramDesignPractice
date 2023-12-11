//Created by MrQi on 2023/12/9.
//Modified by MrQi on 2023/12/10.

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class QueryReservationFrame extends JDialog implements ActionListener {
    private JTable table;
    private JScrollPane jScrollPane = new javax.swing.JScrollPane();
    private JLabel titleLbl, queryLbl;
    private JButton queryBtn;
    private JRadioButton queryByRNum, queryByRName, queryByUserName, queryByUserId, queryByStatus;
    private JTextField queryField;
    private ButtonGroup buttonGroup;
    private Object[][]data;
    private TableListener tableListener;
    private static final long serialVersionUID = 1L;
    public QueryReservationFrame() {

        setTitle("预订信息");
        setSize(800, 600);
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        int x = (int) (width - this.getWidth()) / 2;
        int y = (int) (height - this.getHeight()) / 2;
        setLocation(x, y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        titleLbl = new JLabel("预订信息");
        titleLbl.setBounds(373, 30, 100, 20);
        this.add(titleLbl);

        queryLbl = new JLabel("查询:");
        queryField = new JTextField(20);
        queryBtn = new JButton("查询");
        queryByRNum = new JRadioButton("按会议室编号查询");
        queryByRName = new JRadioButton("按会议室名称查询");
        queryByUserName = new JRadioButton("按使用人姓名查询");
        queryByUserId = new JRadioButton("按使用人账号查询");
        queryByStatus = new JRadioButton("按预定状态查询");
        buttonGroup = new ButtonGroup();

        buttonGroup.add(queryByRNum);
        buttonGroup.add(queryByRName);
        buttonGroup.add(queryByUserName);
        buttonGroup.add(queryByUserId);
        buttonGroup.add(queryByStatus);

        queryLbl.setBounds(100, 500, 50, 20);
        queryField.setBounds(150, 500, 100, 20);
        queryBtn.setBounds(260, 500, 60, 20);
        queryByRNum.setBounds(100, 530, 120, 20);
        queryByRName.setBounds(220, 530, 120, 20);
        queryByUserName.setBounds(340, 530, 120, 20);
        queryByUserId.setBounds(460, 530, 120, 20);
        queryByStatus.setBounds(580, 530, 120, 20);

        this.add(queryLbl);
        this.add(queryField);
        this.add(queryBtn);
        this.add(queryByRNum);
        this.add(queryByRName);
        this.add(queryByUserName);
        this.add(queryByUserId);
        this.add(queryByStatus);

        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        freshTable();

        setVisible(true);
    }

    public static void main(String[] args) {
        new QueryReservationFrame();
    }

    private void freshTable(){
        String[] columnsName={"使用人姓名", "使用人账号", "会议室编号", "会议室名称", "移交时间", "审核时间", "预定状态", "备注"};
        try{
            Dao dao = new Dao();
            List<Reservation> reservationList = dao.query(new Reservation());
            int columnsNum = reservationList.size();
            data = new Object[columnsNum][8];
            int index = 0;
            for (Reservation reservation : reservationList) {
                List<Room> roomList = dao.query(new Room(), "RNum", reservation.getRNum());
                Room room = roomList.getFirst();
                List<User> userList = dao.query(new User(), "id", reservation.getUserID());
                User user = userList.getFirst();
                data[index][0] = user.getName();
                data[index][1] = user.getId();
                data[index][2] = room.getRNum();
                data[index][3] = room.getRName();
                data[index][4] = reservation.getSubTime();
                data[index][5] = reservation.getCheckTime();
                if(reservation.getCheckStatus().equals("0")){
                    data[index][6] = "预约成功";
                }else if (reservation.getCheckStatus().equals("1")){
                    data[index][6] = "审核中";
                } else if(reservation.getCheckStatus().equals("2")){
                    data[index][6] = "预约驳回";
                } else if(reservation.getCheckStatus().equals("3")){
                    data[index][6] = "已取消预约";
                } else if(reservation.getCheckStatus().equals("4")){
                    data[index][6] = "已签退";
                }
                data[index][7] = reservation.getNote();
                index++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        table = new JTable(data,columnsName);
        table.setModel(new DefaultTableModel(data,columnsName){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        });
        tableListener =new TableListener();
        table.addMouseListener(tableListener);
        jScrollPane.setBounds(100, 80, 600, 400);
        jScrollPane.setViewportView(table);
        this.add(jScrollPane);
    }

    private void freshTable(String key, String value){
        String[] columnsName={"使用人姓名", "使用人账号", "会议室编号", "会议室名称", "移交时间", "审核时间", "预定状态", "备注"};
        try{
            Dao dao = new Dao();
            List<Reservation> reservationList = dao.query(new Reservation(), key, value);
            int columnsNum = reservationList.size();
            data = new Object[columnsNum][8];
            int index = 0;
            for (Reservation reservation : reservationList) {
                List<Room> roomList = dao.query(new Room(), "RNum", reservation.getRNum());
                Room room = roomList.getFirst();
                List<User> userList = dao.query(new User(), "id", reservation.getUserID());
                User user = userList.getFirst();
                data[index][0] = user.getName();
                data[index][1] = user.getId();
                data[index][2] = room.getRNum();
                data[index][3] = room.getRName();
                data[index][4] = reservation.getSubTime();
                data[index][5] = reservation.getCheckTime();
                if(reservation.getCheckStatus().equals("0")){
                    data[index][6] = "预约成功";
                }else if (reservation.getCheckStatus().equals("1")){
                    data[index][6] = "审核中";
                } else if(reservation.getCheckStatus().equals("2")){
                    data[index][6] = "预约驳回";
                } else if(reservation.getCheckStatus().equals("3")){
                    data[index][6] = "已取消预约";
                } else if(reservation.getCheckStatus().equals("4")){
                    data[index][6] = "已签退";
                }
                data[index][7] = reservation.getNote();
                index++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        table = new JTable(data,columnsName);
        table.setModel(new DefaultTableModel(data,columnsName){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        });
        tableListener =new TableListener();
        table.addMouseListener(tableListener);
        jScrollPane.setBounds(100, 80, 600, 400);
        jScrollPane.setViewportView(table);
        this.add(jScrollPane);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == queryBtn) {
            if(queryField.getText().trim().equals("")){
                freshTable();
                return ;
            }
            if(queryByRNum.isSelected()) {
                String RNum = queryField.getText();
                if (RNum == null || "".equals(RNum.trim())) {
                    JOptionPane.showMessageDialog(null, "请输入查询信息");
                    return;
                }
                freshTable("RNum", RNum);
            } else if (queryByRName.isSelected()) {
                String RName = queryField.getText();
                if (RName == null || "".equals(RName.trim())) {
                    JOptionPane.showMessageDialog(null, "请输入查询信息");
                    return;
                }
                freshTable("RName", RName);
            } else if (queryByUserId.isSelected()){
                String UserId = queryField.getText();
                if (UserId == null || "".equals(UserId.trim())) {
                    JOptionPane.showMessageDialog(null, "请输入查询信息");
                    return;
                }
                freshTable("userID", UserId);
            } else if (queryByUserName.isSelected()){
                String UserName = queryField.getText();
                if (UserName == null || "".equals(UserName.trim())) {
                    JOptionPane.showMessageDialog(null, "请输入查询信息");
                    return;
                }
                freshTable("userName", UserName);
            } else if (queryByStatus.isSelected()){
                String Status = queryField.getText();
                if (Status == null || "".equals(Status.trim())) {
                    JOptionPane.showMessageDialog(null, "请输入查询信息");
                    return;
                }
                freshTable("checkStatus", Status);
            }
        } else {
            this.dispose();//关闭当前窗口
        }
    }

    class TableListener extends MouseAdapter {
        String RNum, RName, subTime, checkTime, status, note;
        public void mouseClicked( MouseEvent e) {
            int selRow = table.getSelectedRow();
            RNum = table.getValueAt(selRow, 0) != null ? table.getValueAt(selRow, 0).toString().trim() : null;
            RName = table.getValueAt(selRow, 1) != null ? table.getValueAt(selRow, 1).toString().trim() : null;
            subTime = table.getValueAt(selRow, 2) != null ? table.getValueAt(selRow, 2).toString().trim() : null;
            checkTime = table.getValueAt(selRow, 3) != null ? table.getValueAt(selRow, 3).toString().trim() : null;
            status = table.getValueAt(selRow, 4) != null ? table.getValueAt(selRow, 4).toString().trim() : null;
            note = table.getValueAt(selRow, 5) != null ? table.getValueAt(selRow, 5).toString().trim() : null;
        }
    }
}
