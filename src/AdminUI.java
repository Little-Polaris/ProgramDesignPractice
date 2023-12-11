//Modified by MrQi on 2023/12/9.

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;
import java.time.LocalDateTime;

public class AdminUI extends JFrame implements ActionListener {
    private JMenuBar menuBar;
    private JMenu roomMenu, userMenu, reservationMenu ;
    private JMenuItem addRoomItem, modifyRoomItem, queryRoomItem, deleteRoomItem, addUserItem, addAdminItem, modifyUserItem, modifyAdminItem, deleteUserItem , queryReservationItem;
    private JTable table;
    private JButton passBtn, rejectBtn;
    private JLabel RUserNameLbl, RUserIdLbl, RUserTelLbl, RUserEmailLbl,roomLbl, UsingDateLbl, StartingTimeLbl, EndingTimeLbl,purLbl;
    private JLabel checkLbl, queryLbl;
    private JTextField queryField;
    private JButton queryBtn;
    private JRadioButton RNumRadio, RNameRadio, RUserIDRadio, RUserNameRadio;
    ButtonGroup group;
    private JTextField RUserNameField, RUserIdField, RUserTelField, RUserEmailField,roomField, UsingDateField, StartingTimeField, EndingTimeField;
    private JTextArea UsageField;
    private JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
    Admin adminData;
    Object[][]data;
    TableListener tableListener;

