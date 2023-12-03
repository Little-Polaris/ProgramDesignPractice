/**
 * Created by xsw on 2017/5/26.
 */
//管理员界面

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class AdminUI extends JFrame implements ActionListener {
    private JMenuBar menuBar;
    //菜单：会议室信息管理、用户信息管理、待办事项、系统管理
    private JMenu classroomMenu,userMenu,waitMenu,exitMenu;
    //菜单项：添加信息、删除信息、修改信息
    private JMenuItem addItem,modifyItem,rwItem,uwItem;
    private JTable table;
    private JButton modifyBtn,refBtn;
    private JLabel RUserNameLbl, RUserIdLbl, RUserTelLbl, RUserEmailLbl,roomLbl, UsingDateLbl, StartingTimeLbl, EndingTimeLbl,purLbl;
    private JTextField RUserNameField, RUserIdField, RUserTelField, RUserEmailField,roomField, UsingDateField, StartingTimeField, EndingTimeField;
    private JTextArea UsageField;
    private JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
    DepartDao departDao;
    List<Depart> DepartList;
    int num;
    Object[][]data;
    AdminUI.TableListener a;

    public AdminUI(String account){
        setTitle("会议室预约系统"+"当前管理员为："+account);//设置窗体标题
        setSize(900,450);
        WindowUtils.displayOnDesktopCenter(this);//窗体居中的方法
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//正常关闭窗体
        //creatMenu();//添加菜单
        menuBar = new JMenuBar();//创建菜单栏
        //会议室信息管理菜单及菜单项的创建
        classroomMenu = new JMenu("会议室信息管理");
        addItem = new JMenuItem("添加会议室");
        addItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddDepartFrame();
            }
        });
        //delItem = new JMenuItem("删除会议室");
        modifyItem = new JMenuItem("修改信息");
        modifyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {new ad_modify();}
        });
        classroomMenu.add(addItem);
        classroomMenu.addSeparator();
        classroomMenu.add(modifyItem);

        //用户信息管理菜单及菜单项的创建
        userMenu = new JMenu("用户信息管理");
        addItem = new JMenuItem("添加用户");
        addItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new adduserDepartFrame();
            }
        });
        //delItem = new JMenuItem("删除用户");
        modifyItem = new JMenuItem("修改信息");
        modifyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new modify_user();
            }
        });

        userMenu.add(addItem);
        userMenu.addSeparator();
        userMenu.add(modifyItem);
        //待办事项菜单
        waitMenu = new JMenu("待办事项");
        rwItem = new JMenuItem("待办预定");
        uwItem = new JMenuItem("待办注册");
        uwItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new con_user();
            }
        });
        waitMenu.add(rwItem);
        userMenu.addSeparator();//设置分割线
        waitMenu.add(uwItem);
        //系统管理
        //exitMenu=new JMenu("退出");

        menuBar.add(classroomMenu);
        menuBar.add(userMenu);
        menuBar.add(waitMenu);
        //menuBar.add(exitMenu);
        this.setJMenuBar(menuBar);
        modifyBtn = new JButton("通过");
        refBtn = new JButton("驳回");
        setLayout(null);
        modifyBtn.setBounds(160, 300, 100, 20);
        refBtn.setBounds(360, 300, 100, 20);
        add(modifyBtn);
        add(refBtn);

        String columnsName[]={"占用情况","会议室编号","会议室名称","容量","面积", "用途", "图片"};
        try{
            departDao =new DepartDao();
            //调用departBao对象的findAll方法返回会议室信息列表
            DepartList = departDao.queryAll("room");
            //将list集合解析为JTable显示的数据模型
            num = DepartList.size();
            data=new Object[num][7];
            JButton [] pic_button = new JButton[num];
            int index=0;
            for(Depart depart: DepartList){
                data[index][0]=depart.getFlag();
                data[index][1]=depart.getRNum();
                data[index][2]=depart.getRName();
                data[index][3]=depart.getRMemberCount();
                data[index][4]=depart.getRArea();
                data[index][5]=depart.getRUsage();
                pic_button[index] = new JButton("查看");
                pic_button[index].setBounds(540, 72 + index * 15, 70, 15);
//                pic_button[index].addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        new pic();
//                    }
//                });
                //data[index][5] = pic_button[index];
                add(pic_button[index]);

                //data[index][5]=subdepart.getRPic_dir();
//                data[index][6]=subdepart.getstarting_time();
//                data[index][7]=subdepart.getending_time();
//                data[index][8]=subdepart.getRUsage();
//                data[index][9]=subdepart.getflag();
                index++;
            }
            table.setModel(new DefaultTableModel(data,columnsName));
        } catch (Exception ec){
            //JOptionPane.showMessageDialog(null,"查询时出现异常。异常原因为："+ec.getMessage());
            ec.printStackTrace();
        }
        table = new JTable(data,columnsName);
        TableListener a =new TableListener();
        table.addMouseListener(a);
        jScrollPane2.setBounds(20, 50, 600, 200);
        jScrollPane2.setViewportView(table);
        this.add(jScrollPane2);

        RUserNameLbl = new JLabel("姓名:");
        RUserIdLbl = new JLabel("账号:");
        RUserTelLbl = new JLabel("联系方式:");
        RUserEmailLbl = new JLabel("邮箱:");
        roomLbl = new JLabel("会议室编号:");
        UsingDateLbl = new JLabel("借用日期:");
        StartingTimeLbl = new JLabel("借用时间:");
        EndingTimeLbl = new JLabel("结束时间:");
        purLbl = new JLabel("用途:");
        RUserNameField = new JTextField(20);
        RUserIdField = new JTextField(20);
        RUserTelField = new JTextField(20);
        RUserEmailField = new JTextField(20);
        roomField = new JTextField(20);
        UsingDateField = new JTextField(20);
        StartingTimeField = new JTextField(20);
        EndingTimeField = new JTextField(20);
        UsageField = new JTextArea(100,50);

        RUserNameLbl.setBounds(630, 50, 90, 20);
        RUserNameField.setBounds(700, 50, 140, 20);// 90=30+60

        RUserIdLbl.setBounds(630, 80, 90, 20); // 40=10+20+10
        RUserIdField.setBounds(700, 80, 140, 20);

        RUserTelLbl.setBounds(630, 140, 90, 20);
        RUserTelField.setBounds(700, 140, 140, 20);// 90=30+60

        RUserEmailLbl.setBounds(630, 110, 90, 20); // 40=10+20+10
        RUserEmailField.setBounds(700, 110, 140, 20);

        roomLbl.setBounds(630, 170, 90, 20);
        roomField.setBounds(700, 170, 140, 20);// 90=30+60

        UsingDateLbl.setBounds(630, 200, 90, 20);
        UsingDateField.setBounds(700, 200, 140, 20);// 90=30+60

        StartingTimeLbl.setBounds(630, 230, 90, 20);
        StartingTimeField.setBounds(700, 230, 140, 20);// 90=30+60

        EndingTimeLbl.setBounds(630, 260, 90, 20);
        EndingTimeField.setBounds(700, 260, 140, 20);// 90=30+60

        purLbl.setBounds(630, 290, 90, 20);
        UsageField.setBounds(700, 290, 140, 80);// 90=30+60
        //将上述桌面添加进来
        add(RUserNameLbl);
        add(RUserNameField);
        add(RUserIdLbl);
        add(RUserIdField);
        add(RUserTelLbl);
        add(RUserTelField);
        add(RUserEmailLbl);
        add(RUserEmailField);
        add(roomLbl);
        add(roomField);
        add(UsingDateLbl);
        add(UsingDateField);
        add(StartingTimeLbl);
        add(StartingTimeField);
        add(EndingTimeLbl);
        add(EndingTimeField);
        add(purLbl);
        add(UsageField);

        modifyBtn.addActionListener(this);
        setVisible(true);//显示窗体
    }
    public static void main (String[] args){
        new AdminUI("admin");
    }

    class TableListener extends MouseAdapter {
        String RNum, RUserName, RUserId, RUserTel, RUserEmail , UsingDate, StartingTime, EndingTime, RUserUsage;
        public void mouseClicked(final MouseEvent e) {
            int selRow = table.getSelectedRow();
            RNum = table.getValueAt(selRow, 1).toString().trim();
            departDao = new DepartDao();
            try {
                Depart depart = departDao.query("room", RNum);
                RUserName = depart.getRUserName();
                RUserId = depart.getRUserId();
                RUserTel = depart.getRUserTel();
                RUserEmail = depart.getRUserEmail();
                UsingDate = depart.getUsingDate();
                StartingTime = depart.getStartingTime();
                EndingTime = depart.getEndingTime();
                RUserUsage = depart.getRUserUsage();
                RUserNameField.setText(RUserName);
                RUserIdField.setText(RUserId);
                RUserTelField.setText(RUserTel);
                RUserEmailField.setText(RUserEmail);
                roomField .setText(RNum);
                UsingDateField.setText(UsingDate);
                StartingTimeField.setText(StartingTime);
                EndingTimeField.setText(EndingTime);
                UsageField.setText(RUserUsage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

        }
    }

    public void actionPerformed(ActionEvent e){
        String user_name, user_id, telNum, email, room_num,date,starting,ending,RUsage;
        if (e.getSource() == modifyBtn) {
            user_name = RUserNameField.getText();
            user_id = RUserIdField.getText();
            telNum = RUserTelField.getText();
            email = RUserEmailField.getText();
            room_num = roomField.getText();
            date = UsingDateField.getText();
            starting = StartingTimeField.getText();
            ending = EndingTimeField.getText();
            RUsage = UsageField.getText();
            
            System.out.println(user_name);
            if (user_id == null || "".equals(user_id.trim())
                    || user_id == null || "".equals(user_id.trim())
                    || user_name == null || "".equals(user_name.trim())
                    || telNum == null || "".equals(telNum.trim())
                    || email == null || "".equals(email.trim())
                    || room_num == null || "".equals(room_num.trim())
                    || date == null || "".equals(date.trim())
                    || starting == null || "".equals(starting.trim())
                    || ending == null || "".equals(ending.trim())
                    || RUsage == null || "".equals(RUsage.trim()))
            {
                JOptionPane.showMessageDialog(null, "信息填写不完整，请重新检查！");
                return;
            }
            //调用DepartDao业务逻辑处理类来完成增加的操作
            subDepartDao subdepartDao = new subDepartDao();
            subDepart d = new subDepart(user_name, user_id, telNum, email, room_num,date,starting,ending,RUsage);
            try {
                subdepartDao.con_save(d);
                JOptionPane.showMessageDialog(null, "审核通过！");
                log.logger.debug("管理员审核了信息");
            } catch (Exception ec) {
                JOptionPane.showMessageDialog(null, "保存时出现异常，异常原因为:" + ec.getMessage());
                ec.printStackTrace();
            }
        }
        else {
            this.dispose();
        }
    }
}

