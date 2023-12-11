//Created by MrQi on 2023/12/10.

import javax.management.monitor.StringMonitor;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;
import java.time.LocalDateTime;

public class ModifyRoomFrame extends JDialog implements ActionListener {
    private JTable table;
    private JButton modifyBtn, cancelBtn;
    private JLabel RNumLbl, RNameLbl, RMemberCountLbl, RAreaLbl, RPicDirLbl, RUsageLbl;
    private JTextField RNumField, RNameField, RMemberCountField, RAreaField, RPicDirField, RUsageField;
    private JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
    private JLabel queryLbl;
    private JTextField queryField;
    private JButton queryBtn;
    private JRadioButton RNumRadio, RNameRadio, RUserIDRadio, RUserNameRadio;
    ButtonGroup group;
    private Room oldRoom;
    private AdminUI adminUI;
    Object[][]data;
    TableListener tableListener;

    public ModifyRoomFrame(){
        this.adminUI = adminUI;
        setTitle("修改会议室信息");
        setSize(900,450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        RNumLbl = new JLabel("会议室编号:");
        RNameLbl = new JLabel("会议室名称:");
        RMemberCountLbl = new JLabel("可容纳人数:");
        RAreaLbl = new JLabel("会议室面积:");
        RPicDirLbl = new JLabel("图片路径:");
        RUsageLbl = new JLabel("会议室类型:");
        RNumField = new JTextField(20);
        RNameField = new JTextField(20);
        RMemberCountField = new JTextField(20);
        RAreaField = new JTextField(20);
        RPicDirField = new JTextField(20);
        RUsageField = new JTextField(20);

        RNumLbl.setBounds(630, 50, 90, 20);
        RNumField.setBounds(700, 50, 140, 20);

        RNameLbl.setBounds(630, 80, 90, 20);
        RNameField.setBounds(700, 80, 140, 20);

        RMemberCountLbl.setBounds(630, 110, 90, 20);
        RMemberCountField.setBounds(700, 110, 140, 20);

        RAreaLbl.setBounds(630, 140, 90, 20);
        RAreaField.setBounds(700, 140, 140, 20);


        RPicDirLbl.setBounds(630, 170, 90, 20);
        RPicDirField.setBounds(700, 170, 140, 20);

        RUsageLbl.setBounds(630, 200, 90, 20);
        RUsageField.setBounds(700, 200, 140, 20);

        add(RNumLbl);
        add(RNumField);
        add(RNameLbl);
        add(RNameField);
        add(RMemberCountLbl);
        add(RMemberCountField);
        add(RAreaLbl);
        add(RAreaField);
        add(RPicDirLbl);
        add(RPicDirField);
        add(RUsageLbl);
        add(RUsageField);

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

        modifyBtn = new JButton("修改");
        cancelBtn = new JButton("取消");
        modifyBtn.setBounds(160, 350, 100, 20);
        cancelBtn.setBounds(360, 350, 100, 20);
        modifyBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
        add(modifyBtn);
        add(cancelBtn);

        freshTable();

        setVisible(true);
    }
    public static void main (String[] args){
        new ModifyRoomFrame();
    }

    public void freshTable(){
        String[] columnsName={"会议室编号","会议室名称","可容纳人数","会议室面积", "图片路径", "会议室类型"};
        try{
            Dao dao =new Dao();
            List<Room> roomList = dao.query(new Room());
            for(int i = 0; i < roomList.size(); i++){
                if(roomList.get(i).getStatus().equals("1")){
                    roomList.remove(i);
                    i--;
                }
            }
            data=new Object[roomList.size()][6];
            int index=0;
            for(Room room: roomList){
                data[index][0]=room.getRNum();
                data[index][1]=room.getRName();
                data[index][2]=room.getRMemberCount();
                data[index][3]=room.getRArea();
                data[index][4]=room.getRPicDir();
                data[index][5]=room.getRUsage();
                index++;
            }
        } catch (Exception ec){
            ec.printStackTrace();
        }
        table = new JTable(data,columnsName);
        tableListener = new TableListener();
        table.addMouseListener(tableListener);
        jScrollPane2.setBounds(20, 110, 600, 200);
        jScrollPane2.setViewportView(table);
        this.add(jScrollPane2);
    }

    public void freshTable(String key, String value){
        String[] columnsName={"会议室编号","会议室名称","可容纳人数","会议室面积", "图片路径", "会议室类型"};
        try{
            Dao dao =new Dao();
            List<Room> roomList = dao.query(new Room(), key, value);
            for(int i = 0; i < roomList.size(); i++){
                if(roomList.get(i).getStatus().equals("1")){
                    roomList.remove(i);
                    i--;
                }
            }
            data=new Object[roomList.size()][6];
            int index=0;
            for(Room room: roomList){
                data[index][0]=room.getRNum();
                data[index][1]=room.getRName();
                data[index][2]=room.getRMemberCount();
                data[index][3]=room.getRArea();
                data[index][4]=room.getRPicDir();
                data[index][5]=room.getRUsage();
                index++;
            }
        } catch (Exception ec){
            ec.printStackTrace();
        }
        table = new JTable(data,columnsName);
        tableListener = new TableListener();
        table.addMouseListener(tableListener);
        jScrollPane2.setBounds(20, 110, 600, 200);
        jScrollPane2.setViewportView(table);
        this.add(jScrollPane2);
    }


    class TableListener extends MouseAdapter {
        String RNum, RName, RMemberCount, RArea, RPicDir, RUsage;
        public void mouseClicked(final MouseEvent e) {
            int selRow = table.getSelectedRow();
            if(selRow == -1){
                return ;
            }
            RNum = table.getValueAt(selRow, 0).toString().trim();
            RName = table.getValueAt(selRow, 1).toString().trim();
            RMemberCount = table.getValueAt(selRow, 2).toString().trim();
            RArea = table.getValueAt(selRow, 3).toString().trim();
            RPicDir = table.getValueAt(selRow, 4).toString().trim();
            RUsage = table.getValueAt(selRow, 5).toString().trim();
            oldRoom = new Room();
            oldRoom.setRNum(RNum);
            oldRoom.setRName(RName);
            oldRoom.setRMemberCount(RMemberCount);
            oldRoom.setRArea(RArea);
            oldRoom.setRPicDir(RPicDir);
            oldRoom.setRUsage(RUsage);
            RNumField.setText(RNum);
            RNameField.setText(RName);
            RMemberCountField.setText(RMemberCount);
            RAreaField.setText(RArea);
            RPicDirField.setText(RPicDir);
            RUsageField.setText(RUsage);
        }
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == modifyBtn){
            String RNum = RNumField.getText();
            String RName = RNameField.getText();
            String RMemberCount = RMemberCountField.getText();
            String RArea = RAreaField.getText();
            String RPicDir = RPicDirField.getText();
            String RUsage = RUsageField.getText();
            if (RNum == null || "".equals(RNum.trim())
                    || RName == null || "".equals(RName.trim())
                    || RMemberCount == null || "".equals(RMemberCount.trim())
                    || RArea == null || "".equals(RArea.trim())
                    || RPicDir == null || "".equals(RPicDir.trim())
                    || RUsage == null || "".equals(RUsage))
            {
                JOptionPane.showMessageDialog(null, "信息填写不完整，请重新检查！");
                return;
            }
            Room newRoom = new Room();
            newRoom.setRNum(RNum);
            newRoom.setRName(RName);
            newRoom.setRMemberCount(RMemberCount);
            newRoom.setRArea(RArea);
            newRoom.setRPicDir(RPicDir);
            newRoom.setRUsage(RUsage);
            newRoom.setStatus("0");
            Dao dao = new Dao();
            List<Room> roomList = null;
            try {
                roomList = dao.query(new Room());
            } catch(Exception ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "修改时出现异常， 错误原因:" + ex.getMessage());

            }
            for(Room r : roomList){
                if(r.getRNum().equals(RNum) && !RNum.equals(oldRoom.getRNum())){
                    JOptionPane.showMessageDialog(null, "会议室编号已存在，请重新输入！");
                    return;
                }else if(r.getRName().equals(RName) && !RName.equals(oldRoom.getRName())){
                    JOptionPane.showMessageDialog(null, "会议室名称已存在，请重新输入！");
                    return;
                }
            }
            try {
                dao.update(oldRoom, newRoom);
                List<Reservation> reservationList = dao.query(new Reservation(), "RNum", oldRoom.getRNum());
                for(Reservation d : reservationList){
                    d.setRNum(newRoom.getRNum());
                    dao.update(d, d);
                }
                JOptionPane.showMessageDialog(null, "修改成功");
                freshTable();
            } catch (Exception ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "修改时出现异常， 错误原因:" + ex.getMessage());
            }
        } else if(e.getSource() == cancelBtn) {
            this.dispose();
        }
    }
}

