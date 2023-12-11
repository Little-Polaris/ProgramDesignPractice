//Created by MrQi on 2023/12/10.

import javax.swing.*;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;
import java.util.List;

public class ModifyUserInfoFrame extends JDialog implements ActionListener {
    private JLabel nameLbl, IDLbl, genderLbl, pwdLbl, TelLbl, emailLbl;
    private JTextField nameField, IDField, genderField, pwdField, TelField, emailField;
    private JButton modifyBtn, cancelBtn;
    private JTable table;
    private JScrollPane jScrollPane = new javax.swing.JScrollPane();
    User userData;
    UserUI userUI;
    private static final long serialVersionUID = 1L;
    public void main() throws Exception {
        new ModifyUserInfoFrame(null, null);
    }
    public ModifyUserInfoFrame(User userData, UserUI userUI) {
        this.userData = userData;
        this.userUI = userUI;
        setTitle("修改个人信息");
        setSize(400, 450);
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

        nameLbl.setBounds(100, 40, 100, 20);
        nameField.setBounds(150, 40, 100, 20);
        IDLbl.setBounds(100, 80, 100, 20);
        IDField.setBounds(150, 80, 100, 20);
        genderLbl.setBounds(100, 120, 100, 20);
        genderField.setBounds(150, 120, 100, 20);
        pwdLbl.setBounds(100, 160, 100, 20);
        pwdField.setBounds(150, 160, 100, 20);
        TelLbl.setBounds(100, 200, 100, 20);
        TelField.setBounds(150, 200, 100, 20);
        emailLbl.setBounds(100, 240, 100, 20);
        emailField.setBounds(150, 240, 100, 20);
        modifyBtn.setBounds(100, 280, 80, 20);
        cancelBtn.setBounds(200, 280, 80, 20);
        Dao dao = new Dao();
        List<User> userList;
        User user = null;
        try {
            userList = dao.query(new User(), "ID", userData.getId());
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

    public ModifyUserInfoFrame() {
        setTitle("修改用户信息");
        setSize(900,450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        modifyBtn = new JButton("修改");
        cancelBtn = new JButton("取消");
        modifyBtn.setBounds(160, 300, 100, 20);
        cancelBtn.setBounds(360, 300, 100, 20);
        add(modifyBtn);
        add(cancelBtn);


        nameLbl = new JLabel("姓名:");
        nameField = new JTextField(20);
        IDLbl = new JLabel("账号:");
        IDField = new JTextField(20);
        genderLbl = new JLabel("性别:");
        genderField = new JTextField(20);
        pwdLbl = new JLabel("密码:");
        pwdField = new JTextField(20);
        TelLbl = new JLabel("电话:");
        TelField = new JTextField(20);
        emailLbl = new JLabel("邮箱:");
        emailField = new JTextField(20);

        nameLbl.setBounds(630, 50, 90, 20);
        nameField.setBounds(700, 50, 140, 20);
        IDLbl.setBounds(630, 80, 90, 20);
        IDField.setBounds(700, 80, 140, 20);
        genderLbl.setBounds(630, 110, 90, 20);
        genderField.setBounds(700, 110, 140, 20);
        pwdLbl.setBounds(630, 140, 90, 20);
        pwdField.setBounds(700, 140, 140, 20);
        TelLbl.setBounds(630, 170, 90, 20);
        TelField.setBounds(700, 170, 140, 20);
        emailLbl.setBounds(630, 200, 90, 20);
        emailField.setBounds(700, 200, 140, 20);

        add(nameLbl);
        add(nameField);
        add(IDLbl);
        add(IDField);
        add(genderLbl);
        add(genderField);
        add(pwdLbl);
        add(pwdField);
        add(TelLbl);
        add(TelField);
        add(emailLbl);
        add(emailField);

        modifyBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        freshTable();
        setVisible(true);
    }

    public void freshTable(){
        Dao dao = new Dao();
        List<User> userList;
        try {
            userList = dao.query(new User());
            String[] columnsName={"姓名","账号","性别","密码","电话","邮箱"};
            String[][] tableData = new String[userList.size()][6];
            for(int i = 0; i < userList.size(); i++){
                tableData[i][0] = userList.get(i).getName();
                tableData[i][1] = userList.get(i).getId();
                tableData[i][2] = userList.get(i).getGender();
                tableData[i][3] = userList.get(i).getPwd();
                tableData[i][4] = userList.get(i).getTel();
                tableData[i][5] = userList.get(i).getEmail();
            }
            table = new JTable(tableData, columnsName);
            table.setModel(new DefaultTableModel(tableData,columnsName){
                public boolean isCellEditable(int row, int column){
                    return false;
                }
            });
            TableListener tableListener = new TableListener();
            table.addMouseListener(tableListener);
            jScrollPane.setViewportView(table);
            jScrollPane.setBounds(10, 10, 600, 280);
            add(jScrollPane);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "查询时出现异常， 错误原因:" + e.getMessage());
        }
    }

    class TableListener extends MouseAdapter{
        public void mouseClicked(java.awt.event.MouseEvent e){
            int selectedRow = table.getSelectedRow();
            nameField.setText(table.getValueAt(selectedRow, 0).toString().trim());
            IDField.setText(table.getValueAt(selectedRow, 1).toString().trim());
            genderField.setText(table.getValueAt(selectedRow, 2).toString().trim());
            pwdField.setText(table.getValueAt(selectedRow, 3).toString().trim());
            TelField.setText(table.getValueAt(selectedRow, 4).toString().trim());
            emailField.setText(table.getValueAt(selectedRow, 5).toString().trim());
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == modifyBtn && userData != null) {
            Dao dao = new Dao();
            User user = new User();
            user.setName(nameField.getText());
            user.setId(IDField.getText());
            user.setGender(genderField.getText());
            user.setPwd(pwdField.getText());
            user.setTel(TelField.getText());
            user.setEmail(emailField.getText());
            try {
                List<Room> roomList = dao.query(new Room(), "RUserId", userData.getId());
                List<Reservation> reservationList = dao.query(new Reservation(), "userID", userData.getId());
                dao.update(userData, user);
                for(Room d : roomList){
                    d.setRUserName(user.getName());
                    d.setRUserId(user.getId());
                    d.setRUserTel(user.getTel());
                    d.setRUserEmail(user.getEmail());
                    dao.update(d, d);
                }
                for(Reservation d : reservationList){
                    d.setUserID(user.getId());
                    dao.update(d, d);
                }
            } catch (SQLException ex) {
                int errorCode = ex.getErrorCode();
                if(errorCode == 1062){
                    JOptionPane.showMessageDialog(null, "账号重复，请修改后重试");
                } else if (errorCode == 1406) {
                    String message = ex.getMessage();
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
                } else {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "修改时出现异常， 错误原因:" + ex.getMessage());
                }
                return ;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "修改成功");
            if(!userData.getName().equals(user.getName()) || !userData.getId().equals(user.getId())){
                JOptionPane.showMessageDialog(null, "账号或密码已修改，请重新登陆");
                this.dispose();
                userUI.dispose();
                new LoginUI_Main("会议室预约系统—登录");
            }
            this.dispose();
        } else if(e.getSource() == modifyBtn && userData == null){
            Dao dao = new Dao();
            User user = new User();
            user.setName(nameField.getText());
            user.setId(IDField.getText());
            user.setGender(genderField.getText());
            user.setPwd(pwdField.getText());
            user.setTel(TelField.getText());
            user.setEmail(emailField.getText());
            List<User> userList;
            try {
                userList = dao.query(new User(), "ID", user.getId());
                if(userList.isEmpty()){
                    JOptionPane.showMessageDialog(null, "账号不存在，请修改后重试");
                    return;
                }
                dao.update(userList.getFirst(), user);
                JOptionPane.showMessageDialog(null, "修改成功");
                if(table != null) {
                    freshTable();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "查询时出现异常， 错误原因:" + ex.getMessage());
                return ;
            }
        } else if (e.getSource() == cancelBtn) {
            this.dispose();
        }
    }
}
