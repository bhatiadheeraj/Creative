package ws.wolfsoft.creative;

import com.google.firebase.database.Query;

/**
 * Created by bhatia on 25/07/16.
 */
public class Orders {

  public   String maketxt;
  public   String modeltext; public String yeartext; public String colortxt; public  String registrationtxt ; public  String time;public String key ; public CharSequence address;public  String usernames ;String mobileno;
    public Double Latitude ; public Double Longitude ;
    public String vehicle_service;

    public Orders(){

    }
    public Orders(String maketxt, String modeltext, String yeartext, String colortxt, String registrationtxt, String time , String key, CharSequence address,String usernames,String mobileno,Double Longitude , Double Latitude , String vehicle_service) {
        this.maketxt=maketxt;
        this.modeltext = modeltext ;
        this.yeartext = yeartext ;
        this.colortxt = colortxt ;
        this.registrationtxt =registrationtxt;
        this.time = time ;
        this.key = key ;
        this.address = address;
        this.usernames = usernames;
        this.mobileno = mobileno ;
        this.Latitude = Latitude ;
        this.Longitude = Longitude ;
        this.vehicle_service = vehicle_service ;

    }
}
