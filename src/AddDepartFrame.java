/**
 * Created by xsw on 2017/6/2.
 */
//面向管理员添加学院信息的窗体类

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class AddDepartFrame extends JFrame implements ActionListener {
    //该窗体具备五个标签，五个文本框，两个按钮
    private JLabel RNameLbl, RNumLbl, RMember_countLbl, RAreaLbl, RUsageLbl, RPic_dirLbl;
    private JTextField RNameField, RNumField, RMember_countField, RAreaField, RUsageField, RPic_dirField;
    private JButton okBtn, cancelBtn;

    public AddDepartFrame() {

        // 创建窗体
        setTitle("添加教室信息");
        setSize(280, 260);
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

        RNameLbl = new JLabel("会议室名称:");
        RNumLbl = new JLabel("会议室编号:");
        RMember_countLbl = new JLabel("容量:");
        RAreaLbl = new JLabel("面积:");
        RUsageLbl = new JLabel("用途:");
        RPic_dirLbl = new JLabel("图片路径");


        RNameField = new JTextField(20);
        RNumField = new JTextField(20);
        RMember_countField = new JTextField(20);
        RAreaField = new JTextField(20);
        RUsageField = new JTextField(20);
        RPic_dirField = new JTextField(20);
        okBtn = new JButton("确定");
        cancelBtn = new JButton("取消");

        // 窗体位置设置
        setLayout(null);
        RNameLbl.setBounds(30, 10, 90, 20);
        RNameField.setBounds(100, 10, 140, 20);// 90=30+60

        RNumLbl.setBounds(30, 40, 90, 20); // 40=10+20+10
        RNumField.setBounds(100, 40, 140, 20);

        RMember_countLbl.setBounds(30, 100, 90, 20);
        RMember_countField.setBounds(100, 100, 140, 20);// 90=30+60

        RAreaLbl.setBounds(30, 70, 90, 20); // 40=10+20+10
        RAreaField.setBounds(100, 70, 140, 20);

        RUsageLbl.setBounds(30, 130, 90, 20);
        RUsageField.setBounds(100, 130, 140, 20);// 90=30+60

        RPic_dirLbl.setBounds(30, 70, 90, 20);
        RPic_dirField.setBounds(100, 280, 140, 20);

        okBtn.setBounds(30, 180, 100, 20);
        cancelBtn.setBounds(140, 180, 100, 20);
        //将上述桌面添加进来
        add(RNameLbl);
        add(RNameField);
        add(RNumLbl);
        add(RNumField);
        add(RAreaLbl);
        add(RAreaField);
        add(RMember_countLbl);
        add(RMember_countField);
        add(RUsageLbl);
        add(RUsageField);
        add(RPic_dirLbl);
        add(RPic_dirField);
        add(okBtn);
        add(cancelBtn);
        //注册事件监听
        okBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AddDepartFrame();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okBtn) {
            String RName = RNameField.getText();
            String RNum = RNumField.getText();
            String RMember_count = RMember_countField.getText();
            String RArea = RAreaField.getText();
            String RUsage = RUsageField.getText();
            String RPic_dir = RPic_dirField.getText();
            if (RName == null || "".equals(RName.trim()) || RNum == null || "".equals(RNum)) {
                JOptionPane.showMessageDialog(null, "教室编号和所属学院不能为空");
                return;
            }
            //调用DepartDao业务逻辑处理类来完成增加的操作
            DepartDao departDao = new DepartDao();
            //将用户输入的数据封装成一个Depart对象
            Depart d = new Depart("room", RName, RNum, RMember_count, RArea, RUsage, RPic_dir);
            try {
                departDao.save("room", d);//保存数据
                JOptionPane.showMessageDialog(null, "教室信息添加成功");
                log.logger.debug("管理员添加了教室"+RName+"");
            } catch (Exception ec) {
                JOptionPane.showMessageDialog(null, "保存时出现异常，异常原因为:" + ec.getMessage());
                ec.printStackTrace();
            }
        } else {
            this.dispose();//关闭当前窗口
        }
    }
}