    public AdminUI(String account){
        Dao dao = new Dao();
        List<Admin> adminList;
        try {
            adminList = dao.query(new Admin(), "ID", account);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        adminData= adminList.getFirst();
        setTitle("会议室预约系统"+"当前管理员为："+account);
        setSize(900,450);
        WindowUtils.displayOnDesktopCenter(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        checkLbl = new JLabel("待审核列表");
        checkLbl.setBounds(20, 20, 100, 20);
        add(checkLbl);

        menuBar = new JMenuBar();
        roomMenu = new JMenu("会议室信息");
        addRoomItem = new JMenuItem("添加会议室");
        modifyRoomItem = new JMenuItem("修改会议室信息");
        queryRoomItem = new JMenuItem("查询所有会议室信息");
        deleteRoomItem = new JMenuItem("删除会议室");
        roomMenu.add(addRoomItem);
        roomMenu.addSeparator();
        roomMenu.add(modifyRoomItem);
        roomMenu.addSeparator();
        roomMenu.add(queryRoomItem);
        roomMenu.addSeparator();
        roomMenu.add(deleteRoomItem);
        addRoomItem.addActionListener(this);
        modifyRoomItem.addActionListener(this);
        queryRoomItem.addActionListener(this);
        deleteRoomItem.addActionListener(this);

        userMenu = new JMenu("用户信息");
        addUserItem = new JMenuItem("添加用户");
        addAdminItem = new JMenuItem("添加管理员");
        modifyUserItem = new JMenuItem("修改用户信息");
        modifyAdminItem = new JMenuItem("修改管理员信息");
        deleteUserItem = new JMenuItem("删除用户");
        userMenu.add(addUserItem);
        userMenu.addSeparator();
        userMenu.add(modifyUserItem);
        userMenu.addSeparator();
        userMenu.add(addAdminItem);
        userMenu.addSeparator();
        userMenu.add(modifyAdminItem);
        userMenu.addSeparator();
        userMenu.add(deleteUserItem);
        addUserItem.addActionListener(this);
        modifyUserItem.addActionListener(this);
        addAdminItem.addActionListener(this);
        modifyAdminItem.addActionListener(this);
        deleteUserItem.addActionListener(this);

        reservationMenu = new JMenu("预约信息");
        queryReservationItem = new JMenuItem("查询预约信息");
        reservationMenu.add(queryReservationItem);
        queryReservationItem.addActionListener(this);

        queryLbl = new JLabel("查询:");
        queryField = new JTextField(100);
        queryBtn = new JButton("查询");
        queryLbl.setBounds(20, 50, 50, 20);
        queryField.setBounds(80, 50, 100, 20);
        queryBtn.setBounds(200, 50, 60, 20);
        queryBtn.addActionListener(this);
        this.add(queryLbl);
        this.add(queryField);
        this.add(queryBtn);
        RNumRadio = new JRadioButton("按会议室编号查询");
        RNumRadio.setBounds(20, 80, 140, 20);
        RNameRadio = new JRadioButton("按会议室名称查询");
        RNameRadio.setBounds(160, 80, 140, 20);
        RUserIDRadio = new JRadioButton("按申请人ID查询");
        RUserIDRadio.setBounds(300, 80, 140, 20);
        RUserNameRadio = new JRadioButton("按申请人姓名查询");
        RUserNameRadio.setBounds(440, 80, 140, 20);
        group = new ButtonGroup();
        group.add(RNumRadio);
        group.add(RNameRadio);
        group.add(RUserIDRadio);
        group.add(RUserNameRadio);
        this.add(RNumRadio);
        this.add(RNameRadio);
        this.add(RUserIDRadio);
        this.add(RUserNameRadio);


        menuBar.add(roomMenu);
        menuBar.add(userMenu);
        menuBar.add(reservationMenu);
        this.setJMenuBar(menuBar);
        passBtn = new JButton("通过");
        rejectBtn = new JButton("驳回");
        passBtn.setBounds(160, 350, 100, 20);
        rejectBtn.setBounds(360, 350, 100, 20);
        add(passBtn);
        add(rejectBtn);

        RUserNameLbl = new JLabel("姓名:");
        RUserIdLbl = new JLabel("账号:");
        RUserTelLbl = new JLabel("联系方式:");
        RUserEmailLbl = new JLabel("邮箱:");
        roomLbl = new JLabel("会议室编号:");
        UsingDateLbl = new JLabel("借用日期:");
        StartingTimeLbl = new JLabel("借用时间:");
        EndingTimeLbl = new JLabel("结束时间:");
        purLbl = new JLabel("用途:");
        RUserNameField = new JTextField(20);
        RUserIdField = new JTextField(20);
        RUserTelField = new JTextField(20);
        RUserEmailField = new JTextField(20);
        roomField = new JTextField(20);
        UsingDateField = new JTextField(20);
        StartingTimeField = new JTextField(20);
        EndingTimeField = new JTextField(20);
        UsageField = new JTextArea(100,50);

        RUserNameLbl.setBounds(630, 50, 90, 20);
        RUserNameField.setBounds(700, 50, 140, 20);// 90=30+60

        RUserIdLbl.setBounds(630, 80, 90, 20); // 40=10+20+10
        RUserIdField.setBounds(700, 80, 140, 20);

        RUserTelLbl.setBounds(630, 140, 90, 20);
        RUserTelField.setBounds(700, 140, 140, 20);// 90=30+60

        RUserEmailLbl.setBounds(630, 110, 90, 20); // 40=10+20+10
        RUserEmailField.setBounds(700, 110, 140, 20);

        roomLbl.setBounds(630, 170, 90, 20);
        roomField.setBounds(700, 170, 140, 20);// 90=30+60

        UsingDateLbl.setBounds(630, 200, 90, 20);
        UsingDateField.setBounds(700, 200, 140, 20);// 90=30+60

        StartingTimeLbl.setBounds(630, 230, 90, 20);
        StartingTimeField.setBounds(700, 230, 140, 20);// 90=30+60

        EndingTimeLbl.setBounds(630, 260, 90, 20);
        EndingTimeField.setBounds(700, 260, 140, 20);// 90=30+60

        purLbl.setBounds(630, 290, 90, 20);
        UsageField.setBounds(700, 290, 140, 80);// 90=30+60
        //将上述桌面添加进来
        add(RUserNameLbl);
        add(RUserNameField);
        add(RUserIdLbl);
        add(RUserIdField);
        add(RUserTelLbl);
        add(RUserTelField);
        add(RUserEmailLbl);
        add(RUserEmailField);
        add(roomLbl);
        add(roomField);
        add(UsingDateLbl);
        add(UsingDateField);
        add(StartingTimeLbl);
        add(StartingTimeField);
        add(EndingTimeLbl);
        add(EndingTimeField);
        add(purLbl);
        add(UsageField);

        passBtn.addActionListener(this);
        rejectBtn.addActionListener(this);

        freshTable();
        setVisible(true);//显示窗体
    }
    public static void main (String[] args){
        new AdminUI("admin");
    }

    public void freshTable(){
        String[] columnsName = {"会议室编号", "会议室名称", "申请人ID", "申请人姓名", "备注"};
        List<Room> roomList = null;
        Dao dao = new Dao();
        try {
            roomList = dao.query(new Room());
        } catch (Exception ec) {
            ec.printStackTrace();
        }
        for (int i = 0; i < roomList.size(); i++) {
            if (roomList.get(i).getStatus().equals("0")) {
                roomList.remove(i);
                i--;
            } else {
                List<Reservation> reservationList = null;
                try {
                    reservationList = dao.query(new Reservation(), "RNum", roomList.get(i).getRNum());
                } catch (Exception ec) {
                    ec.printStackTrace();
                }
                Reservation reservation = reservationList.getLast();
                if (!reservation.getCheckStatus().equals("1")) {
                    roomList.remove(i);
                    i--;
                }
            }
        }
        int num = roomList.size();
        data = new Object[num][5];
        int index = 0;
        for (Room room : roomList) {
            data[index][0] = room.getRNum();
            data[index][1] = room.getRName();
            data[index][2] = room.getRUserId();
            data[index][3] = room.getRUserName();
            data[index][4] = room.getRUserUsage();
            index++;
        }
        table = new JTable(data, columnsName);
        tableListener = new TableListener();
        table.addMouseListener(tableListener);
        jScrollPane2.setBounds(20, 110, 600, 200);
        jScrollPane2.setViewportView(table);
        this.add(jScrollPane2);
    }

    public void freshTable(String key, String value){
        String[] columnsName = {"会议室编号", "会议室名称", "申请人ID", "申请人姓名", "备注"};
        List<Room> roomList = null;
        Dao dao = new Dao();
        try {
            roomList = dao.query(new Room(), key, value);
        } catch (Exception ec) {
            ec.printStackTrace();
        }
        for (int i = 0; i < roomList.size(); i++) {
            if (roomList.get(i).getStatus().equals("0")) {
                roomList.remove(i);
                i--;
            } else {
                List<Reservation> reservationList = null;
                try {
                    reservationList = dao.query(new Reservation(), "RNum", roomList.get(i).getRNum());
                } catch (Exception ec) {
                    ec.printStackTrace();
                }
                Reservation reservation = reservationList.getLast();
                if (!reservation.getCheckStatus().equals("1")) {
                    roomList.remove(i);
                    i--;
                }
            }
        }
        int num = roomList.size();
        data = new Object[num][5];
        int index = 0;
        for (Room room : roomList) {
            data[index][0] = room.getRNum();
            data[index][1] = room.getRName();
            data[index][2] = room.getRUserId();
            data[index][3] = room.getRUserName();
            data[index][4] = room.getRUserUsage();
            index++;
        }
        table = new JTable(data, columnsName);
        tableListener = new TableListener();
        table.addMouseListener(tableListener);
        jScrollPane2.setBounds(20, 110, 600, 200);
        jScrollPane2.setViewportView(table);
        this.add(jScrollPane2);
    }

    class TableListener extends MouseAdapter {
        String RNum, RUserName, RUserId, RUserTel, RUserEmail , UsingDate, StartingTime, EndingTime, RUserUsage;
        public void mouseClicked(final MouseEvent e) {
            int selRow = table.getSelectedRow();
            RNum = table.getValueAt(selRow, 0).toString().trim();
            Dao dao = new Dao();
            try {
                List<Room> roomList = dao.query(new Room(), "RNum", RNum);
                Room room = roomList.get(0);
                RUserName = room.getRUserName();
                RUserId = room.getRUserId();
                RUserTel = room.getRUserTel();
                RUserEmail = room.getRUserEmail();
                UsingDate = room.getUseDate();
                StartingTime = room.getStartTime();
                EndingTime = room.getEndTime();
                RUserUsage = room.getRUserUsage();
                RUserNameField.setText(RUserName);
                RUserIdField.setText(RUserId);
                RUserTelField.setText(RUserTel);
                RUserEmailField.setText(RUserEmail);
                roomField .setText(RNum);
                UsingDateField.setText(UsingDate);
                StartingTimeField.setText(StartingTime);
                EndingTimeField.setText(EndingTime);
                UsageField.setText(RUserUsage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == passBtn){
            if(table.getSelectedRow() == -1){
                return;
            }
            Dao dao = new Dao();
            try {
                List<Reservation> reservationList = dao.query(new Reservation(), "RNum", tableListener.RNum);
                Reservation reservation = reservationList.getLast();
                reservation.setCheckTime(LocalDateTime.now().toString());
                reservation.setCheckStatus("0");
                reservation.setNote(null);
                dao.update(reservation, reservation);
                JOptionPane.showMessageDialog(null, "审核通过");
                freshTable();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        } else if(e.getSource() == rejectBtn) {
            if(table.getSelectedRow() == -1){
                return;
            }
            Dao dao = new Dao();
            try {
                String note = JOptionPane.showInputDialog("请输入驳回理由");
                List<Reservation> reservationList = dao.query(new Reservation(), "RNum", tableListener.RNum);
                List<Room> roomList = dao.query(new Room(), "RNum", tableListener.RNum);
                Reservation reservation = reservationList.getLast();
                Room room = roomList.getFirst();
                reservation.setCheckTime(LocalDateTime.now().toString());
                reservation.setCheckStatus("2");
                reservation.setNote(note);
                dao.update(reservation, reservation);
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
                freshTable();
                RUserEmailField.setText(null);
                RUserTelField.setText(null);
                RUserIdField.setText(null);
                RUserNameField.setText(null);
                roomField.setText(null);
                UsingDateField.setText(null);
                StartingTimeField.setText(null);
                EndingTimeField.setText(null);
                UsageField.setText(null);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        } else if(e.getSource() == addRoomItem){
            new AddRoomFrame();
        } else if(e.getSource() == modifyRoomItem){
            new ModifyRoomFrame();
        } else if(e.getSource() == queryRoomItem){
            new QueryRoomFrame();
        } else if(e.getSource() == addUserItem){
            new AddUserFrame();
        } else if(e.getSource() == modifyUserItem){
            new ModifyUserInfoFrame();
        } else if(e.getSource() == addAdminItem){
            new AddAdminFrame();
        } else if(e.getSource() == modifyAdminItem){
            new ModifyAdminInfoFrame(adminData, this);
        }else if(e.getSource() == deleteUserItem){
            new DeleteUserFrame();
        } else if(e.getSource() == deleteRoomItem){
            new DeleteRoomFrame();
        } else if(e.getSource() == queryReservationItem){
            new QueryReservationFrame();
        } else if(e.getSource() == queryBtn){
            if(RNumRadio.isSelected()){
                freshTable("RNum", queryField.getText());
            } else if(RNameRadio.isSelected()){
                freshTable("RName", queryField.getText());
            } else if(RUserIDRadio.isSelected()){
                freshTable("RUserId", queryField.getText());
            } else if(RUserNameRadio.isSelected()){
                freshTable("RUserName", queryField.getText());
            } else {
                JOptionPane.showMessageDialog(null, "请选择查询方式");
            }
        }
    }
}

