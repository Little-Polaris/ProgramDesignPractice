//Created by MrQi on 2023/12/9.
//Modified by MrQi on 2023/12/10.

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class UserUI extends JFrame implements ActionListener{
    private JMenuBar menuBar;
    private JMenu roomMenu, resvInfoMenu, userMenu, finishMenu;
    private JMenuItem modifyUserItem, queryAllItem, allResvItem, signOutMenuItem;
    private JLabel titleLbl, queryLbl;
    private JTextField queryField;
    private JTable table;
    private JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
    private JButton reserveBtn, freshBtn, queryBtn;
    private JRadioButton RNumRadio, RNameRadio, RMemberCountRadio, RAreaRadio;
    ButtonGroup group;
    TableListener tableListener;
    Object[][]data;
    User userData;
    public UserUI(String account) throws Exception {
        setTitle("会议室预约系统"+"当前用户为："+account);
        setSize(800,600);
        WindowUtils.displayOnDesktopCenter(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        Dao dao = new Dao();
        userData = dao.query(new User(), "ID", account).get(0);

        menuBar = new JMenuBar();

        roomMenu = new JMenu("会议室信息");
        queryAllItem =new JMenuItem("所有会议室状态");
        roomMenu.add(queryAllItem);
        queryAllItem.addActionListener(this);

        resvInfoMenu = new JMenu("预订信息");
        allResvItem = new JMenuItem("所有预订信息");
        resvInfoMenu.add(allResvItem);
        allResvItem.addActionListener(this);

        userMenu =new JMenu("个人账户");
        modifyUserItem = new JMenuItem("修改信息");
        userMenu.add(modifyUserItem);
        modifyUserItem.addActionListener(this);

        finishMenu = new JMenu("会议室签退");
        signOutMenuItem = new JMenuItem("归还会议室");
        finishMenu.add(signOutMenuItem);
        signOutMenuItem.addActionListener(this);

        menuBar.add(roomMenu);
        menuBar.add(resvInfoMenu);
        menuBar.add(userMenu);
        menuBar.add(finishMenu);
        this.setJMenuBar(menuBar);

        titleLbl = new JLabel("可用会议室列表");
        titleLbl.setBounds(50, 80, 100, 20);
        freshBtn = new JButton("刷新");
        freshBtn.setBounds(160, 80, 100, 20);
        freshBtn.addActionListener(this);
        this.add(freshBtn);
        this.add(titleLbl);

        queryLbl = new JLabel("查询:");
        queryField = new JTextField(100);
        queryBtn = new JButton("查询");
        queryLbl.setBounds(50, 40, 50, 20);
        queryField.setBounds(110, 40, 100, 20);
        queryBtn.setBounds(230, 40, 100, 20);
        queryBtn.addActionListener(this);
        this.add(queryLbl);
        this.add(queryField);
        this.add(queryBtn);

        RNumRadio = new JRadioButton("按会议室编号查询");
        RNumRadio.setBounds(50, 60, 140, 20);
        RNameRadio = new JRadioButton("按会议室名称查询");
        RNameRadio.setBounds(200, 60, 140, 20);
        RMemberCountRadio = new JRadioButton("按会议室容纳人数查询");
        RMemberCountRadio.setBounds(350, 60, 150, 20);
        RAreaRadio = new JRadioButton("按会议室面积查询");
        RAreaRadio.setBounds(510, 60, 150, 20);
        group = new ButtonGroup();
        group.add(RNumRadio);
        group.add(RNameRadio);
        group.add(RMemberCountRadio);
        group.add(RAreaRadio);
        this.add(RNumRadio);
        this.add(RNameRadio);
        this.add(RMemberCountRadio);
        this.add(RAreaRadio);

        reserveBtn = new JButton("预定");
        reserveBtn.setBounds(600, 500, 100, 20);
        add(reserveBtn);
        reserveBtn.addActionListener(this);

        jScrollPane2.setBounds(50, 100, 700, 350);
        this.add(jScrollPane2);

        freshTable();

        setVisible(true);
    }
    public void freshTable(){
        String[] columnsName={"会议室编号","会议室名称","可容纳人数","会议室面积", "会议室类型","会议室图片"};
        try{
            Dao dao = new Dao();
            List<Room> dList = dao.query(new Room(), "Status", "0");
            int columnNum = dList.size();
            data=new Object[columnNum][6];
            int index=0;
            for(Room room:dList){
                data[index][0]=room.getRNum();
                data[index][1]=room.getRName();
                data[index][2]=room.getRMemberCount();
                data[index][3]=room.getRArea();
                data[index][4]=room.getRUsage();
                index++;
            }
        } catch (Exception ec){
            ec.printStackTrace();
        }
        table = new JTable(data,columnsName);
        table.setModel(new DefaultTableModel(data,columnsName){
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        });
        tableListener =new TableListener();
        table.addMouseListener(tableListener);

        jScrollPane2.setViewportView(table);
    }

    public void freshTable(String key, String value){
        String[] columnsName={"会议室编号","会议室名称","可容纳人数","会议室面积", "会议室类型","会议室图片"};
        try{
            Dao dao = new Dao();
            List<Room> dList = dao.query(new Room(), key, value);
            int columnNum = 0;
            for(int i = 0; i < dList.size(); i++){
                if(!dList.get(i).getStatus().equals("0")){
                    dList.remove(i);
                    i--;
                }
            }
            columnNum = dList.size();
            data=new Object[columnNum][6];
            int index=0;
            for(Room room:dList){
                data[index][0]=room.getRNum();
                data[index][1]=room.getRName();
                data[index][2]=room.getRMemberCount();
                data[index][3]=room.getRArea();
                data[index][4]=room.getRUsage();
                index++;
            }
        } catch (Exception ec){
            ec.printStackTrace();
        }
        table = new JTable(data,columnsName);
        table.setModel(new DefaultTableModel(data,columnsName){
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        });
        tableListener =new TableListener();
        table.addMouseListener(tableListener);

        jScrollPane2.setViewportView(table);
    }

    public static void main (String[] args) throws Exception {
        new UserUI("user");
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

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== reserveBtn){
           new ReservationFrame(tableListener.RNum, userData, this);
        } else if(e.getSource()==modifyUserItem){
            new ModifyUserInfoFrame(userData, this);
        } else if (e.getSource() == allResvItem){
            new AllResvFrame(userData, this);
        }  else if (e.getSource() == freshBtn) {
            freshTable();
        } else if(e.getSource() == queryAllItem){
            new QueryRoomFrame();
        } else if(e.getSource() == signOutMenuItem){
            new SignOutFrame(userData, this);
        } else if(e.getSource() == queryBtn){
            String value = queryField.getText().trim();
            if(value.trim().equals("")){
                freshTable();
                return ;
            }
            if(RNumRadio.isSelected()){
                freshTable("RNum", queryField.getText().trim());
            } else if(RNameRadio.isSelected()){
                freshTable("RName", queryField.getText().trim());
            } else if(RMemberCountRadio.isSelected()){
                freshTable("RMemberCount", queryField.getText().trim());
            } else if(RAreaRadio.isSelected()){
                freshTable("RArea", queryField.getText().trim());
            } else {
                JOptionPane.showMessageDialog(null, "请选择查询方式");
            }
        }
    }
}
