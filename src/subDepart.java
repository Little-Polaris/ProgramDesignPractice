/**
 * Created by xsw on 2017/6/2.
 */
/**
 * Created by xsw on 2017/6/1.
 */
//教室信息实体类

    //user_name, user_id, telNum, email, RNum,data_begin,data_end,ending_time,RUsage
public class subDepart {
    private String user_name;
    private String  user_id;
    private String telNum;
    private String email;
    private String RNum;
    private String  using_date;
   // private Object starting_time1=(String);
    //private Object ending_time;
    private String RUsage;
    private String starting_time;
    private String ending_time;
    private String flag;
    //构造方法
    public subDepart (){}
    public subDepart(String user_name,String user_id,String telNum, String email, String RNum,String using_date,String starting_time,String ending_time,String RUsage,String flag){
        this.user_name=user_name;
        this.user_id =user_id;
        this.telNum=telNum;
        this.email=email;
        this.RNum=RNum;
        this.using_date =using_date;
        this.starting_time=starting_time;
        this.ending_time=ending_time;
        this.RUsage=RUsage;
        this.flag=flag;
    }
    public subDepart (String user_name, String user_id, String telNum, String email, String RNum,String using_date,String starting_time1,String ending_time1,String RUsage){
        this.user_name=user_name;
        this.user_id =user_id;
        this.telNum=telNum;
        this.email=email;
        this.RNum=RNum;
        this.using_date =using_date;
        this.starting_time=starting_time;
        this.ending_time=ending_time;
        this.RUsage=RUsage;
    }
    public String getUser_name() { return user_name; }
    public void setUser_name (String user_name) {
        this.user_name = user_name;
    }

    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String academy) {
        this.user_id = user_id;
    }

    public String getTelNum() {
        return telNum;
    }
    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getRNum() {
        return RNum;
    }
    public void setRNum(String RNum) {this.RNum = RNum;}

    public String getusing_date() {
        return using_date;
    }
    public void setusing_date(String using_date) {
        this.using_date = using_date;
    }


    public String getstarting_time() {
        return starting_time;
    }
    public void Csetstarting_time(String starting_time) {
        this.starting_time = starting_time;
    }

    public String getending_time() {
        return ending_time;
    }
    public void Csetending_time(String ending_time) {this.ending_time = ending_time;}

    public String getRUsage() {
        return RUsage;
    }
    public void setRUsage(String RUsage) {
        this.RUsage = RUsage;
    }
    public String getflag() {
        return flag;
    }
    public void setflag(String flag) {
        this.flag = flag;
    }
}

