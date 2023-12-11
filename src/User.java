//Created by MrQi on 2023/12/9.

public class User {
    private String name;
    private String id;
    private String gender;
    private String pwd;
    private String tel;
    private String email;
    public User (){};
    public User(String name, String id, String gender, String pwd, String tel, String email){
        this.name = name;
        this.id = id;
        this.gender = gender;
        this.pwd = pwd;
        this.tel = tel;
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }
    public String getGender(){
        return gender;
    }
    public String getPwd(){
        return pwd;
    }
    public String getTel(){
        return tel;
    }
    public String getEmail(){
        return email;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setId(String id){
        this.id = id;
    }
    public void setGender(String gender){
        this.gender = gender;
    }
    public void setPwd(String pwd){
        this.pwd = pwd;
    }
    public void setTel(String tel){
        this.tel = tel;
    }
    public void setEmail(String email){
        this.email = email;
    }
}
