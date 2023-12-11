//Created by MrQi on 2023/12/9.

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAdminFrame extends JDialog implements ActionListener {
    private JLabel NameLbl, IdLbl, PwdLbl, GenderLbl, TelLbl, EmailLbl;
    private JTextField NameField, IdField, PwdField, GenderField, TelField, EmailField;
    private JButton OkBtn, CancelBtn;
    private static final long serialVersionUID = 1L;
    public AddAdminFrame() {
        setTitle("添加新管理员");
        setSize(300, 280);
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        int x = (int) (width - this.getWidth()) / 2;
        int y = (int) (height - this.getHeight()) / 2;
        setLocation(x, y);
        setResizable(false);
        setLayout(null);

        NameLbl = new JLabel("姓名:");
        IdLbl = new JLabel("账号:");
        GenderLbl = new JLabel("性别:");
        PwdLbl = new JLabel("密码:");
        TelLbl = new JLabel("联系方式:");
        EmailLbl = new JLabel("邮箱:");

        NameField = new JTextField(20);
        IdField = new JTextField(20);
        PwdField = new JTextField(20);
        GenderField = new JTextField(20);
        TelField = new JTextField(20);
        EmailField = new JTextField(20);

        OkBtn = new JButton("添加");
        CancelBtn = new JButton("取消");

        NameLbl.setBounds(60, 10, 90, 20);
        NameField.setBounds(140, 10, 120, 20);

        IdLbl.setBounds(60, 40, 90, 20);
        IdField.setBounds(140, 40, 120, 20);

        PwdLbl.setBounds(60, 100, 90, 20);
        PwdField.setBounds(140, 100, 120, 20);

        GenderLbl.setBounds(60, 70, 90, 20);
        GenderField.setBounds(140, 70, 120, 20);

        TelLbl.setBounds(60, 130, 90, 20);
        TelField.setBounds(140, 130, 120, 20);

        EmailLbl.setBounds(60, 160, 90, 20);
        EmailField.setBounds(140, 160, 120, 20);

        OkBtn.setBounds(60, 200, 80, 20);
        CancelBtn.setBounds(180, 200, 80, 20);

        add(NameLbl);
        add(NameField);
        add(IdLbl);
        add(IdField);
        add(PwdLbl);
        add(PwdField);
        add(GenderLbl);
        add(GenderField);
        add(TelLbl);
        add(TelField);
        add(EmailLbl);
        add(EmailField);
        add(OkBtn);
        add(CancelBtn);

        OkBtn.addActionListener(this);
        CancelBtn.addActionListener(this);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AddAdminFrame();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == OkBtn) {
            String Id = IdField.getText();
            String Name = NameField.getText();
            String Gender = GenderField.getText();
            String Pwd = PwdField.getText();
            String Tel = TelField.getText();
            String Email = EmailField.getText();
            if (Name == null || "".equals(Name.trim())
                    || Id == null || "".equals(Id.trim())
                    || Pwd == null || "".equals(Pwd.trim())
                    || Gender == null || "".equals(Gender.trim())
                    || Tel == null || "".equals(Tel.trim())
                    || Email == null || "".equals(Email))
            {
                JOptionPane.showMessageDialog(null, "信息填写不完整，请重新检查！");
                return;
            }
            Dao dao = new Dao();
            Admin admin = new Admin(Name, Id, Gender, Pwd, Tel, Email);
            try {
                dao.insert(admin);//保存数据
                JOptionPane.showMessageDialog(null, "添加成功");
                this.dispose();
            } catch (java.sql.SQLException ec) {
                int errorCode = ec.getErrorCode();
                if(errorCode == 1062){
                    JOptionPane.showMessageDialog(null, "账号重复，请修改后重试");
                } else if (errorCode == 1406) {
                    String message = ec.getMessage();
                    if(message.contains("gender")){
                        JOptionPane.showMessageDialog(null, "输入的性别有误，请修改后重试");
                    } else if (message.contains("name")) {
                        JOptionPane.showMessageDialog(null, "输入的姓名过长，请修改后重试");
                    } else if (message.contains("password")){
                        JOptionPane.showMessageDialog(null, "输入的密码过长，请修改后重试");
                    } else if (message.contains("phone")) {
                        JOptionPane.showMessageDialog(null, "输入的手机号码，请修改后重试");
                    } else if (message.contains("email")) {
                        JOptionPane.showMessageDialog(null, "输入的邮箱过长，请修改后重试");
                    }
                }
                JOptionPane.showMessageDialog(null, "保存时出现异常，异常原因为:" + ec.getMessage());
            }catch (Exception ec1){
                ec1.printStackTrace();
            }
        }
        else {
            this.dispose();
        }
    }
}
