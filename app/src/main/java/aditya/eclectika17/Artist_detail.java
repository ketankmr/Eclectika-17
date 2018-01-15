package aditya.eclectika17;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


public class Artist_detail extends Fragment {
    UserDatabase database = new UserDatabase(getActivity());
    SQLiteDatabase db;



    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_artist_detail, container, false);

        ImageView image = (ImageView) view.findViewById(R.id.image);
        TextView name = (TextView) view.findViewById(R.id.textView);
        TextView descrip = (TextView) view.findViewById(R.id.textView2);

        db = database.getReadableDatabase();

        Cursor cursor = database.get_complete_event(getArguments().getString("number"),db);





        if(cursor.moveToFirst()) {

            name.setText(cursor.getString(1));
            descrip.setText(cursor.getString(2));




            Glide
                    .with(getActivity())
                    .load(cursor.getString(11))
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .transform(new BlurTransformation(getActivity()))

                    .into(image);

        }

        return view;
    }
}
