//Created by MrQi on 2023/12/10.

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class DeleteUserFrame extends JDialog implements ActionListener {
    private JTable table;
    private JLabel titleLbl, queryLbl;
    private JTextField queryField;
    private JScrollPane jScrollPane = new javax.swing.JScrollPane();
    private JButton queryBtn, deleteBtn;
    private JRadioButton UserNameRadio, UserIDRadio;
    private User deleteUser;
    ButtonGroup group;
    private Object[][]data;
    private static final long serialVersionUID = 1L;
    public DeleteUserFrame() {
        setTitle("删除用户");
        setSize(800, 600);
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        int x = (int) (width - this.getWidth()) / 2;
        int y = (int) (height - this.getHeight()) / 2;
        setLocation(x, y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        titleLbl = new JLabel("用户信息");
        titleLbl.setBounds(373, 40, 100, 20);
        this.add(titleLbl);

        queryLbl = new JLabel("查询:");
        queryField = new JTextField(100);
        queryBtn = new JButton("查询");
        UserNameRadio = new JRadioButton("按用户姓名查询");
        UserIDRadio = new JRadioButton("按用户账号查询");
        group = new ButtonGroup();
        group.add(UserNameRadio);
        group.add(UserIDRadio);
        queryBtn.setBounds(320, 80, 80, 20);
        queryLbl.setBounds(100, 80, 110, 20);
        queryField.setBounds(210, 80, 100, 20);
        UserNameRadio.setBounds(100, 110, 150, 20);
        UserIDRadio.setBounds(250, 110, 150, 20);
        queryBtn.addActionListener(this);
        this.add(queryBtn);
        this.add(queryField);
        this.add(queryLbl);
        this.add(UserNameRadio);
        this.add(UserIDRadio);

        deleteBtn = new JButton("删除");
        deleteBtn.setBounds(360, 500, 100, 20);
        deleteBtn.addActionListener(this);
        this.add(deleteBtn);

        freshTable();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new DeleteUserFrame();
    }
    private void freshTable(){
        String[] columnsName={"用户姓名","用户账号","用户性别","用户密码", "用户电话","用户邮箱"};
        try{
            Dao dao = new Dao();
            List<User> userList = dao.query(new User());
            int columnsNum = userList.size();
            data = new Object[columnsNum][6];
            int index = 0;
            for (User user : userList) {
                data[index][0] = user.getName();
                data[index][1] = user.getId();
                data[index][2] = user.getGender();
                data[index][3] = user.getPwd();
                data[index][4] = user.getTel();
                data[index][6] = user.getEmail();
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
        TableListener tableListener =new TableListener();
        table.addMouseListener(tableListener);
        jScrollPane.setBounds(100, 150, 600, 300);
        jScrollPane.setViewportView(table);
        this.add(jScrollPane);
    }

    private void freshTable(String key, String value){
        String[] columnsName={"用户姓名","用户账号","用户性别","用户密码", "用户电话","用户邮箱"};
        try{
            Dao dao = new Dao();
            List<User> userList = dao.query(new User(), key, value);
            int columnsNum = userList.size();
            data = new Object[columnsNum][6];
            int index = 0;
            for (User user : userList) {
                data[index][0] = user.getName();
                data[index][1] = user.getId();
                data[index][2] = user.getGender();
                data[index][3] = user.getPwd();
                data[index][4] = user.getTel();
                data[index][6] = user.getEmail();
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
        TableListener tableListener =new TableListener();
        table.addMouseListener(tableListener);
        jScrollPane.setBounds(100, 150, 600, 300);
        jScrollPane.setViewportView(table);
        this.add(jScrollPane);
    }

    class TableListener extends MouseAdapter {
        String userID;
        public void mouseClicked(final MouseEvent e) {
            int selRow = table.getSelectedRow();
            if(selRow == -1){
                return ;
            }
            userID = table.getValueAt(selRow, 1).toString().trim();
            deleteUser = new User();
            deleteUser.setId(userID);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == queryBtn) {
            if(queryField.getText().trim().equals("")){
                freshTable();
                return;
            }
            if(UserNameRadio.isSelected()){
                String RNum = queryField.getText().trim();
                freshTable("RNum", RNum);
            }else if(UserIDRadio.isSelected()){
                String RName = queryField.getText().trim();
                freshTable("RName", RName);
            }else{
                JOptionPane.showMessageDialog(null, "请选择查询方式");
            }
        }else if(e.getSource() == deleteBtn){
            if(deleteUser == null){
                JOptionPane.showMessageDialog(null, "请选择要删除的用户");
                return;
            }
            Dao dao = new Dao();
            try {
                List<Reservation> reservationList = dao.query(new Reservation(), "userID", deleteUser.getId());
                for(Reservation reservation : reservationList){
                    List<Room> roomList = dao.query(new Room(), "RNum", reservation.getRNum());
                    Room room = roomList.getFirst();
                    room.setRUserName(null);
                    room.setRUserId(null);
                    room.setRUserTel(null);
                    room.setRUserEmail(null);
                    room.setUseDate(null);
                    room.setStartTime(null);
                    room.setEndTime(null);
                    room.setRUserUsage(null);
                    room.setStatus("0");
                    dao.update(room, room);
                    dao.delete(reservation);
                }
                dao.delete(deleteUser);
                JOptionPane.showMessageDialog(null, "删除成功");
                freshTable();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else {
            this.dispose();
        }
    }
}
