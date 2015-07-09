package itac.yzu_taxi;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ChengYuShao on 15/5/26.
 * 已加入的揪團列表
 */
public class JoinedBoard extends Fragment{

    private index indexActivity;
    private partyNode partyNode;
    private ArrayList<party> parties;
    private RecyclerView recyclerView;
    private JoinedBoard_partyAdapter joinedBoard_partyAdapter;
    private Context context;
    private TextView TV_JoinedCount,TV_JoinedLastPartyStatus;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_joinedboard,container,false);

        init(v);//初始化
        setRecyclerView();//設定RecyclerView
        test();//輸入測資
        setStatusText();//設定頁面上方狀態文字

        return v;
    }

    private void init(View v){
        indexActivity = (index) this.getActivity();
        parties = new ArrayList<party>();
        context = this.getActivity().getBaseContext();
        recyclerView = (RecyclerView) v.findViewById(R.id.JoinedBoard_RecyclerView);
        TV_JoinedCount = (TextView) v.findViewById(R.id.JoinedBoard_textView_JoinedCount);
        TV_JoinedLastPartyStatus = (TextView) v.findViewById(R.id.JoinedBoard_textView_JoinedPartyStatus);
        partyNode = new partyNode();
    }

    private void setRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.context);

        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);

        AnimationSet animationSet = new AnimationSet(false);
        Animation animation = new AlphaAnimation(0,1);
        animation.setDuration(100);
        animationSet.addAnimation(animation);
        LayoutAnimationController controller = new LayoutAnimationController(animationSet, 1);
        recyclerView.setLayoutAnimation(controller);

        joinedBoard_partyAdapter = new JoinedBoard_partyAdapter(this.context, indexActivity);
        recyclerView.setAdapter(joinedBoard_partyAdapter);
    }

    private void setStatusText(){

        TV_JoinedCount.setText("你目前加入了"+Integer.toString(parties.size())+" 個共乘。");

        if(parties.size() != 0) {
            party tempParty = parties.get(0);

            SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日 HH:mm");
            String tempDateString = sdf.format(tempParty.getATime());
            TV_JoinedLastPartyStatus.setText(tempDateString + " 前往" + partyNode.getLocation(tempParty.getDestination()).getLocationName() + "的「" + tempParty.getPartyName() + "」，在" + partyNode.getLocation(tempParty.getAPoint()).getLocationName() + "集合，目前有" + Integer.toString(tempParty.getPartyMembers().size()) + " 個團員。");

        }
        else
            TV_JoinedLastPartyStatus.setText("尚未加入共乘。");
    }

    private void test(){
        user user1 = new user(1,"劭",Boolean.TRUE,9.9);
        user user2 = new user(2,"姿姿",Boolean.FALSE,9.9);
        Date temp = new Date();
        //user QQ = new user();
        //QQ.setUserID(1);
        ArrayList<user> QAQ = new ArrayList<user>();
        QAQ.add(user1);
        QAQ.add(user2);
        party test1 = new party();
        test1.setAPoint(1);
        test1.setATime(temp);
        test1.setDestination(2);
        test1.setPartyID(1);
        test1.setPartyStatus(1);
        // test1.setPartyMember(QAQ);
        test1.joinParty(user1);
        test1.joinParty(user2);
        user user3 = new user(3,"megshaoRR",Boolean.TRUE,8.9);
        user user4 = new user(4,"Q___Q",Boolean.FALSE,6.5);
        test1.joinParty(user3);
        test1.joinParty(user4);
        test1.setPartyName("姿姿好可愛<3");
        //for (int i = 0 ; i <10; i++)
        parties.add(test1);

        party test2 = new party();
        test2.setAPoint(3);
        test2.setDestination(1);
        test2.setATime(temp);
        test2.setPartyID(2);
        test2.setPartyStatus(0);
        test2.setPartyMember(QAQ);
        test2.setPartyName("徵婆 有點裝 會養");
        //test1.setPartyStatus(0);
        parties.add(test2);

        joinedBoard_partyAdapter.refresh(parties);
    }
}
