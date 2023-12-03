/**
 * Created by xsw on 2017/6/1.
 */
//教室信息实体类

public class Depart {
    //会议室属性
    private String RName;
    private String RNum;
    private String RMemberCount;
    private String RArea;
    private String RPicDir;
    private String RUsage;
    private String RUserName;
    private String RUserId;
    private String RUserTel;
    private String RUserEmail;
    private String UsingDate;
    private String StartingTime;
    private String EndingTime;
    private String RUserUsage;
    private String Flag;
    //管理员信息
    private String AdName;
    private String AdId;
    private String AdGender;
    private String AdPwd;
    private String AdTel;
    private String AdEmail;
    //用户信息
    private String UserName;
    private String UserId;
    private String UserGender;
    private String UserPwd;
    private String UserTel;
    private String UserEmail;

    //构造方法
    public Depart (){}
    public Depart(String type, String RName, String RNum, String RMemberCount, String RArea, String RPicDir, String RUsage, String RUserName, String RUserId, String RUserTel, String RUserEmail, String UsingDate, String starting_time, String EndingTime, String RUserUsage, String Flag){
        if(type.equals("room")) {
            this.RName = RName;
            this.RNum = RNum;
            this.RMemberCount = RMemberCount;
            this.RArea = RArea;
            this.RPicDir = RPicDir;
            this.RUsage = RUsage;
            this.RUserName = RUserName;
            this.RUserId = RUserId;
            this.RUserTel = RUserTel;
            this.RUserEmail = RUserEmail;
            this.UsingDate = UsingDate;
            this.StartingTime = starting_time;
            this.EndingTime = EndingTime;
            this.RUserUsage = RUserUsage;
            this.Flag = Flag;
        }
    }

    public Depart(String type, String Name_RName, String Id_RNum, String Gender_RMemberCount, String Pwd_RArea, String Tel_RPicDir, String Email_RUsage){
        if(type.equals("room")){
            this.RName = Name_RName;
            this.RNum = Id_RNum;
            this.RMemberCount = Gender_RMemberCount;
            this.RArea = Pwd_RArea;
            this.RPicDir = Tel_RPicDir;
            this.RUsage = Email_RUsage;
        } else if(type.equals("admin")){
            this.AdName =Name_RName;
            this.AdId =Id_RNum;
            this.AdGender = Gender_RMemberCount;
            this.AdPwd =Pwd_RArea;
            this.AdTel =Tel_RPicDir;
            this.AdEmail =Email_RUsage;
        } else if (type.equals("user")) {
            this.UserName =Name_RName;
            this.UserId =Id_RNum;
            this.UserGender = Gender_RMemberCount;
            this.UserPwd =Pwd_RArea;
            this.UserTel =Tel_RPicDir;
            this.UserEmail =Email_RUsage;
        }
    }
    //获取属性
    //获取会议室属性
    public String getRName() {
        return RName;
    }
    public String getRNum() {
        return RNum;
    }
    public String getRMemberCount() {
        return RMemberCount;
    }
    public String getRArea() {
        return RArea;
    }
    public String getRPicDir() {
        return RPicDir;
    }
    public String getRUsage() {
        return RUsage;
    }
    public String getRUserName() {
        return RUserName;
    }
    public String getRUserId() {
        return RUserId;
    }
    public String getRUserTel() {
        return RUserTel;
    }
    public String getRUserEmail() {
        return RUserEmail;
    }
    public String getUsingDate() {
        return UsingDate;
    }
    public String getStartingTime() {
        return StartingTime;
    }
    public String getEndingTime() {
        return EndingTime;
    }
    public String getRUserUsage() {
        return RUserUsage;
    }
    public String getFlag() {
        return Flag;
    }
    //获取管理员信息
    public String getAdName() {
        return AdName;
    }
    public String getAdId() {
        return AdId;
    }
    public String getAdGender(){
        return AdGender;
    }
    public String getAdPwd() {
        return AdPwd;
    }
    public String getAdTel() {
        return AdTel;
    }
    public String getAdEmail() {
        return AdEmail;
    }
    //获取用户信息
    public String getUserName() {
        return UserName;
    }
    public String getUserId() {
        return UserId;
    }
    public String getUserGender(){
        return UserGender;
    }
    public String getUserPwd() {
        return UserPwd;
    }
    public String getUserTel() {
        return UserTel;
    }
    public String getUserEmail() {
        return UserEmail;
    }
    //设置属性
    //设置会议室属性
    public void setRName(String RName) {
        this.RName = RName;
    }
    public void setRNum(String RNum) {
        this.RNum = RNum;
    }
    public void setRMemberCount(String RMemberCount) {
        this.RMemberCount = RMemberCount;
    }
    public void setRArea(String RArea) {
        this.RArea = RArea;
    }
    public void setRPicDir(String RPicDir) {
        this.RPicDir = RPicDir;
    }
    public void setRUsage(String RUsage) {
        this.RUsage = RUsage;
    }
    public void setRUserName(String RUserName) {
        this.RUserName = RUserName;
    }
    public void setRUserId(String RUserId) {
        this.RUserId = RUserId;
    }
    public void setRUserTel(String RUserTel) {
        this.RUserTel = RUserTel;
    }
    public void setRUserEmail(String RUserEmail) {
        this.RUserEmail = RUserEmail;
    }
    public void setUsingDate(String UsingDate) {
        this.UsingDate = UsingDate;
    }
    public void setStartingTime(String StartingTime) {
        this.StartingTime = StartingTime;
    }
    public void setEndingTime(String EndingTime) {
        this.EndingTime = EndingTime;
    }
    public void setRUserUsage(String RUserUsage) {
        this.RUserUsage = RUserUsage;
    }
    public void setFlag(String Flag) {
        this.Flag = Flag;
    }
    //设置管理员信息
    public void setAdName(String AdName) {
        this.AdName = AdName;
    }
    public void setAdId(String AdId) {
        this.AdId = AdId;
    }
    public void setAdGender(String AdGender){
        this.AdGender =AdGender;
    }
    public void setAdPwd(String AdPwd) {
        this.AdPwd = AdPwd;
    }
    public void setAdTel(String AdTel) {
        this.AdTel = AdTel;
    }
    public void setAdEmail(String AdEmail) {
        this.AdEmail = AdEmail;
    }
    //设置用户信息
    public void setUserName(String UserName) {
        this.UserName = UserName;
    }
    public void setUserId(String UserId) {
        this.UserId = UserId;
    }
    public void setUserGender(String UserGender){
        this.UserGender =UserGender;
    }
    public void setUserPwd(String UserPwd) {
        this.UserPwd = UserPwd;
    }
    public void setUserTel(String UserTel) {
        this.UserTel = UserTel;
    }
    public void setUserEmail(String UserEmail) {
        this.UserEmail = UserEmail;
    }
}
