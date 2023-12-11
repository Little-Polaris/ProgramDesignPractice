//Created by MrQi on 2023/12/9.
//Modified by MrQi on 2023/12/10.

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReservationFrame extends JDialog implements ActionListener {
    private String RNum;
    private User userData;
    private JLabel UsingDate, StartingTimeLbl, EndingTimeLbl, UserUsageLbl;
    private JTextField UseDateField;
    private JButton reserveBtn, cancelBtn;
    private JTextArea UserUsageField;
    private JComboBox StartCombox, EndCombox;
    private UserUI userUI;
    private static final long serialVersionUID = 1L;
    public ReservationFrame(String RNum, User userData, UserUI userUI) {
        if(RNum == null || userData == null || userUI == null){
            return;
        }
        this.RNum = RNum;
        this.userData = userData;
        this.userUI = userUI;

        setTitle("预定会议室");
        setSize(370, 450);
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        int x = (int) (width - this.getWidth()) / 2;
        int y = (int) (height - this.getHeight()) / 2;
        setLocation(x, y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        UsingDate = new JLabel("使用日期:");
        StartingTimeLbl = new JLabel("开始时间:");
        EndingTimeLbl = new JLabel("结束时间:");
        UserUsageLbl = new JLabel("用途:");
        StartCombox =new JComboBox();
        StartCombox.addItem("8:00");
        StartCombox.addItem("9:00");
        StartCombox.addItem("10:00");
        StartCombox.addItem("11:00");
        StartCombox.addItem("12:00");
        StartCombox.addItem("13:00");
        StartCombox.addItem("14:00");
        StartCombox.addItem("15:00");
        StartCombox.addItem("16:00");
        StartCombox.addItem("17:00");
        StartCombox.addItem("18:00");
        StartCombox.addItem("19:00");
        EndCombox =new JComboBox();
        EndCombox.addItem("9:00");
        EndCombox.addItem("10:00");
        EndCombox.addItem("11:00");
        EndCombox.addItem("12:00");
        EndCombox.addItem("13:00");
        EndCombox.addItem("14:00");
        EndCombox.addItem("15:00");
        EndCombox.addItem("16:00");
        EndCombox.addItem("17:00");
        EndCombox.addItem("18:00");
        EndCombox.addItem("19:00");
        EndCombox.addItem("20:00");

        UseDateField = new JTextField(20);

        UserUsageField = new JTextArea(100,50);
        UserUsageField.setBorder(BorderFactory.createLineBorder(Color.black));

        reserveBtn = new JButton("提交");
        cancelBtn = new JButton("取消");

        UsingDate.setBounds(60, 40, 90, 20);
        UseDateField.setBounds(140, 40, 140, 20);
        UseDateField.setText(LocalDate.now().toString());
        StartingTimeLbl.setBounds(60, 80, 90, 20);
        StartCombox.setBounds(140, 80, 140, 20);
        EndingTimeLbl.setBounds(60, 120, 90, 20);
        EndCombox.setBounds(140, 120, 140, 20);
        UserUsageLbl.setBounds(60, 160, 90, 20);
        UserUsageField.setBounds(140, 160, 140, 110);

        reserveBtn.setBounds(60, 300, 100, 20);
        cancelBtn.setBounds(180, 300, 100, 20);

        add(UsingDate);
        add(UseDateField);
        add(StartingTimeLbl);
        add(EndingTimeLbl);
        add(StartCombox);
        add(EndCombox);
        add(UserUsageLbl);
        add(UserUsageField);
        add(reserveBtn);
        add(cancelBtn);

        getContentPane().add(UseDateField);

        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        reserveBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
        addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                userUI.freshTable();
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        new ReservationFrame("1", null, null);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == reserveBtn) {
            String RNum = this.RNum;
            String UseDate = UseDateField.getText();
            String StartTime = (String) StartCombox.getSelectedItem();
            String EndTIme =(String) EndCombox.getSelectedItem();
            String RUserUsage = UserUsageField.getText();
            if (RNum == null || "".equals(RNum.trim())
                || UseDate == null || "".equals(UseDate.trim())
                || StartTime == null || "".equals(StartTime)
                || EndTIme == null || "".equals(EndTIme.trim())
                || RUserUsage == null || "".equals(RUserUsage))
            {
                JOptionPane.showMessageDialog(null, "信息填写不完整，请重新检查！");
                return;
            }
            Dao dao = new Dao();
            Room room = null;
            try {
                List<Room> roomList = dao.query(new Room(), "RNum", RNum);
                room = roomList.getFirst();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            room.setStatus("1");
            room.setRUserName(userData.getName());
            room.setRUserId(userData.getId());
            room.setRUserTel(userData.getTel());
            room.setRUserEmail(userData.getEmail());;
            room.setUseDate(UseDate);
            room.setStartTime(StartTime);
            room.setEndTime(EndTIme);
            room.setRUserUsage(RUserUsage);
            Reservation reservation = new Reservation();
            reservation.setUserID(userData.getId());
            reservation.setRNum(RNum);
            reservation.setSubTime(LocalDateTime.now().toString());
            reservation.setNote(RUserUsage);
            reservation.setCheckStatus("1");
            try {
                dao.update(room, room);
                dao.insert(reservation);
                JOptionPane.showMessageDialog(null, "提交成功，请等待审核");
                this.dispose();
            } catch (Exception ec) {
                JOptionPane.showMessageDialog(null, "保存时出现异常，异常原因为:" + ec.getMessage());
                ec.printStackTrace();
            }
        }else if(e.getSource() == cancelBtn){
            this.dispose();
        }
    }
}
