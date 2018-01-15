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
public class event_tab extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

     public View inflatedView;

    // TODO: Rename and change types of parameters
     String category,mine;
    private String mParam2;
    ArrayList itemname;



    public event_tab() {


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
    public static event_tab newInstance(String param1,String param2) {
        event_tab fragment = new event_tab();
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
            mine = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         inflatedView = inflater.inflate(R.layout.event_details, container, false);


        {
            itemname = new ArrayList();


            ListView listView = (ListView) inflatedView.findViewById(R.id.android_listlist);


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    String Slecteditem = itemname.get(position).toString();
                    Toast.makeText(getContext(),Slecteditem,Toast.LENGTH_LONG).show();
                    ((MainActivity)getActivity()).start_event_detail(Slecteditem);

                }
            });

           getData(category,mine);
        }




    return inflatedView;}
            //------------------MENU---------------------------------------------------------





    public void getData(String category,String mine)
    {





        ListView list=(ListView)inflatedView.findViewById(R.id.android_listlist);

        itemname.clear();
        Integer c=0;
        UserDatabase database = new UserDatabase(getActivity().getApplicationContext());
        SQLiteDatabase db= database.getReadableDatabase();
        Cursor cursor= database.get_event_by_category(category,db);


      if (cursor.moveToFirst())

          if(mine.equalsIgnoreCase("no"))
          {{do {





                itemname.add(c,cursor.getString(0));


                c++;
            } while (cursor.moveToNext());}}
        else {

              {{do {




                  if(database.is_registered(cursor.getString(0),db).equalsIgnoreCase("yes")){
                  itemname.add(c,cursor.getString(0));
                  c++;
              }} while (cursor.moveToNext());}}

          }

        Event_category_list_adapter adapter = new Event_category_list_adapter(getActivity(), itemname, 0);
        Event_category_list_adapter_going adapter2=new Event_category_list_adapter_going(getActivity(), itemname, 0);

        if(mine.equalsIgnoreCase("no")) {
            list.setAdapter(adapter);
        }else {
            list.setAdapter(adapter2);
        }







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
