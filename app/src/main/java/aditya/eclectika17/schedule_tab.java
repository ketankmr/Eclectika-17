package aditya.eclectika17;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * to handle interaction events.
 * Use the {@link event_tab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class schedule_tab extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public View inflatedView;

    // TODO: Rename and change types of parameters
    String category;
    private String mParam2,day;
    ArrayList itemname,venue,time,regis;



    public schedule_tab() {


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
    public static schedule_tab newInstance(String param1) {
        schedule_tab fragment = new schedule_tab();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            category = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        inflatedView = inflater.inflate(R.layout.event_details, container, false);


        {
            itemname = new ArrayList();
            regis = new ArrayList();
            venue = new ArrayList();
            time = new ArrayList();



            ListView listView = (ListView) inflatedView.findViewById(R.id.android_listlist);




            getData(category);
        }




        return inflatedView;}
    //------------------MENU---------------------------------------------------------





    public void getData(String category)
    {

        Cursor cursor;



        ListView list=(ListView)inflatedView.findViewById(R.id.android_listlist);

        itemname.clear();
        venue.clear();
        time.clear();
        String day;
        Integer c=0;
        UserDatabase database = new UserDatabase(getActivity().getApplicationContext());
        SQLiteDatabase db= database.getReadableDatabase();
        if (category.equalsIgnoreCase("day1"))
        { cursor= database.Day1(db);
             day="1";}
        else if(category.equalsIgnoreCase("day2")){
             cursor= database.Day2(db);
            day="2";
        }
        else { cursor= database.Day3(db);day="3";}


       if (cursor.moveToFirst())

        {do {





            itemname.add(c,cursor.getString(0));
            if(database.get_reminder(cursor.getString(0),day,db).equalsIgnoreCase("true")){
                regis.add(c,"true");
            }else {regis.add(c,"false");};


            time.add(c,cursor.getString(2));
            venue.add(c,cursor.getString(1));


            c++;
        }// while (cursor.moveToNext());}
        while (cursor.moveToNext());}



        ScheduleAdapter adapter=new ScheduleAdapter(getActivity(), itemname, regis,time,itemname,venue,day);

        list.setAdapter(adapter);





    }


}





/**
 * This interface must be implemented by activities that contain this
 * fragment to allow an interaction in this fragment to be communicated
 * to the activity and potentially other fragments contained in that
 * activity.
 * <p>
 * See the Android Training lesson <a href=
 * "http://developer.android.com/training/basics/fragments/communicating.html"
 * >Communicating with Other Fragments</a> for more information.
 */
