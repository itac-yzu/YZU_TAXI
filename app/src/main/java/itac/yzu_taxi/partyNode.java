package itac.yzu_taxi;

import java.util.ArrayList;

/**
 * Created by ChengYuShao on 15/5/13.
 * 對應Location ID 的Location 資訊
 */
public class partyNode {
    //public String locationes[] = {"元智一館","元智宿舍","內壢火車站","中壢火車站"};

    private ArrayList<location> locationes;

    public String status[] = {"揪團中","人數已滿","已出發","已結束"};

    public partyNode(){
        locationes = new ArrayList<location>();
        location location0 = new location("元智一館",24.970456,121.2666584);
        locationes.add(location0);
        location location1 = new location("元智宿舍",24.966543, 121.268203);
        locationes.add(location1);
        location location2 = new location("內壢火車站",24.972769,121.258211);
        locationes.add(location2);
        location location3 = new location("中壢火車站",24.953635,121.225648);
        locationes.add(location3);
    }

    public location getLocation(int index) { return this.locationes.get(index); }
}
