package itac.yzu_taxi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChengYuShao on 15/5/4.
 */
public class partyInfo extends FragmentActivity {

    private partyNode partyNode;
    private party curParty;
    private List<user> members;
    private memberAdapter memberAdapter;
    private ListView listView;
    private Context context;
    private TextView TV_StartLocation,TV_Destination,TV_Time,TV_MemberNumber,TV_PartyName;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.layout_partyinfo);
        setLayout();//設定Layout
        init();
        memberAdapter = new memberAdapter(context,R.layout.partyinfo_member_item,members);//initial adapter
        listView.setAdapter(memberAdapter);
        setGoogleMap();
        memberAdapter.refresh(members);//接受資料塞入members後，使Adapter refresh
    }

    private void setLayout(){
        RelativeLayout relativeLayout;
        relativeLayout = (RelativeLayout) findViewById(R.id.partyInfo_Base_RelativeLayout);

        DisplayMetrics metrics = getResources().getDisplayMetrics();//取得螢幕Size
        int heiPx = metrics.heightPixels; // height max of the device
        int widPx = metrics.widthPixels;//算出Layou長度寬度

        RelativeLayout.LayoutParams relativeParams;
        relativeParams = new RelativeLayout.LayoutParams((int)(widPx),(int)(heiPx * 0.3333));//透過LayoutParams設定Layou長寬
        relativeLayout.setLayoutParams(relativeParams);
    }

    private void init(){
        members = new ArrayList<user>();
        listView = (ListView) this.findViewById(R.id.partyInfo_ListView_Member);
        context = this.getBaseContext();
        TV_StartLocation = (TextView) this.findViewById(R.id.partyInfo_textView_StartLocation);
        TV_Destination = (TextView) this.findViewById(R.id.partyInfo_textView_Destination);
        TV_Time = (TextView) this.findViewById(R.id.partyInfo_textView_Time);
        TV_MemberNumber = (TextView) this.findViewById(R.id.partyInfo_textView_MemberNumber);
        TV_PartyName = (TextView) this.findViewById(R.id.partyInfo_partyName);
        partyNode = new partyNode();

        Bundle bundle = this.getIntent().getExtras();
        curParty = (party) bundle.getSerializable("party");//接收來自BulletinBoar點選的party

        members = curParty.getPartyMembers();//將傳送進來的成員資料存下來

        TV_StartLocation.setText(partyNode.getLocation(curParty.getAPoint()).getLocationName());//顯示起點終點
        TV_Destination.setText(partyNode.getLocation(curParty.getDestination()).getLocationName());
        //透過partyNod取得對應地點名稱
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日 HH:mm");
        String tempDateString = sdf.format(curParty.getATime());//將時間轉換成 MM月dd日 HH:mm

        TV_Time.setText(" "+tempDateString);
        TV_MemberNumber.setText(" "+Integer.toString(curParty.getPartyMemberNumber())+"/4");//顯示揪團人數
        if(curParty.getPartyMemberNumber() < 4)//根據人數改變顏色
            TV_MemberNumber.setTextColor(getResources().getColor(R.color.colorPrimary));//未滿時，為綠色
        else
            TV_MemberNumber.setTextColor(getResources().getColor(R.color.red));//已滿時，為紅色
        TV_PartyName.setText(curParty.getPartyName());
    }

    private void setGoogleMap(){//initial Google Map

        if (mMap == null) {//當Map不存在時，New一個出來
            mMap = ((SupportMapFragment) getSupportFragmentManager().
                    findFragmentById(R.id.partyInfo_GoogleMap)).getMap();

            if (mMap != null) {//當Ma存在時，setup Map
                setUpMap();
            }
        }
    }

    private void setUpMap(){
        // 刪除原來預設的內容
        //mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));

        // 建立位置的座標物件
        LatLng place = new LatLng(partyNode.getLocation(curParty.getAPoint()).getLongitude(), partyNode.getLocation(curParty.getAPoint()).getLatitude());


        // 加入地圖標記
        addMarker(place, partyNode.getLocation(curParty.getAPoint()).getLocationName(), " Google Maps v2!");

        // 移動地圖
        moveMap(place);
    }

    private void moveMap(LatLng place){
        // 建立地圖攝影機的位置物件
        CameraPosition cameraPosition =
                new CameraPosition.Builder()
                        .target(place)
                        .zoom(17)
                        .build();

        // 使用動畫的效果移動地圖
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    private void addMarker(LatLng place, String title, String snippet) {
        //BitmapDescriptor icon =
        //        BitmapDescriptorFactory.fromResource(R.drawable.car_icon);
        //Bitmap myIcon = BitmapFactory.decodeResource(getResources(),R.drawable.car_icon);

        //分別將地點資訊塞入Marker中顯示
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(place);
        markerOptions.title(title);
        markerOptions.draggable(false);
        markerOptions.visible(true);

        //將Marker加入地圖中
        Marker marker = mMap.addMarker(markerOptions);

    }

}
