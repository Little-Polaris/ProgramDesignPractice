import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

//import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;

/**
 * Created by xsw on 2017/6/5.
 */
public class ad_modify extends JFrame implements ActionListener{
    //该窗体具备一个表和两个按钮
    private JButton modifyBtn, delBtn;
    private JTable table;
    private JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
    private JLabel RNameLbl, RNumLbl, RMember_countLbl, RAreaLbl, RUsageLbl, RPic_dirLbl;
    private JTextField RNameField, RNumField, RMember_countField, RAreaField, RUsageField, RPic_dirField;
    DepartDao departDao;
    List<Depart> dList;
    int num;
    Object[][]data;
    TableListener a;
    public ad_modify() {

        // 创建窗体
        setTitle("修改教室信息");
        setSize(750, 400);
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

        modifyBtn = new JButton("修改");
        delBtn = new JButton("删除");
        setLayout(null);
        modifyBtn.setBounds(100, 300, 100, 20);
        delBtn.setBounds(300, 300, 100, 20);
        add(modifyBtn);
        add(delBtn);

        String[] columnsName={"教室编号","所属学院","位置","设备","可容纳人数", "图片"};
        try{
            departDao=new DepartDao();
            //调用departBao对象的findAll方法返回教室信息列表
            dList = departDao.findAll();
            //将list集合解析为JTable显示的数据模型
            num = dList.size();
            JButton[] pic_button = new JButton[num];
            data=new Object[num][5];
            int index=0;
            for(Depart depart:dList){
                data[index][0]=depart.getRName();
                data[index][1]=depart.getRNum();
                data[index][2]=depart.getRMember_count();
                data[index][3]=depart.getRArea();
                //data[index][4]=depart.getRPic_dir();
//                pic_button[index] = new JButton("查看图片");
//                pic_button[index].setBounds(510, 200 + index * 20, 90, 20);
//                pic_button[index].addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        new pic();
//                    }
//                });
//                data[index][4] = pic_button[index];

                index++;
            }
            table.setModel(new DefaultTableModel(data,columnsName));
        } catch (Exception ec){
            //JOptionPane.showMessageDialog(null,"查询时出现异常。异常原因为："+ec.getMessage());
            ec.printStackTrace();
        }
        //初始化一个二维数组
        //String columnsName[]={"教室编号","所属学院","位置","设备","可容纳人数"};
        //Object[][] data={};
        table = new JTable(data,columnsName);

        TableListener a =new TableListener();
        table.addMouseListener(a);

        jScrollPane2.setBounds(20, 50, 450, 200);
        jScrollPane2.setViewportView(table);
        this.add(jScrollPane2);

        RNameLbl = new JLabel("会议室名称:");
        RNumLbl = new JLabel("会议室编号:");
        RMember_countLbl = new JLabel("容量:");
        RAreaLbl = new JLabel("面积:");
        RUsageLbl = new JLabel("用途:");
        RPic_dirLbl = new JLabel("图片:");



        RNameField = new JTextField(20);
        RNumField = new JTextField(20);
        RMember_countField = new JTextField(20);
        RAreaField = new JTextField(20);
        RUsageField = new JTextField(20);
        RPic_dirField = new JTextField(20);

        RNameLbl.setBounds(510, 50, 90, 20);
        RNameField.setBounds(590, 50, 140, 20);// 90=30+60

        RNumLbl.setBounds(510, 80, 90, 20); // 40=10+20+10
        RNumField.setBounds(590, 80, 140, 20);

        RMember_countLbl.setBounds(510, 140, 90, 20);
        RMember_countField.setBounds(590, 140, 140, 20);// 90=30+60

        RAreaLbl.setBounds(510, 110, 90, 20); // 40=10+20+10
        RAreaField.setBounds(590, 110, 140, 20);

        RUsageLbl.setBounds(510, 170, 90, 20);
        RUsageField.setBounds(590, 170, 140, 20);// 90=30+60

        RPic_dirLbl.setBounds(510, 200, 90, 20);
        RPic_dirField.setBounds(590, 200, 140, 20);// 90=30+60

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

        //注册事件监听
        modifyBtn.addActionListener(this);
        delBtn.addActionListener(this);
        setVisible(true);
    }
    public static void main(String[] args) {
        new ad_modify();
    }
    class TableListener extends MouseAdapter {
        String RName, RNum, RMember_count, RArea, RUsage, RPic_dir;
        public void mouseClicked( MouseEvent e) {
           // String clr_id, academy, place, equipment, peoNum;
            int selRow = table.getSelectedRow();
            RName = table.getValueAt(selRow, 0).toString().trim();
            RNum = table.getValueAt(selRow, 1).toString().trim();
            RArea = table.getValueAt(selRow, 2).toString().trim();
            RMember_count = table.getValueAt(selRow, 3).toString().trim();
            RUsage = table.getValueAt(selRow, 4).toString().trim();
            RPic_dir = table.getValueAt(selRow, 5).toString().trim();
            System.out.println(RName);
            System.out.println(RNum);
            System.out.println(RMember_count);
            System.out.println(RArea);
            System.out.println(RUsage);
            System.out.println(RPic_dir);

            RNameField.setText(RName);
            RNumField.setText(RNum);
            RMember_countField.setText(RMember_count);
            RAreaField.setText(RArea);
            RUsageField.setText(RUsage);
            RPic_dirField.setText(RPic_dir);
        }
    }
    public void actionPerformed(ActionEvent e) {
        //调用DepartDao业务逻辑处理类来完成增加的操作
        DepartDao departDao=new DepartDao();
        if (e.getSource() == modifyBtn) {
            String RName = RNameField.getText();
            String RNum = RNumField.getText();
            String RMember_count = RMember_countField.getText();
            String RArea = RAreaField.getText();
            String RUsage = RUsageField.getText();
            String RPic_dir = RPic_dirField.getText();
            if (RName == null || "".equals(RName.trim()) || RNum == null || "".equals(RNum)) {
                JOptionPane.showMessageDialog(null, "教室和所属学院不能为空");
                return;
            }
            //将用户输入的数据封装成一个Depart对象
            Depart d = new Depart(RName, RNum, RMember_count, RArea, RUsage, RPic_dir);
            try {
                departDao.modify(d);//修改数据
                JOptionPane.showMessageDialog(null, "教室信息修改成功");
                log.logger.debug("管理员修改了"+RName+"的信息");
            } catch (Exception ec) {
                JOptionPane.showMessageDialog(null, "保存时出现异常，异常原因为:" + ec.getMessage());
                ec.printStackTrace();
            }
        }
        else if(e.getSource() == delBtn){
            String RName = RNameField.getText();
            String RNum = RNumField.getText();
            String RMember_count = RMember_countField.getText();
            String RArea = RAreaField.getText();
            String RUsage = RUsageField.getText();
            String RPic_dir = RPic_dirField.getText();
            if (RName == null || "".equals(RName.trim()) || RNum == null || "".equals(RNum)) {
                JOptionPane.showMessageDialog(null, "教室和所属学院不能为空");
                return;
            }
            //将用户输入的数据封装成一个Depart对象
            Depart d = new Depart(RName, RNum, RMember_count, RArea, RUsage, RPic_dir);
            try {
                departDao.delete(d);//修改数据
                JOptionPane.showMessageDialog(null, "教室信息添加成功");
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
