package aditya.eclectika17;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * to handle interaction events.
 * Use the {@link event_tab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class event_detail_tabs_fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public View inflatedView;

    // TODO: Rename and change types of parameters
   String category,tab_name;
    private String mParam2;
    ArrayList itemname;



    public event_detail_tabs_fragment() {


        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     *
     * @return A new instance of fragment event_tab.
     */
    // TODO: Rename and change types and number of parameters
    public static event_detail_tabs_fragment  newInstance(String param1,String param2) {
        event_detail_tabs_fragment fragment = new event_detail_tabs_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            category = getArguments().getString(ARG_PARAM1);
            tab_name= getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        inflatedView = inflater.inflate(R.layout.event_detai_tab, container, false);

       TextView t=(TextView) inflatedView.findViewById(R.id.textView2);

                 final UserDatabase database = new UserDatabase(getActivity().getApplicationContext());

        final SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor;
        cursor=database.get_complete_event(category,db);
        cursor.moveToFirst();
            if(tab_name.equalsIgnoreCase("one")){


              t.setText(Html.fromHtml(cursor.getString(2)));}

        if(tab_name.equalsIgnoreCase("two")){
            t.setText(Html.fromHtml(cursor.getString(5)));}
        if(tab_name.equalsIgnoreCase("three")){
            t.setText(Html.fromHtml(cursor.getString(3)))
            ;}
        if(tab_name.equalsIgnoreCase("four")){
           final SharedPreferences preferences= getActivity().getSharedPreferences("mypref", Context.MODE_PRIVATE);
            inflatedView = inflater.inflate(R.layout.event_detai_tab2, container, false);
            final Button button =(Button) inflatedView.findViewById(R.id.button2);
                  if(database.is_registered(category,db).equalsIgnoreCase("yes")){


                      button.setEnabled(false);

                      ;
                      button.setText("Aleady Registered");
                  }else if (preferences.getString("logged_in","false").equalsIgnoreCase("false")){

                      button.setText("Guest Mode");
                      button.setEnabled(false);

                  }
            else{ button.setEnabled(true);
                      button.setText("Register");
                      button.setBackgroundColor(Color.parseColor("#191919"));}
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "We will notify you once registration completes", Toast.LENGTH_SHORT).show();
                button.setEnabled(false);
                button.setText("In process");
                String url ="http://eclectika.org/apiv2/api/user/events/update/user/events" ;
                StringRequest strReq = new StringRequest(Request.Method.PUT,
                        url, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.e("", "Login Response: " + response.toString());



                        try {
                            JSONObject jObj = new JSONObject(response);

                             database.insert_registered(category,db);
                            if(jObj.length()>2){

                                Toast.makeText(getActivity().getApplicationContext(), "Successfully Registered", Toast.LENGTH_LONG).show();
                            }


                        } catch (Exception e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(getActivity().getApplicationContext(), "Cannot Register Now", Toast.LENGTH_SHORT).show();


                            Log.e("Json error: ",e.getMessage());
                            button.setEnabled(true);
                            button.setText("Register");
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error:",error.getMessage());
                        Toast.makeText(getActivity().getApplicationContext(), "Cannot Register Now", Toast.LENGTH_SHORT).show();
                        button.setEnabled(true);
                        button.setText("Register");


                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() {
                        // Posting parameters to login url
                        Map<String, String> params = new HashMap<String, String>();


                        params.put("ec_id",  preferences.getString("ec_id","null"));
                       params.put("events", database.get_Registr(db)+category);





                        return params;
                    }

                };
                AppController.getInstance().addToRequestQueue(strReq, "");

            }
        }
            );
        }



        return inflatedView;}}
    //------------------MENU---------------------------------------------------------




