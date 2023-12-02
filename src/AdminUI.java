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
    private JLabel unLbl, idLbl, telLbl,emailLbl,roomLbl,dateLbl,startLbl,endLbl,purLbl;
    private JTextField unField,idField,telField,emailField,roomField,dateField,startField,endField;
    private JTextArea purField;
    private JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
    subDepartDao subdepartDao;
    List<subDepart> dList;
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

        //初始化一个二维数组
        //String columnsName[]={"姓名","学号","联系电话","邮箱","会议室","日期","时间","时长","用途"};
        String columnsName[]={"占用情况","会议室编号","会议室名称","容量","面积", "用途", "图片"};
        try{
            subdepartDao=new subDepartDao();
            //调用departBao对象的findAll方法返回会议室信息列表
            dList = subdepartDao.findAll();
            //将list集合解析为JTable显示的数据模型
            num = dList.size();
            data=new Object[num][7];
            JButton [] pic_button = new JButton[num];
            int index=0;
            for(subDepart subdepart:dList){
                data[index][0]=subdepart.getflag();
                data[index][1]=subdepart.getRNum();
                data[index][2]=subdepart.getRName();
                data[index][3]=subdepart.getRMember_count();
                data[index][4]=subdepart.getRArea();
                data[index][5]=subdepart.getRUsage();
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

        unLbl = new JLabel("姓名:");
        idLbl= new JLabel("学号:");
        telLbl = new JLabel("联系方式:");
        emailLbl = new JLabel("邮箱:");
        roomLbl = new JLabel("会议室编号:");
        dateLbl = new JLabel("借用日期:");
        startLbl = new JLabel("借用时间:");
        endLbl = new JLabel("结束时间:");
        purLbl = new JLabel("用途:");
        unField = new JTextField(20);
        idField = new JTextField(20);
        telField = new JTextField(20);
        emailField = new JTextField(20);
        roomField = new JTextField(20);
        dateField = new JTextField(20);
        startField = new JTextField(20);
        endField = new JTextField(20);
        purField = new JTextArea(100,50);

        unLbl.setBounds(630, 50, 90, 20);
        unField.setBounds(700, 50, 140, 20);// 90=30+60

        idLbl.setBounds(630, 80, 90, 20); // 40=10+20+10
        idField.setBounds(700, 80, 140, 20);

        telLbl.setBounds(630, 140, 90, 20);
        telField.setBounds(700, 140, 140, 20);// 90=30+60

        emailLbl.setBounds(630, 110, 90, 20); // 40=10+20+10
        emailField.setBounds(700, 110, 140, 20);

        roomLbl.setBounds(630, 170, 90, 20);
        roomField.setBounds(700, 170, 140, 20);// 90=30+60

        dateLbl.setBounds(630, 200, 90, 20);
        dateField.setBounds(700, 200, 140, 20);// 90=30+60

        startLbl.setBounds(630, 230, 90, 20);
        startField.setBounds(700, 230, 140, 20);// 90=30+60

        endLbl.setBounds(630, 260, 90, 20);
        endField.setBounds(700, 260, 140, 20);// 90=30+60

        purLbl.setBounds(630, 290, 90, 20);
        purField.setBounds(700, 290, 140, 80);// 90=30+60
        //将上述桌面添加进来
        add(unLbl);
        add(unField);
        add(idLbl);
        add(idField);
        add(telLbl);
        add(telField);
        add(emailLbl);
        add(emailField);
        add(roomLbl);
        add(roomField);
        add(dateLbl);
        add(dateField);
        add(startLbl);
        add(startField);
        add(endLbl);
        add(endField);
        add(purLbl);
        add(purField);

        modifyBtn.addActionListener(this);
        setVisible(true);//显示窗体
    }
    public static void main (String[] args){
        new AdminUI("admin");
    }

    class TableListener extends MouseAdapter {
        String user_name, user_id, telNum, email, room_num,date,starting,ending,RUsage;
        public void mouseClicked(final MouseEvent e) {
            int selRow = table.getSelectedRow();
            user_name = table.getValueAt(selRow, 0).toString().trim();
            user_id = table.getValueAt(selRow, 1).toString().trim();
            telNum = table.getValueAt(selRow, 2).toString().trim();
            email = table.getValueAt(selRow, 3).toString().trim();
            room_num = table.getValueAt(selRow, 4).toString().trim();
            date = table.getValueAt(selRow, 5).toString().trim();
            starting = table.getValueAt(selRow, 6).toString().trim();
            ending = table.getValueAt(selRow, 7).toString().trim();
            RUsage = table.getValueAt(selRow, 8).toString().trim();
            unField.setText(user_name);
            idField .setText(user_id);
            telField .setText(telNum);
            emailField .setText(email);
            roomField .setText(room_num);
            dateField .setText(date);
            startField .setText(starting);
            endField .setText(ending);
            purField .setText(RUsage);
        }
    }
    //添加数据
    //待办事项按钮的实现
    //审核通过
    public void actionPerformed(ActionEvent e){
        String user_name, user_id, telNum, email, room_num,date,starting,ending,RUsage;
        if (e.getSource() == modifyBtn) {
            user_name = unField.getText();
            user_id = idField.getText();
            telNum = telField.getText();
            email = emailField.getText();
            room_num = roomField.getText();
            date = dateField.getText();
            starting = startField.getText();
            ending = endField.getText();
            RUsage = purField.getText();
            
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
            //将用户输入的数据封装成一个Depart对象
            subDepart d = new subDepart(user_name, user_id, telNum, email, room_num,date,starting,ending,RUsage);
            try {
                subdepartDao.con_save(d);//保存数据
                JOptionPane.showMessageDialog(null, "审核通过！");
                log.logger.debug("管理员审核了信息");
            } catch (Exception ec) {
                JOptionPane.showMessageDialog(null, "保存时出现异常，异常原因为:" + ec.getMessage());
                ec.printStackTrace();
            }
        }
        else {
            this.dispose();//关闭当前窗口
        }
    }
    //用户注册

}

