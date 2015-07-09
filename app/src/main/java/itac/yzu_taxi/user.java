package itac.yzu_taxi;

import java.io.Serializable;

/**
 * Created by ChengYuShao on 15/4/20.
 */
public class user implements Serializable {
    private int userID;
    private String userName;
    private Boolean userSex;//male 0 female 1
    private double userAppraisal;


    public user() {
        this.userID = 0;
        this.userName = "";
        this.userSex = Boolean.TRUE;
        this.userAppraisal = 0;
    }


    public user(int userID, String userName, Boolean userSex, double userAppraisal) {
        this.userID = userID;
        this.userName = userName;
        this.userSex = userSex;
        this.userAppraisal = userAppraisal;
    }

    public void setUserID(int userID)   {this.userID = userID;}
    public void setUserName(String userName)    {this.userName = userName;}
    public void setUserSex(Boolean userSex)     {this.userSex = userSex;}
    public void setUserAppraisal(double userAppraisal)  {this.userAppraisal = userAppraisal;}

    public int getUserID()  {return this.userID;}
    public String getUserName()     {return this.userName;}
    public Boolean getUserSex()     {return this.userSex;}
    public double getUserAppraisal()    {return this.userAppraisal;}

    public Boolean isMain()     {return true; }
    public Boolean isFriend()   {return true; }

}