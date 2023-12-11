//Created by MrQi on 2023/12/9.

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class AllResvFrame extends JDialog implements ActionListener {
    private User userData;
    private JTable table;
    private JScrollPane jScrollPane = new javax.swing.JScrollPane();
    private JButton cancelBtn;
    private JLabel titleLbl;
    private Object[][]data;
    private TableListener tableListener;
    private UserUI userUI;
    private static final long serialVersionUID = 1L;
    public AllResvFrame(User userData, UserUI userUI) {

        this.userData = userData;
        this.userUI = userUI;

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

        jScrollPane.setBounds(100, 50, 600, 400);
        this.add(jScrollPane);

        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        cancelBtn = new JButton("取消预定");
        cancelBtn.setBounds(360, 500, 100, 20);
        cancelBtn.addActionListener(this);
        add(cancelBtn);

        freshTable();

        setVisible(true);
    }

    public static void main(String[] args) {
        new AllResvFrame( null, null);
    }

    private void freshTable(){
        String[] columnsName={"会议室编号","会议室名称","移交时间","审核时间", "预定状态", "备注"};
        try{
            Dao dao = new Dao();
            List<Reservation> reservationList = dao.query(new Reservation(), "userID", userData.getId());
            int columnsNum = reservationList.size();
            data = new Object[columnsNum][6];
            int index = 0;
            for (Reservation reservation : reservationList) {
                List<Room> roomList = dao.query(new Room(), "RNum", reservation.getRNum());
                Room room = roomList.getFirst();
                data[index][0] = room.getRNum();
                data[index][1] = room.getRName();
                data[index][2] = reservation.getSubTime();
                data[index][3] = reservation.getCheckTime();
                if(reservation.getCheckStatus().equals("0")){
                    data[index][4] = "预约成功";
                }else if (reservation.getCheckStatus().equals("1")){
                    data[index][4] = "审核中";
                } else if(reservation.getCheckStatus().equals("2")){
                    data[index][4] = "预约驳回";
                } else if(reservation.getCheckStatus().equals("3")){
                    data[index][4] = "已取消预约";
                } else if(reservation.getCheckStatus().equals("4")){
                    data[index][4] = "已签退";
                }
                data[index][5] = reservation.getNote();
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
        jScrollPane.setViewportView(table);
    }


    public void actionPerformed(ActionEvent e) {
        if(table.getSelectedRow() == -1){
            return ;
        }
        if (e.getSource() == cancelBtn) {
            if((table.getValueAt(table.getSelectedRow(), 4).equals("预约成功") || table.getValueAt(table.getSelectedRow(), 4).equals("审核中"))) {
                Dao dao = new Dao();
                Room room = null;
                Reservation reservation = null;
                try {
                    List<Room> roomList = dao.query(new Room(), "RNum", tableListener.RNum);
                    List<Reservation> reservationList = dao.query(new Reservation(), "RNum", tableListener.RNum);
                    room = roomList.getFirst();
                    reservation = reservationList.getLast();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                room.setRUserName(null);
                room.setRUserId(null);
                room.setRUserTel(null);
                room.setRUserEmail(null);
                room.setUseDate(null);
                room.setStartTime(null);
                room.setEndTime(null);
                room.setRUserUsage(null);
                room.setStatus("0");
                reservation.setCheckStatus("3");
                try {
                    dao.update(room, room);
                    dao.update(reservation, reservation);
                    JOptionPane.showMessageDialog(null, "取消预定成功");
                    freshTable();
                    userUI.freshTable();
                } catch (Exception ec) {
                    JOptionPane.showMessageDialog(null, "保存时出现异常，异常原因为:" + ec.getMessage());
                    ec.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "该状态不可取消预定");
            }
        }
        else {
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
