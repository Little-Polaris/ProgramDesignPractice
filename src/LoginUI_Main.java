//Modified by MrQi on 2023/12/9.

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class LoginUI_Main extends JFrame implements ActionListener{
    //该窗体上有三个标签、一个文本框、一个密码框、一个下拉列表框、两个按钮
    private JLabel accountLbl,paswordLbl,roleLbl;
    private JTextField accountField;
    private JPasswordField passwordField;
    private JButton enterBtn,regBtn;
    private JComboBox roleCombox;
    public LoginUI_Main(String title){
        setTitle (title);
        setSize(280,160);
        WindowUtils.displayOnDesktopCenter(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        accountLbl=new JLabel("用户账号：");
        paswordLbl=new JLabel("用户密码：");
        roleLbl=new JLabel("用户身份：");
        accountField=new JTextField(20);
        passwordField=new JPasswordField(20);
        enterBtn=new JButton("登录");
        enterBtn.addActionListener(this);//注册监听器
        regBtn=new JButton("注册");
        //创建一个显示内容为管理员、学生、教师的组合框
        roleCombox=new JComboBox(new String[]{"普通用户","管理员"});
        accountLbl.setBounds(30,10,70,20);
        accountField.setBounds(90,10,150,20);
        paswordLbl.setBounds(30,40,70,20);
        passwordField.setBounds(90,40,150,20);
        roleLbl.setBounds(30,70,70,20);
        roleCombox.setBounds(90,70,150,20);
        enterBtn.setBounds(30,100,100,20);
        regBtn.setBounds(140,100,100,20);
        //将上述组建添加到顶层窗体中
        add(accountLbl);
        add(accountField);
        add(paswordLbl);
        add(passwordField);
        add(roleLbl);
        add(roleCombox);
        add(enterBtn);
        add(regBtn);
        regBtn.addActionListener(this);
        setVisible(true);

    }
    public static void main(String[] args){
        new LoginUI_Main("会议室预约系统—登录");
    }
    //登录按键的事件代码
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==enterBtn){
            String account = accountField.getText();
            String password = new String(passwordField.getPassword());
            String role = (String)roleCombox.getSelectedItem();
            if(account==null||"".equals(account.trim())||password==null||"".equals(password.trim())){
                JOptionPane.showMessageDialog(null,"用户账号或密码不能为空");
                return;
            }
            Login login = new Login();
            try{
                boolean valid = login.loginCheck(account,password,role);
                if(valid==true) {
                    JOptionPane.showMessageDialog(null, "登录成功，欢迎使用本系统");
                    if("管理员".equals(role)){
                        this.dispose();//关闭当前窗体进入相应的主界面
                        new AdminUI(account);//管理员登录成功进入管理员界面
                    }
                    else if("普通用户".equals(role)) {
                        this.dispose();//关闭当前窗体进入相应的主界面
                        new UserUI(account);//管理员登录成功进6y
                        // 入管理员界面
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"登录失败，请检查用户名或密码是否正确");
                }
            }catch(Exception ec) {
                JOptionPane.showMessageDialog(null, "登录时出现异常，异常原因为：" + ec.getMessage());
            }
        }else if (e.getSource() == regBtn){
            new RegisterFrame();
        }
    }
}
