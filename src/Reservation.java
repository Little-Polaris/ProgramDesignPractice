//Created by MrQi on 2023/12/9.

public class Reservation {
    private String resID;
    private String userID;
    private String RNum;
    private String subTime;
    private String checkTime;
    private String checkStatus;//0预约成功、1审核中、2预约驳回、3取消预约、4已签退
    private String note;
    public Reservation(){}
    public Reservation(String resID, String userID, String RNum, String subTime, String checkTime, String checkStatus, String note){
        this.resID = resID;
        this.userID = userID;
        this.RNum = RNum;
        this.subTime = subTime;
        this.checkTime = checkTime;
        this.checkStatus = checkStatus;
        this.note = note;
    }
    public String getResID() {
        return resID;
    }
    public String getUserID() {
        return userID;
    }
    public String getRNum() {
        return RNum;
    }
    public String getSubTime() {
        return subTime;
    }
    public String getCheckTime() {
        return checkTime;
    }
    public String getCheckStatus() {
        return checkStatus;
    }
    public String getNote() {
        return note;
    }
    public void setResID(String resID) {
        this.resID = resID;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }
    public void setRNum(String RNum) {
        this.RNum = RNum;
    }
    public void setSubTime(String subTime) {
        this.subTime = subTime;
    }
    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }
    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }
    public void setNote(String note) {
        this.note = note;
    }
}
