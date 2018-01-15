package aditya.eclectika17;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 31-01-2017.
 */
public class update_app {
    String logged_in,ec_id,fb_id,web_access_token;
    int user_version,event_version,day1_version,day2_version,day3_version;
    int server_user_version,server_event_version,server_day1_version,server_day2_version,server_day3_version;
    SharedPreferences preferences ; Context context;

   public update_app(Context context){
       this.context=context;
       preferences= context.getSharedPreferences("mypref",Context.MODE_PRIVATE);
       logged_in= preferences.getString("logged_in","false");
       ec_id= preferences.getString("ec_id","not_registered");
       user_version= preferences.getInt("user_version",0);
       event_version= preferences.getInt("event_version",0);
       day1_version= preferences.getInt("day1_version",0);
       day3_version= preferences.getInt("day3_version",0);
       day2_version= preferences.getInt("day2_version",0);

       server_user_version= preferences.getInt("server_user_version",0);
       server_event_version= preferences.getInt("server_event_version",0);
       server_day1_version= preferences.getInt("server_day1_version",0);
       server_day3_version= preferences.getInt("server_day3_version",0);
       server_day2_version= preferences.getInt("server_day2_version",0);






   }



     public  void  update_schedule(final Context context){

         JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET,  "http://eclectika.org/apiv2/api/schedule", null,
                 new Response.Listener<JSONArray>()
                 {
                     @Override
                     public void onResponse(JSONArray p) {
                         // display response
                         Log.d("Response", p.toString());
                         try {

                             // JSONObject product= jObj.getJSONObject("product");

                            if (p.length()>0){
                                JSONObject j;
                                 UserDatabase database= new UserDatabase(context);
                                 SQLiteDatabase db;
                                 db= database.getWritableDatabase();

                                for (int z=0;z<p.length();z++){
                                   j= p.getJSONObject(z);
                                    if(j.getString("date").equalsIgnoreCase("1")){

                                        database.update_schedule(j.getString("name"),j.getString("venue"),j.getString("time"),"day1",db);
                                    }
                                    else if (j.getString("date").equalsIgnoreCase("2")){
                                        database.update_schedule(j.getString("name"),j.getString("venue"),j.getString("time"),"day2",db);
                                    }
                                    else{
                                        database.update_schedule(j.getString("name"),j.getString("venue"),j.getString("time"),"day3",db);
                                    }

                                }

                                db.close();



                             }}

                         catch (Exception e) {
                             // JSON error
                             e.printStackTrace();
                             Toast.makeText(context,"App data cannot be refreshed ,please refresh manually",Toast.LENGTH_LONG);

                         }

                     }
                 },
                 new Response.ErrorListener()
                 {
                     @Override
                     public void onErrorResponse(VolleyError error) {
                         Log.d("Error.Response", "");
                     }
                 }
         );


         AppController.getInstance().addToRequestQueue(getRequest);}


   public void update_events(final Context context){








           JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET,  "http://eclectika.org/apiv2/api/event", null,
                   new Response.Listener<JSONArray>()
                   {
                       @Override
                       public void onResponse(JSONArray p) {
                           // display response
                           Log.d("Response", p.toString());
                           try {

                               // JSONObject product= jObj.getJSONObject("product");

                               if (p.length()>0){

                                   UserDatabase database= new UserDatabase(context);
                                   SQLiteDatabase db;
                                   db= database.getWritableDatabase();
                                   database.delete_events(db);


                                   int i=0;JSONObject j;
                                   while (i<p.length())
                                   {
                                       j=p.getJSONObject(i);

                                       database.update_events(j.getString("event_slug"),j.getString("name"),j.getString("description"),j.getString("contacts"),j.getString("prize"),j.getString("rules"),
                                               j.getString("venue"),j.getString("datetime"),j.getString("category_id"),"na","na",db);
                                       i++;
                                   }
                                   db.close();





                               }





                               // close this activity




                           } catch (Exception e) {
                               // JSON error
                               e.printStackTrace();
                               Toast.makeText(context,"App data cannot be refreshed ,please refresh manually",Toast.LENGTH_LONG);

                           }



                       }
                   },
                   new Response.ErrorListener()
                   {
                       @Override
                       public void onErrorResponse(VolleyError error) {
                           Log.d("Error.Response", "");
                       }
                   }
           );


           AppController.getInstance().addToRequestQueue(getRequest);



   }


    public void update_user(final Context context){

   if(logged_in.equalsIgnoreCase("true")){

    if(true){
                               String id= ec_id;

         JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET,  "http://eclectika.org/apiv2/api/user/"+id, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject p) {
                        // display response
                        Log.d("Response", p.toString());
                        try {

                            // JSONObject product= jObj.getJSONObject("product");

                            if (p.length()>0){

                                UserDatabase database= new UserDatabase(context);
                                SQLiteDatabase db;
                                db= database.getWritableDatabase();
                                database.delete_events(db);
                                String r= p.getString("registered_for");
                                if(!r.isEmpty()){

                                    String[] separated = r.split(",");
                                    database.delete_registered(db);
                                    int i=0;
                                    while(i<separated.length){

                                        database.insert_registered(separated[i],db);
                                        i++;

                                    }}

                                db.close();





                            }





                            // close this activity




                        } catch (Exception e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(context,"App data cannot be refreshed ,please refresh manually",Toast.LENGTH_LONG);

                        }

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", "");
                    }
                }
        );


        AppController.getInstance().addToRequestQueue(getRequest);}

    }




    }








}
