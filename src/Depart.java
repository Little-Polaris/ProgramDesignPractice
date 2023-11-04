/**
 * Created by xsw on 2017/6/1.
 */
//教室信息实体类

public class Depart {
    private String RName;
    private String RNum;
    private String RMember_count;
    private String RArea;
    private String RPic_dir;
    //private String use_time;

    //构造方法
    public Depart (){}
    public Depart(String RNum,String RMember_count,String RArea,String RPic_dir){
        this.RNum=RNum;
        this.RMember_count=RMember_count;
        this.RArea=RArea;
        this.RPic_dir=RPic_dir;
        //this.use_time=use_time;
    }
    public Depart (String RName,String RNum,String RMember_count,String RArea,String RPic_dir){
        this.RName=RName;
        this.RNum=RNum;
        this.RMember_count=RMember_count;
        this.RArea=RArea;
        this.RPic_dir=RPic_dir;
        //this.use_time=use_time;
    }
    public String getRName() {
        return RName;
    }
    public void setRName (String RName) {
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

    /*public String getUse_time(){return use_time;}
    public void setUse_time(String use_time){this.use_time=use_time;}*/
}
