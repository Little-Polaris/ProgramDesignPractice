/**
 * Created by xsw on 2017/6/2.
 */

/**
 * Created by xsw on 2017/6/1.
 */
//教室信息实体类

public class subDepart {
    private String RName;
    private String RNum;
    private String RMember_count;
    private String RArea;
    private String RPic_dir;
    private String RUsage;
    private String user_name;
    private String user_id;
    private String telNum;
    private String email;
    private String using_date;
    private String starting_time;
    private String ending_time;
    private String flag;

    //构造方法
    public subDepart() {
    }

    public subDepart(String user_name, String user_id, String telNum, String email, String RNum, String using_date, String starting_time, String ending_time, String RUsage, String flag) {
        this.user_name = user_name;
        this.user_id = user_id;
        this.telNum = telNum;
        this.email = email;
        this.RNum = RNum;
        this.using_date = using_date;
        this.starting_time = starting_time;
        this.ending_time = ending_time;
        this.RUsage = RUsage;
        this.flag = flag;
    }

    public subDepart(String user_name, String user_id, String telNum, String email, String RNum, String using_date, String starting_time, String ending_time, String RUsage) {
        this.user_name = user_name;
        this.user_id = user_id;
        this.telNum = telNum;
        this.email = email;
        this.RNum = RNum;
        this.using_date = using_date;
        this.starting_time = starting_time;
        this.ending_time = ending_time;
        this.RUsage = RUsage;
    }

    public subDepart(String RName, String RNum, String RMember_count, String RArea, String RPic_dir, String RUsage, String user_name, String user_id, String telNum, String email, String using_date, String starting_time, String ending_time, String flag) {
        this.RName = RName;
        this.RNum = RNum;
        this.RMember_count = RMember_count;
        this.RArea = RArea;
        this.RPic_dir = RPic_dir;
        this.RUsage = RUsage;
        this.user_name = user_name;
        this.user_id = user_id;
        this.telNum = telNum;
        this.email = email;
        this.using_date = using_date;
        this.starting_time = starting_time;
        this.ending_time = ending_time;
        this.flag = flag;
    }

    public String getRName() {
        return RName;
    }

    public void setRName(String RName) {
        this.RName = RName;
    }

    public String getRNum() {
        return RNum;
    }

    public void setRNum(String RNum) {
        this.RNum = RNum;
    }

    public String getRMember_count() {
        return RMember_count;
    }

    public void setRMember_count(String RMember_count) {
        this.RMember_count = RMember_count;
    }

    public String getRArea() {
        return RArea;
    }

    public void setRArea(String RArea) {
        this.RArea = RArea;
    }

    public String getRPic_dir() {
        return RPic_dir;
    }

    public void setRPic_dir(String RPic_dir) {
        this.RPic_dir = RPic_dir;
    }

    public String getRUsage() {
        return RUsage;
    }

    public void setRUsage(String RUsage) {
        this.RUsage = RUsage;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
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

    public String getusing_date() {
        return using_date;
    }

    public void setusing_date(String using_date) {
        this.using_date = using_date;
    }

    public String getstarting_time() {
        return starting_time;
    }

    public void setstarting_time(String starting_time) {
        this.starting_time = starting_time;
    }

    public String getending_time() {
        return ending_time;
    }

    public void setending_time(String ending_time) {
        this.ending_time = ending_time;
    }

    public String getflag() {
        return flag;
    }

    public void setflag(String flag) {
        this.flag = flag;
    }
}

