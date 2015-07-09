package itac.yzu_taxi;

/**
 * Created by ChengYuShao on 15/5/13.
 * Location data structure
 */
public class location {
    private String locationName;
    private Double longitude;
    private Double latitude;

    public location(){
        this.locationName = "";
        this.longitude = 0.0;
        this.latitude = 0.0;
    }

    public location(String name, Double longitude, Double latitude){
        this.locationName = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getLocationName() { return this.locationName; }

    public Double getLongitude() { return this.longitude; }

    public Double getLatitude() { return this.latitude; }
}
