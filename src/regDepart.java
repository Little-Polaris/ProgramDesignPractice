/**
 * Created by xsw on 2017/6/3.
 */
public class regDepart {
    private String con_uname;
    private String  con_id;
    private String con_pwd;
    private String con_gender;
    private String con_telNum;
    private String  con_email;

    //构造方法
    public regDepart (){}
    public regDepart(String name,  String pwd, String gender, String tel,String email){
        this.con_uname=name;
        this.con_pwd =pwd;
        this.con_gender=gender;
        this.con_telNum=tel;
        this.con_email=email;

    }
    public regDepart (String id,String name,  String pwd, String gender, String tel,String email){
        this.con_id=id;
        this.con_uname =name;
        this.con_pwd =pwd;
        this.con_gender=gender;
        this.con_telNum=tel;
        this.con_email=email;

    }
    public String getId() { return con_id; }
    public void setId (String con_id) {
        this.con_id = con_id;
    }

    public String getUname() {return con_uname;}
    public void setUname(String con_uname) {
        this.con_uname = con_uname;
    }

    public String getPwd() {
        return con_pwd;
    }
    public void setPwd(String con_pwd) {
        this.con_pwd = con_pwd;
    }

    public String getGender() {
        return con_gender;
    }
    public void setGender(String con_gender) {
        this.con_gender = con_gender;
    }

    public String getTelNum() {
        return con_telNum;
    }
    public void setTelNum(String con_telNum) {this.con_telNum = con_telNum;}

    public String getEmail() {
        return con_email;
    }
    public void setEmail(String con_email) {
        this.con_email = con_email;
    }

}
