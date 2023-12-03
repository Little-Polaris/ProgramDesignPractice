/**
 * Created by xsw on 2017/6/2.
 */
//预定信息界面

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class subDepartFrame extends JFrame implements ActionListener {
    //该窗体具备五个标签，五个文本框，两个按钮
    private JLabel NameLbl, IdLbl, TelLbl, EmailLbl, UsingDate, StartingTimeLbl, EndingTimeLbl,durLbl, UserUsageLbl;
    private JTextField NameField, IdField, TelField, EmailField, UsingDateField;
    private JButton okBtn, cancelBtn;
    private JTextArea UserUsageField;
    private JComboBox StartingCombox, EndingCombox;
    private static final long serialVersionUID = 1L;
    public subDepartFrame() {

        // 创建窗体
        setTitle("预定教室");
        setSize(400, 450);
        WindowUtils.displayOnDesktopCenter(this);//窗体居中的方法
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        int x = (int) (width - this.getWidth()) / 2;
        int y = (int) (height - this.getHeight()) / 2;
        setLocation(x, y);
        // 正常关闭窗口
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);//设置窗体大小不可改变
        //setResizable(false);

        NameLbl = new JLabel("姓名:");
        IdLbl = new JLabel("账号:");
        TelLbl = new JLabel("电话:");
        EmailLbl = new JLabel("邮箱:");
        UsingDate = new JLabel("使用日期:");
        StartingTimeLbl = new JLabel("开始时间:");
        EndingTimeLbl = new JLabel("结束时间:");
        //durLbl = new JLabel("借用时长:");
        UserUsageLbl = new JLabel("用途:");
        StartingCombox =new JComboBox();
        StartingCombox.addItem("8:00");
        StartingCombox.addItem("9:00");
        StartingCombox.addItem("10:00");
        StartingCombox.addItem("11:00");
        StartingCombox.addItem("12:00");
        StartingCombox.addItem("13:00");
        StartingCombox.addItem("14:00");
        StartingCombox.addItem("15:00");
        StartingCombox.addItem("16:00");
        StartingCombox.addItem("17:00");
        StartingCombox.addItem("18:00");
        StartingCombox.addItem("19:00");
        EndingCombox =new JComboBox();
        EndingCombox.addItem("9:00");
        EndingCombox.addItem("10:00");
        EndingCombox.addItem("11:00");
        EndingCombox.addItem("12:00");
        EndingCombox.addItem("13:00");
        EndingCombox.addItem("14:00");
        EndingCombox.addItem("15:00");
        EndingCombox.addItem("16:00");
        EndingCombox.addItem("17:00");
        EndingCombox.addItem("18:00");
        EndingCombox.addItem("19:00");
        EndingCombox.addItem("20:00");

        NameField = new JTextField(20);
        IdField = new JTextField(20);
        TelField = new JTextField(20);
        EmailField = new JTextField(20);
        UsingDateField = new JTextField(20);

        UserUsageField = new JTextArea(100,50);
        UserUsageField.setBorder(BorderFactory.createLineBorder(Color.black));

        okBtn = new JButton("提交");
        cancelBtn = new JButton("取消");

        // 窗体位置设置
        setLayout(null);
        NameLbl.setBounds(60, 10, 90, 20);
        NameField.setBounds(140, 10, 140, 20);// 90=30+60

        IdLbl.setBounds(60, 40, 90, 20); // 40=10+20+10
        IdField.setBounds(140, 40, 140, 20);

        TelLbl.setBounds(60, 70, 90, 20);
        TelField.setBounds(140, 70, 140, 20);// 90=30+60

        EmailLbl.setBounds(60, 100, 90, 20); // 40=10+20+10
        EmailField.setBounds(140, 100, 140, 20);

        UsingDate.setBounds(60, 130, 90, 20);
        UsingDateField.setBounds(140, 130, 140, 20);// 90=30+60

        StartingTimeLbl.setBounds(60, 160, 90, 20);
        StartingCombox.setBounds(140, 160, 140, 20);// 90=30+60

        EndingTimeLbl.setBounds(60, 190, 90, 20);
        EndingCombox.setBounds(140, 190, 140, 20);// 90=30+60

        UserUsageLbl.setBounds(60, 220, 90, 20);
        UserUsageField.setBounds(140, 220, 140, 110);// 90=30+60

        okBtn.setBounds(60, 350, 100, 20);
        cancelBtn.setBounds(180, 350, 100, 20);
        //将上述桌面添加进来
        add(NameLbl);
        add(NameField);
        add(IdLbl);
        add(IdField);
        add(TelLbl);
        add(TelField);
        add(EmailLbl);
        add(EmailField);
        add(UsingDate);
        add(UsingDateField);
        add(StartingTimeLbl);
        add(EndingTimeLbl);
        add(StartingCombox);
        add(EndingCombox);
        add(UserUsageLbl);
        add(UserUsageField);
        add(okBtn);
        add(cancelBtn);

        CalendarPanel p = new CalendarPanel(UsingDateField, "yyyy/MM/dd");
        p.initCalendarPanel();

        JLabel l = new JLabel("日历面板");
        p.add(l);
        getContentPane().add(p);
        getContentPane().add(UsingDateField);
        //setSize(500, 400);
        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);




        //注册事件监听
        okBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
        setVisible(true);
    }

    public static void main(String[] args) {
        new subDepartFrame();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okBtn) {
            String user_name = NameField.getText();
            String user_id = IdField.getText();
            String telNum = TelField.getText();
            String email = EmailField.getText();
            String clr_num = UsingDateField.getText();
            String date_begin = UsingDateField.getText();
            String date_end = (String) StartingCombox.getSelectedItem();
            String duration =(String) EndingCombox.getSelectedItem();
            String purpose = UserUsageField.getText();
            if (user_id == null || "".equals(user_id.trim())
                    || user_id == null || "".equals(user_id.trim())
                    || user_name == null || "".equals(user_name.trim())
                    || telNum == null || "".equals(telNum.trim())
                    || email == null || "".equals(email.trim())
                    || clr_num == null || "".equals(clr_num.trim())
                    || date_begin == null || "".equals(date_begin.trim())
                    || date_end == null || "".equals(date_end)
                    || duration == null || "".equals(duration.trim())
                    || purpose == null || "".equals(purpose))
            {
                JOptionPane.showMessageDialog(null, "信息填写不完整，请重新检查！");
                return;
            }
            //调用DepartDao业务逻辑处理类来完成增加的操作
            subDepartDao subdepartDao = new subDepartDao();
            //将用户输入的数据封装成一个Depart对象
            subDepart d = new subDepart(user_name, user_id, telNum, email, clr_num,date_begin,date_end,duration,purpose);
            try {
                subdepartDao.save(d);//保存数据
                JOptionPane.showMessageDialog(null, "预定提交成功，等待审核");
                log.logger.debug(""+user_id+"预定了教室"+clr_num+"并等待审核");
            } catch (Exception ec) {
                JOptionPane.showMessageDialog(null, "保存时出现异常，异常原因为:" + ec.getMessage());
                ec.printStackTrace();
            }
        }
        else {
            this.dispose();//关闭当前窗口
        }
    }
}
