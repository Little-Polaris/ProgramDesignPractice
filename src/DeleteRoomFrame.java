//Created by MrQi on 2023/12/10.

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class DeleteRoomFrame extends JDialog implements ActionListener {
    private JTable table;
    private JLabel titleLbl, queryLbl;
    private JTextField queryField;
    private JScrollPane jScrollPane = new javax.swing.JScrollPane();
    private JButton queryBtn, deleteBtn;
    private JRadioButton RNumRadio, RNameRadio, RMemberCountRadio, RAreaRadio;
    private Room deleteRoom;
    ButtonGroup group;
    private Object[][]data;
    private static final long serialVersionUID = 1L;
    public DeleteRoomFrame() {
        setTitle("查询所有会议室信息");
        setSize(800, 600);
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        int x = (int) (width - this.getWidth()) / 2;
        int y = (int) (height - this.getHeight()) / 2;
        setLocation(x, y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        titleLbl = new JLabel("会议室信息");
        titleLbl.setBounds(373, 40, 100, 20);
        this.add(titleLbl);

        queryLbl = new JLabel("查询:");
        queryField = new JTextField(100);
        queryBtn = new JButton("查询");
        RNumRadio = new JRadioButton("按会议室编号查询");
        RNameRadio = new JRadioButton("按会议室名称查询");
        RMemberCountRadio = new JRadioButton("按可容纳人数查询");
        RAreaRadio = new JRadioButton("按会议室面积查询");
        group = new ButtonGroup();
        group.add(RNumRadio);
        group.add(RNameRadio);
        group.add(RMemberCountRadio);
        group.add(RAreaRadio);
        queryBtn.setBounds(320, 80, 80, 20);
        queryLbl.setBounds(100, 80, 110, 20);
        queryField.setBounds(210, 80, 100, 20);
        RNumRadio.setBounds(100, 110, 150, 20);
        RNameRadio.setBounds(250, 110, 150, 20);
        RMemberCountRadio.setBounds(400, 110, 150, 20);
        RAreaRadio.setBounds(550, 110, 150, 20);
        queryBtn.addActionListener(this);
        this.add(queryBtn);
        this.add(queryField);
        this.add(queryLbl);
        this.add(RNumRadio);
        this.add(RNameRadio);
        this.add(RMemberCountRadio);
        this.add(RAreaRadio);

        deleteBtn = new JButton("删除");
        deleteBtn.setBounds(360, 500, 100, 20);
        deleteBtn.addActionListener(this);
        this.add(deleteBtn);

        freshTable();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new DeleteRoomFrame();
    }
    private void freshTable(){
        String[] columnsName={"会议室编号","会议室名称","可容纳人数","会议室面积", "会议室类型","占用情况", "会议室图片"};
        try{
            Dao dao = new Dao();
            List<Room> roomList = dao.query(new Room());
            for(int i = 0; i < roomList.size(); i++){
                if(!roomList.get(i).getStatus().equals("0")){
                    roomList.remove(i);
                    i--;
                }
            }
            int columnsNum = roomList.size();
            data = new Object[columnsNum][7];
            int index = 0;
            for (Room room : roomList) {
                data[index][0] = room.getRNum();
                data[index][1] = room.getRName();
                data[index][2] = room.getRMemberCount();
                data[index][3] = room.getRArea();
                data[index][4] = room.getRUsage();
                data[index][5] = room.getStatus().equals("0") ? "空闲" : "占用";
                data[index][6] = "";
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
        String[] columnsName={"会议室编号","会议室名称","可容纳人数","会议室面积", "会议室类型", "占用情况", "会议室图片"};

        try{
            Dao dao = new Dao();
            List<Room> roomList = dao.query(new Room(), key, value);
            for(int i = 0; i < roomList.size(); i++){
                if(!roomList.get(i).getStatus().equals("0")){
                    roomList.remove(i);
                    i--;
                }
            }
            int columnsNum = roomList.size();
            data = new Object[columnsNum][7];
            int index = 0;
            for (Room room : roomList) {
                data[index][0] = room.getRNum();
                data[index][1] = room.getRName();
                data[index][2] = room.getRMemberCount();
                data[index][3] = room.getRArea();
                data[index][4] = room.getRUsage();
                data[index][5] = room.getStatus().equals("0") ? "空闲" : "占用";
                data[index][6] = "";
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
        String RNum;
        public void mouseClicked(final MouseEvent e) {
            int selRow = table.getSelectedRow();
            if(selRow == -1){
                return ;
            }
            RNum = table.getValueAt(selRow, 0).toString().trim();
            deleteRoom = new Room();
            deleteRoom.setRNum(RNum);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == queryBtn) {
            if(queryField.getText().trim().equals("")){
                freshTable();
                return;
            }
            if(RNumRadio.isSelected()){
                String RNum = queryField.getText().trim();
                freshTable("RNum", RNum);
            }else if(RNameRadio.isSelected()){
                String RName = queryField.getText().trim();
                freshTable("RName", RName);
            }else if(RMemberCountRadio.isSelected()){
                String RMemberCount = queryField.getText().trim();
                freshTable("RMemberCount", RMemberCount);
            }else if(RAreaRadio.isSelected()){
                String RArea = queryField.getText().trim();
                freshTable("RArea", RArea);
            }else{
                JOptionPane.showMessageDialog(null, "请选择查询方式");
            }
        }else if(e.getSource() == deleteBtn){
            if(deleteRoom == null){
                JOptionPane.showMessageDialog(null, "请选择要删除的会议室");
                return;
            }
            Dao dao = new Dao();
            try {
                dao.delete(deleteRoom);
                JOptionPane.showMessageDialog(null, "删除成功");
                freshTable();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else {
            this.dispose();//关闭当前窗口
        }
    }
}
