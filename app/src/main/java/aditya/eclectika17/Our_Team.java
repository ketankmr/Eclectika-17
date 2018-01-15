package aditya.eclectika17;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;


public class Our_Team extends Fragment {
   GridView gridView;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artists, container, false);




        gridView = (GridView) view.findViewById(R.id.gridview);
        return view ;
    }


}
