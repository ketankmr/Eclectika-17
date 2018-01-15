package aditya.eclectika17;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Artists extends Fragment {
    GridView gridView;
    UserDatabase database;
    SQLiteDatabase db;



    // This Data show in grid ( Used by adapter )

    static  ArrayList Number = new ArrayList();;



    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_artists, container, false);

        database = new UserDatabase(getActivity());


        gridView = (GridView) view.findViewById(R.id.gridview);

        db= database.getReadableDatabase();

        final Cursor cursor = database.get_event_by_category("14",db);




        gridView.setAdapter(  new CustomGridAdapter( getActivity() ) );

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                if (cursor.moveToPosition(position)) {

                    Intent intent = new Intent(getActivity(),news_details.class);
                    intent.putExtra("title",cursor.getString(1));
                    intent.putExtra("description",cursor.getString(2));
                    intent.putExtra("url","https://s-media-cache-ak0.pinimg.com/originals/e4/a0/a1/e4a0a179a199e74936e98b9aff80a423.jpg");
                    startActivity(intent);


                }

            }
        });

        return view;
    }
}
