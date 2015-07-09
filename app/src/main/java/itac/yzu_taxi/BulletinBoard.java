package itac.yzu_taxi;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.view.View.OnClickListener;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class BulletinBoard extends Fragment implements OnClickListener {

    private index indexActivity;
    private List<party> parties;
    private ListView listView;
    private Context context;
    private ImageView IV_Setting,IV_Searching;
    private RecyclerView recyclerView;
    private partyAdapter_RecyclerView recyclerAdapter;
    private Toolbar toolbar;
    private ImageButton IB_CreateGroup;
    private Handler mHandler;
    private Thread mThread;
    private HttpClient client;
    private JSONObject jObj;
    private JSONArray jArray;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_bulletinboard,container,false);

        init(v);//初始化
        setRecyclerView();//設定RecyclerView
        test();//測資

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        for(int i = 0 ; i < jObj.length() ;i++){
                            try {
                                JSONObject tempJObj = new JSONObject();
                                tempJObj = jObj.getJSONArray("result").getJSONObject(i);
                                //Date tempDate = new Date(tempJObj.getString("party_startTime"));
                                ArrayList<user> tempArrayList = new ArrayList<user>();
                                String[] tempMembersID = tempJObj.getString("party_member").split(",");
                                Log.e("member",tempMembersID[0]);
                                /*party tempParty = new party(tempJObj.getInt("party_ID")
                                                      ,tempJObj.getString("party_Name")
                                                      ,tempJObj.getInt("party_startLocation")
                                                      ,tempJObj.getInt("party_destination")
                                                      ,tempDate
                                                      ,tempJObj.getInt("party_status")
                                                      ,tempArrayList);*/
                                Log.e("testJSON","QAQ");
                            }catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                }
            }
        };

        return v;
    }

    private void init(View v){
        indexActivity = (index) this.getActivity();
        parties = new ArrayList<party>();
        context = this.getActivity().getBaseContext();
        recyclerView = (RecyclerView) v.findViewById(R.id.bulletinBoard_RecyclerView);
        IB_CreateGroup = (ImageButton) v.findViewById(R.id.fab_createGroup);
        setCreateGroupButton();
    }

    private void setCreateGroupButton(){
        IB_CreateGroup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                indexActivity.createGroup();//呼叫index的方選
            }
        });
    }

    private void getPartyListFromAPI(final int partyID){//透過API從Server端接收Party資料，輸入ID取得單一筆party
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    int inputPartyID = partyID;
                    client = new DefaultHttpClient();
                    String url = "http://10.0.2.2:1234/party/getPartyInfoByID";
                    HttpPost post = new HttpPost(url);
                    JSONObject postData = new JSONObject();
                    postData.put("party_id",inputPartyID);//將ID放入PostData中
                    post.setEntity(new StringEntity(postData.toString()));//將Data放入HttpPost
                    post.setHeader("Content-type", "application/json");//設定Header
                    HttpResponse rp = client.execute(post);
                    if(rp.getStatusLine().getStatusCode() == 200){
                        String result = EntityUtils.toString(rp.getEntity());//從API收到的response
                        jObj = new JSONObject(result);//轉成JSONObject
                        //jArray = new JSONArray(result);
                        Log.e("QAQ", result);
                        Message msg = new Message();
                        msg.what = 1;
                        mHandler.sendMessage(msg);//回傳給Handler更新資料
                    }
                    else{
                        Log.e("result","not 200ok");
                    }
                }
                catch (ClientProtocolException e) {
                    Log.d("cPE", e.getMessage().toString());
                } catch (IOException e) {
                    Log.d("IOE", e.getMessage().toString());
                } catch (Exception e){
                    Log.d("E", e.getMessage().toString());
                }
            }
        });
        mThread.start();
    }

    private void getPartyListFromAPI(){//透過API從Server端接收Party資料，輸入多個ID取得多筆party
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    client = new DefaultHttpClient();
                    String url = "http://10.0.2.2:1234/party/getPartyInfo";
                    HttpPost post = new HttpPost(url);
                    JSONObject postData = new JSONObject();
                    //postData.put("party_id",inputPartyID);
                    post.setEntity(new StringEntity(postData.toString()));
                    post.setHeader("Content-type", "application/json");
                    HttpResponse rp = client.execute(post);
                    if(rp.getStatusLine().getStatusCode() == 200){
                        String result = EntityUtils.toString(rp.getEntity());
                        result = "{ 'result' : " + result + '}';
                        Log.e("QAQ",result);
                        jObj = new JSONObject(result);
                        //jArray = new JSONArray(result);
                        Log.e("123",jObj.getJSONArray("result").getJSONObject(0).toString());

                        Message msg = new Message();
                        msg.what = 1;
                        mHandler.sendMessage(msg);
                    }
                    else{
                        Log.e("result","not 200ok");
                    }
                }
                catch (ClientProtocolException e) {
                    Log.d("cPE", e.getMessage().toString());
                } catch (IOException e) {
                    Log.d("IOE", e.getMessage().toString());
                } catch (Exception e){
                    Log.d("E", e.getMessage().toString());
                }
            }
        });
        mThread.start();
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
        recyclerAdapter.refresh((ArrayList<party>)parties);
    }

    private void setRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.context);

        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);

        AnimationSet animationSet = new AnimationSet(false);
        Animation animation = new AlphaAnimation(0, 1);
        animation.setDuration(100);
        animationSet.addAnimation(animation);
        LayoutAnimationController controller = new LayoutAnimationController(animationSet, 1);
        recyclerView.setLayoutAnimation(controller);

        recyclerAdapter = new partyAdapter_RecyclerView(this.context, indexActivity);
        recyclerView.setAdapter(recyclerAdapter);
    }


    @Override
    public void onClick(View view) {
        if(view == IV_Setting){

        }
    }
}
