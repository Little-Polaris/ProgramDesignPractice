//Created by MrQi on 2023/12/9.

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class SignOutFrame extends JDialog implements ActionListener {
    private User userData;
    private JTable table;
    private JScrollPane jScrollPane = new javax.swing.JScrollPane();
    private JButton signOutBtn;
    private JLabel titleLbl;
    private Object[][]data;
    private TableListener tableListener;
    private UserUI userUI;
    private static final long serialVersionUID = 1L;
    public SignOutFrame(User userData, UserUI userUI) {

        this.userData = userData;
        this.userUI = userUI;

        setModal(true);
        setTitle("会议室签退");
        setSize(800, 600);
        //WindowUtils.displayOnDesktopCenter(this);//窗体居中的方法
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        int x = (int) (width - this.getWidth()) / 2;
        int y = (int) (height - this.getHeight()) / 2;
        setLocation(x, y);
        setResizable(false);
        setLayout(null);

        titleLbl = new JLabel("已预定会议室");
        titleLbl.setBounds(373, 30, 100, 20);
        this.add(titleLbl);

        jScrollPane.setBounds(100, 50, 600, 400);
        this.add(jScrollPane);

        setBackground(Color.WHITE);

        signOutBtn = new JButton("签退");
        signOutBtn.setBounds(360, 500, 100, 20);
        signOutBtn.addActionListener(this);
        add(signOutBtn);

        freshTable();

        setVisible(true);
    }

    public static void main(String[] args) {
        new SignOutFrame( null, null);
    }

    private void freshTable(){
        String[] columnsName={"会议室编号","会议室名称","可容纳人数","会议室面积", "会议室类型","会议室图片"};
        try{
            Dao dao = new Dao();
            List<Room> roomList = dao.query(new Room(), "RUserName", userData.getName());
            int columnsNum = roomList.size();
            for(int i = 0; i < columnsNum; i++){
                if(roomList.get(i).getStatus().equals("1")){
                    List<Reservation> roomList1 = dao.query(new Reservation(), "RNum", roomList.get(i).getRNum());
                    Reservation reservation = roomList1.getLast();
                    if(reservation.getCheckStatus().equals("0")){
                        continue;
                    }
                }
                roomList.remove(i);
                i--;
                columnsNum--;
            }
            data = new Object[columnsNum][5];
            int index = 0;
            for (Room room : roomList) {
                data[index][0] = room.getRNum();
                data[index][1] = room.getRName();
                data[index][2] = room.getRMemberCount();
                data[index][3] = room.getRArea();
                data[index][4] = room.getRUsage();
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
        if (e.getSource() == signOutBtn) {
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
            reservation.setCheckStatus("4");
            try {
                dao.update(room, room);
                dao.update(reservation, reservation);
                JOptionPane.showMessageDialog(null, "签退成功");
                this.dispose();
                userUI.freshTable();
            } catch (Exception ec) {
                JOptionPane.showMessageDialog(null, "保存时出现异常，异常原因为:" + ec.getMessage());
                ec.printStackTrace();
            }
        }
        else {
            this.dispose();
        }
    }

    class TableListener extends MouseAdapter {
        String RName, RNum, RMemberCOunt, RArea, RUsage;
        public void mouseClicked( MouseEvent e) {
            int selRow = table.getSelectedRow();
            RNum = table.getValueAt(selRow, 0).toString().trim();
            RName = table.getValueAt(selRow, 1).toString().trim();
            RMemberCOunt = table.getValueAt(selRow, 2).toString().trim();
            RArea = table.getValueAt(selRow, 3).toString().trim();
            RUsage = table.getValueAt(selRow, 4).toString().trim();
        }
    }
}
