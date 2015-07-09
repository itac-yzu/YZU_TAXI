package itac.yzu_taxi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ChengYuShao on 15/4/20.
 * party data structure
 */
public class party implements Serializable {
    private int partyID;
    private int APoint;//出發點
    private int destination;//目的地
    private Date ATime;//出發時間
    private int partyStatus;//揪團狀態
    private ArrayList<user> partyMember;//揪團成員array
    private String partyName;

    public party(){
        this.partyID = 0;
        this.APoint = 0;
        this.destination = 0;
        this.ATime = new Date();
        this.partyStatus = 0;
        this.partyMember = new ArrayList<user>();
        this.partyName = "";
    }

    public party(int inputPartyID,String inputPartyName, int inputAPoint, int inputDestination, Date inputATime, int inputPartyStatus, ArrayList<user> inputPartyMember){
        this.partyID = inputPartyID;
        this.APoint = inputAPoint;
        this.destination = inputDestination;
        this.ATime = inputATime;
        this.partyStatus = inputPartyStatus;
        this.partyMember = inputPartyMember;
        this.partyName = inputPartyName;
    }

    public void setPartyID(int inputPartyID)    { this.partyID = inputPartyID; }

    public void setAPoint(int inputAPoint)  { this.APoint = inputAPoint;}

    public void setDestination(int inputDestination)    { this.destination = inputDestination; }

    public void setATime(Date inputATime)   { this.ATime = inputATime; }

    public void setPartyStatus(int inputPartyStatus)    { this.partyStatus = inputPartyStatus; }

    public void setPartyMember(ArrayList<user> users)   {this.partyMember = users; }

    public void setPartyName(String name) {this.partyName = name; }

    public boolean joinParty(user newUser){
        if(this.partyMember.add(newUser)){
            if(this.partyMember.size() == 4)
                this.partyStatus = 1;
            return true;
        }
        else
            return false;
    }

    public boolean leaveParty(user leaveUser){
        if(this.partyMember.remove(leaveUser)){
            if(this.partyMember.size() < 4)
                this.partyStatus = 0;
            return true;
        }
        else
            return false;
    }

    public boolean editParty(int inputAPoint, int inputDestination, Date inputATime, int inputPartyStatus){
        return true;
    }

    public int getAPoint() {return this.APoint; }

    public int getDestination() {return this.destination; }

    public Date getATime() {return this.ATime; }

    public int getPartyStatus() {return this.partyStatus; }

    public int getPartyMemberNumber() {return this.partyMember.size(); }

    public user getMember(int index) {return this.partyMember.get(index); }

    public ArrayList<user> getPartyMembers() {return this.partyMember; }

    public String getPartyName() {return this.partyName; }
}
