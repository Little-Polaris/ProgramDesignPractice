//Created by MrQi on 2023/12/10.

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class AddRoomFrame extends JDialog implements ActionListener {
    private JLabel RNumLbl, RNameLbl, RMemberCount, RArea, RPicDir, RUsageLbl;
    private JTextField RNumField, RNameField, RMemberCountField, RAreaField, RPicDirField, RUsageField;
    private JButton OkBtn, CancelBtn;
    private static final long serialVersionUID = 1L;
    public AddRoomFrame() {
        setTitle("添加会议室");
        setSize(300, 280);
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        int x = (int) (width - this.getWidth()) / 2;
        int y = (int) (height - this.getHeight()) / 2;
        setLocation(x, y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        RNumLbl = new JLabel("会议室编号:");
        RNameLbl = new JLabel("会议室名称:");
        RArea = new JLabel("会议室面积:");
        RMemberCount = new JLabel("可容纳人数:");
        RPicDir = new JLabel("图片路径:");
        RUsageLbl = new JLabel("会议室类型:");

        RNumField = new JTextField(20);
        RNameField = new JTextField(20);
        RMemberCountField = new JTextField(20);
        RAreaField = new JTextField(20);
        RPicDirField = new JTextField(20);
        RUsageField = new JTextField(20);

        OkBtn = new JButton("添加");
        CancelBtn = new JButton("取消");

        RNumLbl.setBounds(60, 10, 90, 20);
        RNumField.setBounds(140, 10, 120, 20);

        RNameLbl.setBounds(60, 40, 90, 20);
        RNameField.setBounds(140, 40, 120, 20);

        RMemberCount.setBounds(60, 100, 90, 20);
        RMemberCountField.setBounds(140, 100, 120, 20);

        RArea.setBounds(60, 70, 90, 20);
        RAreaField.setBounds(140, 70, 120, 20);

        RPicDir.setBounds(60, 130, 90, 20);
        RPicDirField.setBounds(140, 130, 120, 20);

        RUsageLbl.setBounds(60, 160, 90, 20);
        RUsageField.setBounds(140, 160, 120, 20);

        OkBtn.setBounds(60, 200, 80, 20);
        CancelBtn.setBounds(180, 200, 80, 20);

        add(RNumLbl);
        add(RNumField);
        add(RNameLbl);
        add(RNameField);
        add(RMemberCount);
        add(RMemberCountField);
        add(RArea);
        add(RAreaField);
        add(RPicDir);
        add(RPicDirField);
        add(RUsageLbl);
        add(RUsageField);
        add(OkBtn);
        add(CancelBtn);

        OkBtn.addActionListener(this);
        CancelBtn.addActionListener(this);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AddRoomFrame();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == OkBtn) {
            String RName = RNameField.getText();
            String RNum = RNumField.getText();
            String RArea = RAreaField.getText();
            String RMemberCount = RMemberCountField.getText();
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
            Dao dao = new Dao();
            Room room = new Room();
            room.setRNum(RNum);
            room.setRName(RName);
            room.setRArea(RArea);
            room.setRMemberCount(RMemberCount);
            room.setRPicDir(RPicDir);
            room.setRUsage(RUsage);
            room.setStatus("0");
            try {
                List<Room> roomList = dao.query(new Room());
                for(Room r : roomList){
                    if(r.getRNum().equals(RNum)){
                        JOptionPane.showMessageDialog(null, "会议室编号已存在，请重新输入！");
                        return;
                    }else if(r.getRName().equals(RName)){
                        JOptionPane.showMessageDialog(null, "会议室名称已存在，请重新输入！");
                        return;
                    }
                }
                dao.insert(room);
                JOptionPane.showMessageDialog(null, "添加成功");
                this.dispose();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
        else {
            this.dispose();
        }
    }
}
