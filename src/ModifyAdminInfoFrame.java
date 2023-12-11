//Created by MrQi on 2023/12/10.

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class ModifyAdminInfoFrame extends JDialog implements ActionListener {
    private JLabel nameLbl, IDLbl, genderLbl, pwdLbl, TelLbl, emailLbl;
    private JTextField nameField, IDField, genderField, pwdField, TelField, emailField;
    private JButton modifyBtn, cancelBtn;
    Admin adminData;
    AdminUI adminUI;
    private static final long serialVersionUID = 1L;
    public void main() throws Exception {
        new ModifyAdminInfoFrame(null, null);
    }
    public ModifyAdminInfoFrame(Admin adminData, AdminUI AdminUI) {
        this.adminData = adminData;
        this.adminUI = AdminUI;
        setTitle("修改个人信息");
        setSize(300, 280);
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        int x = (int) (width - this.getWidth()) / 2;
        int y = (int) (height - this.getHeight()) / 2;
        setLocation(x, y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        nameLbl = new JLabel("姓名:");
        IDLbl = new JLabel("账号:");
        genderLbl = new JLabel("性别:");
        pwdLbl = new JLabel("密码:");
        TelLbl = new JLabel("电话:");
        emailLbl = new JLabel("邮箱:");
        nameField = new JTextField(100);
        IDField = new JTextField(100);
        genderField = new JTextField(100);
        pwdField = new JTextField(100);
        TelField = new JTextField(100);
        emailField = new JTextField(100);
        modifyBtn = new JButton("修改");
        cancelBtn = new JButton("取消");

        nameLbl.setBounds(60, 10, 90, 20);
        nameField.setBounds(140, 10, 120, 20);
        IDLbl.setBounds(60, 40, 90, 20);
        IDField.setBounds(140, 40, 120, 20);
        pwdLbl.setBounds(60, 100, 90, 20);
        pwdField.setBounds(140, 100, 120, 20);
        genderLbl.setBounds(60, 70, 90, 20);
        genderField.setBounds(140, 70, 120, 20);
        TelLbl.setBounds(60, 130, 90, 20);
        TelField.setBounds(140, 130, 120, 20);
        emailLbl.setBounds(60, 160, 90, 20);
        emailField.setBounds(140, 160, 120, 20);

        modifyBtn.setBounds(60, 200, 80, 20);
        cancelBtn.setBounds(180, 200, 80, 20);
        Dao dao = new Dao();
        List<User> userList;
        User user = null;
        try {
            userList = dao.query(new User(), "ID", adminData.getId());
            user = userList.getFirst();
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "查询时出现异常， 错误原因:" + e.getMessage());
        }
        nameField.setText(user.getName());
        IDField.setText(user.getId());
        genderField.setText(user.getGender());
        pwdField.setText(user.getPwd());
        TelField.setText(user.getTel());
        emailField.setText(user.getEmail());

        this.add(nameLbl);
        this.add(nameField);
        this.add(IDLbl);
        this.add(IDField);
        this.add(genderLbl);
        this.add(genderField);
        this.add(pwdLbl);
        this.add(pwdField);
        this.add(TelLbl);
        this.add(TelField);
        this.add(emailLbl);
        this.add(emailField);
        this.add(modifyBtn);
        this.add(cancelBtn);

        modifyBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == modifyBtn) {
            Dao dao = new Dao();
            Admin admin = new Admin();
            admin.setName(nameField.getText());
            admin.setId(IDField.getText());
            admin.setGender(genderField.getText());
            admin.setPwd(pwdField.getText());
            admin.setTel(TelField.getText());
            admin.setEmail(emailField.getText());
            try {
                dao.update(adminData, admin);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "修改时出现异常， 错误原因:" + ex.getMessage());
            }
            JOptionPane.showMessageDialog(null, "修改成功");
            if(adminData.getId().equals(admin.getId()) && adminData.getPwd().equals(admin.getPwd())){
                adminData = admin;
            } else {
                JOptionPane.showMessageDialog(null, "账号或密码已修改，请重新登陆");
                this.dispose();
                adminUI.dispose();
                new LoginUI_Main("会议室预约系统—登录");
            }
            this.dispose();
        } else if (e.getSource() == cancelBtn) {
            this.dispose();
        }
    }
}
